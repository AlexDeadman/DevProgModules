package ru.lostman.dpm.world;

import ru.lostman.dpm.entity.Entity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class World {
    private int id = -2;
    private String title = "Unknown world";
    private List<Entity> entities = new ArrayList<>();

    public World() {
    }

    public World(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "World{" +
            "id=" + id +
            ", worldName='" + title + '\'' +
            ", entities=" + entities +
            '}';
    }

    public void update() {
        entities.forEach(Entity::update);
        entities.removeIf(ent -> ent.getHealth() <= 0);
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public World setId(int id) {
        this.id = id;
        return this;
    }

    public World setTitle(String title) {
        this.title = title;
        return this;
    }

    public World setEntities(List<Entity> entities) {
        this.entities = entities;
        return this;
    }
}
