package ru.lostman.dpm;

import ru.lostman.dpm.entity.Entity;
import ru.lostman.dpm.game.GameConfig;
import ru.lostman.dpm.game.GameServer;
import ru.lostman.dpm.utils.DBMSConnection;
import ru.lostman.dpm.utils.DatabaseUtils;
import ru.lostman.dpm.utils.FileUtils;
import ru.lostman.dpm.world.World;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        DBMSConnection dbmsConnection = new DBMSConnection(
                "127.0.0.1",
                "root",
                "qwerty"
        );

        DatabaseUtils.createDatabase(dbmsConnection, "db_dump.sql");

//        DatabaseUtils.selectEntityByID(dbmsConnection, 99);

        GameServer coolServer = new GameServer();

        List<World> worlds = FileUtils.loadWorlds("worlds.json");
        if (worlds == null) {
            System.out.println("Не удалось загрузить миры");
            return;
        }
        coolServer.setWorlds(worlds);

        DatabaseUtils.insertEntity(dbmsConnection, worlds.get(0).getEntities().get(0));
//
//        GameConfig gameConfig = FileUtils.loadGameConfig("game_config.json");
//        if (gameConfig == null) {
//            System.out.println("Не удалось загрузить конфиг");
//            return;
//        }
//        coolServer.setConfig(gameConfig);
//
//        coolServer.printEntities();
//        coolServer.startGame(30, "worlds_result.json");
//        coolServer.printEntities();
//
//        FileUtils.saveGameConfig("game_config.json", GameServer.getInstance().getConfig());
    }
}
