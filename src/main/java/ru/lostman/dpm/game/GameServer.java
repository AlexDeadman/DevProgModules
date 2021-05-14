package ru.lostman.dpm.game;

import ru.lostman.dpm.utils.FileUtils;
import ru.lostman.dpm.entity.Entity;
import ru.lostman.dpm.entity.Player;
import ru.lostman.dpm.world.World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private static GameServer instance;
    private List<World> worlds = new ArrayList<>();
    private GameConfig config = new GameConfig();
    private int serverTick = 1;

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

// ----------------------------------------------------------------------------------------------------

    public World getWorldById(int worldId) {
        return this.worlds
            .stream()
            .dropWhile(world -> world.getId() != worldId)
            .findFirst()
            .orElseThrow();
    }

    public void updateServer() {
        if (this.serverTick == 1) {
            System.out.print("Game was started");
        }
        System.out.print(". ");

        worlds.forEach(World::update);

        try {
            Thread.sleep(this.config.getTickDelay());
            this.serverTick++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printEntities() {
        for (World world : worlds) {
            System.out.print(
                """


                --------------------------------------------------------------------------------------
                |  ID  |       Title       |   Health   |   Position  X ; Z   |       Nickname       |
                |----- + ----------------- + ---------- + ------------------- + ---------------------|
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
                    System.out.printf("| %-20s |\n", ((Player) entity).getNickname());
                } else {
                    System.out.print("|                      |\n");
                }
            }
            System.out.println("--------------------------------------------------------------------------------------\n");
        }
    }

    public void startGame(int tickQuantity, String resultPath) throws IOException {

        for (int i = 0; i <= tickQuantity; i++) {
            this.updateServer();
            if (i % this.config.getSavePeriod() == 0) {
                FileUtils.saveWorlds(resultPath, this.worlds);
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------

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

    public GameServer setWorlds(List<World> worlds) {
        this.worlds = worlds;
        return this;
    }

    public GameServer setConfig(GameConfig config) {
        this.config = config;
        return this;
    }
}
