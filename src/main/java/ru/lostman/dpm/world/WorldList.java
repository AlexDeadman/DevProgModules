package ru.lostman.dpm.world;

import java.util.ArrayList;
import java.util.List;

public class WorldList {
    private List<World> worlds = new ArrayList<>();

    public void addWorld(World world) {
        this.worlds.add(world);
    }

    @Override
    public String toString() {
        return "WorldList{" +
            "worlds=" + worlds +
            '}';
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public WorldList setWorlds(List<World> worlds) {
        this.worlds = worlds;
        return this;
    }
}