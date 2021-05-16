package ru.lostman.dpm;

import ru.lostman.dpm.game.GameConfig;
import ru.lostman.dpm.game.GameServer;
import ru.lostman.dpm.utils.DBMSConnection;
import ru.lostman.dpm.utils.FileUtils;
import ru.lostman.dpm.world.World;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        GameServer coolServer = new GameServer();

        List<World> worlds = FileUtils.loadWorlds("worlds.json");
        if (worlds == null) {
            System.out.println("Не удалось загрузить миры");
            return;
        }
        coolServer.setWorlds(worlds);

        GameConfig gameConfig = FileUtils.loadGameConfig("game_config.json");
        if (gameConfig == null) {
            System.out.println("Не удалось загрузить конфиг");
            return;
        }
        coolServer.setConfig(gameConfig);
        coolServer.setDbmsConnection(new DBMSConnection(gameConfig));

        coolServer.startGame(30, "worlds_result.json");

        FileUtils.saveGameConfig("game_config.json", GameServer.getInstance().getConfig());
    }
}
