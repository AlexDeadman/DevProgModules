package ru.lostman.dpm.game;

import ru.lostman.dpm.utils.DBMSConnection;
import ru.lostman.dpm.utils.DatabaseUtils;
import ru.lostman.dpm.utils.FileUtils;
import ru.lostman.dpm.entity.Entity;
import ru.lostman.dpm.entity.Player;
import ru.lostman.dpm.world.World;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private static GameServer instance;
    private List<World> worlds = new ArrayList<>();
    private GameConfig config = new GameConfig();
    private int serverTick = 1;
    private DBMSConnection dbmsConnection = new DBMSConnection(config);

    public GameServer() {
        instance = this;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "worlds=" + worlds +
                ", config=" + config +
                '}';
    }

    public World getWorldById(int worldId) {
        return this.worlds
                .stream()
                .dropWhile(world -> world.getId() != worldId)
                .findFirst()
                .orElseThrow();
        // при желании исключение можно обработать иначе
    }

    public void updateServer() throws SQLException {
        if (this.serverTick == 1) {
            System.out.print("Game was start");
        }
        System.out.print(". ");

        for (World world : worlds) {
            world.update();
        }

        try {
            Thread.sleep(this.config.getTickDelay());
            this.serverTick++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printEntities() {
        for (World world : worlds) {
            System.out.print("""


                    --------------------------------------------------------------------------------------------------
                    |  ID  |       Title       |   Health   |   Position  X ; Z   |       Nickname       |    Exp    |
                    |----- + ----------------- + ---------- + ------------------- + -------------------- + ----------|
                    """
            );

            for (Entity entity : world.getEntities()) {
                System.out.printf("| %-4d | %-17s | %-10d | %-9.2f;%9.2f ",
                        entity.getId(),
                        entity.getTitle(),
                        entity.getHealth(),
                        entity.getPosX(),
                        entity.getPosZ()
                );
                if (entity instanceof Player) {
                    Player asPlayer = ((Player) entity);
                    System.out.printf("| %-20s | %-9.1f |\n", asPlayer.getNickname(), asPlayer.getExperience());
                } else {
                    System.out.print("|                      |           |\n");
                }
            }
            System.out.println(
                    "--------------------------------------------------------------------------------------------------\n");
        }
    }

    public void startGame(int tickQuantity, String resultPath) throws IOException, SQLException {
        DatabaseUtils.restoreDatabase(dbmsConnection, "db_dump.sql");

        worlds.forEach(
                world -> world.getEntities().forEach(
                        entity -> {
                            try {
                                DatabaseUtils.insertEntity(dbmsConnection, entity);
                                if (entity instanceof Player) {
                                    DatabaseUtils.insertPlayer(dbmsConnection, (Player) entity);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );

        printEntities();

        for (int i = 0; i <= tickQuantity; i++) {
            this.updateServer();
            if (i % this.config.getSavePeriod() == 0) {
                FileUtils.saveWorlds(resultPath, this.worlds);
            }
        }

        printEntities();
    }

    public static GameServer getInstance() {
        return instance;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public GameConfig getConfig() {
        return config;
    }

    public int getServerTick() {
        return serverTick;
    }

    public DBMSConnection getDbmsConnection() {
        return dbmsConnection;
    }

    public GameServer setWorlds(List<World> worlds) {
        this.worlds = worlds;
        return this;
    }

    public GameServer setConfig(GameConfig config) {
        this.config = config;
        return this;
    }

    public GameServer setDbmsConnection(DBMSConnection dbmsConnection) {
        this.dbmsConnection = dbmsConnection;
        return this;
    }
}
