package ru.lostman;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
//        GameServer coolServer = new GameServer();
//
//        List<World> worlds = FileUtils.loadWorlds("worlds.json");
//        if (worlds == null) {
//            System.out.println("Не удалось загрузить миры");
//            return;
//        }
//        coolServer.setWorlds(worlds);
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
        GameConnection gameConnection = new GameConnection(
                "127.0.0.1",
                "dpm",
                "root",
                "qwerty"
        );

        DatabaseUtils.selectFromEntities(gameConnection);
    }
}
