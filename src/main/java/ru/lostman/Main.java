package ru.lostman;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

//        TODO вынести обработку файлов в FileUtils

        String jsonWorlds = Files.readString(Paths.get("worlds.json"));

        List<World> epicWorlds = new Gson().fromJson(jsonWorlds, WorldList.class).list;

        GameServer coolServer = new GameServer(
            "127.0.0.1",
            1,
            epicWorlds,
            500
        );

        coolServer.printEntities();

//        TODO засофткодить количество тиков
        for (int i = 0; i <= 30; i++) {
            coolServer.updateServer();
        }

        coolServer.printEntities();
    }
}
