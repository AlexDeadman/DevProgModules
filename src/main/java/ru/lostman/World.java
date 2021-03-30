package ru.lostman;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class World {
    private int id;
    private String worldName;
    private List<Entity> entities;

    public World(int id, String worldName) {
        this.id = id;
        this.worldName = worldName;
    }

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", worldName='" + worldName + '\'' +
                ", entities=" + entities +
                '}';
    }

    // ----------------------------------------------------------------------------------------------------

    public void update() {
        entities.forEach(Entity::update);
        entities.removeIf(ent -> ent.health <= 0);
    }

    public static double getDistanceToEntity(double x, double z, Entity other) {
        return Math.sqrt(
                Math.pow((x - other.getPosX()), 2) + Math.pow((z - other.getPosZ()), 2)
        );
    }

    public static List<Entity> getEntitiesByPosition(double x, double z, List<Entity> entities) {
        Map<Entity, Double> entitiesWithDistance =
                entities.stream().collect(
                        Collectors.toMap(Function.identity(), ent -> getDistanceToEntity(x, z, ent))
                );
        return entitiesWithDistance
                .entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .filter(ent -> !ent.isAgressive())
                .collect(Collectors.toList());
    }

    public static List<Entity> getEntitiesNearEntity(Entity entity, List<Entity> entities) {
        return getEntitiesByPosition(entity.getPosX(), entity.getPosZ(), entities)
                .stream()
                .filter(ent -> !ent.equals(entity))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
