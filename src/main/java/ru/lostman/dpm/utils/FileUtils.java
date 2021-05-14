package ru.lostman.dpm.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lostman.dpm.game.GameConfig;
import ru.lostman.dpm.world.World;
import ru.lostman.dpm.world.WorldDeserializer;
import ru.lostman.dpm.world.WorldList;
import ru.lostman.dpm.world.WorldSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class FileUtils {
    public static List<World> loadWorlds(String jsonPath) {
        String jsonInputWorlds;
        try {
            jsonInputWorlds = Files.readString(Path.of(jsonPath));
        } catch (IOException e) {
            return null;
        }
        Gson gsonReader = new GsonBuilder()
            .registerTypeAdapter(World.class, new WorldDeserializer())
            .create();
        return gsonReader.fromJson(jsonInputWorlds, WorldList.class).getWorlds();
    }

    public static void saveWorlds(String jsonPath, List<World> worlds) throws IOException {
        Gson gsonWriter = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(World.class, new WorldSerializer())
            .create();
        String jsonOutputWorlds = gsonWriter.toJson(worlds);
        Files.writeString(Path.of(jsonPath), jsonOutputWorlds);
    }

    public static GameConfig loadGameConfig(String configPath) {
        String jsonInputConfig;
        try {
            jsonInputConfig = Files.readString(Path.of(configPath));
        } catch (IOException e) {
            return null;
        }
        Gson gsonReader = new GsonBuilder().create();
        return gsonReader.fromJson(jsonInputConfig, GameConfig.class);
    }

    public static void saveGameConfig(String configPath, GameConfig gameConfig) throws IOException {
        Gson gsonWriter = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        String jsonOutputConfig = gsonWriter.toJson(gameConfig);
        Files.writeString(Path.of(configPath), jsonOutputConfig);
    }

    public static List<String> loadDbDump(String dumpPath) {
        try {
            return List.of(Files.readString(Path.of(dumpPath)).split(";"));
        } catch (IOException e) {
            return null;
        }
    }
}
