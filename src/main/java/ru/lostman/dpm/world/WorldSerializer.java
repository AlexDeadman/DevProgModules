package ru.lostman.dpm.world;

import com.google.gson.*;
import ru.lostman.dpm.entity.Entity;
import ru.lostman.dpm.entity.Player;

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
            entities.add(context.serialize(entity, entity.getClass()));
        }

        return result;
    }
}
