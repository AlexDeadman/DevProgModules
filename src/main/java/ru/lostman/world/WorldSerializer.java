package ru.lostman.world;

import com.google.gson.*;
import ru.lostman.entity.Entity;
import ru.lostman.entity.Player;

import java.lang.reflect.Type;

public class WorldSerializer implements JsonSerializer<World> {
    @Override
    public JsonElement serialize(
        World src,
        Type typeOfSrc,
        JsonSerializationContext context
    ) {
        JsonObject result = new JsonObject();

        result.addProperty("id", src.getId());
        result.addProperty("title", src.getTitle());

        JsonArray entities = new JsonArray();
        result.add("entities", entities);
        for (Entity entity : src.getEntities()) {
            entities.add(
                entity instanceof Player ?
                    context.serialize(entity, Player.class) :
                    context.serialize(entity, Entity.class)
            );
        }

        return result;
    }
}
