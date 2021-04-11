package ru.lostman.world;

import com.google.gson.*;
import ru.lostman.entity.Entity;
import ru.lostman.entity.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldDeserializer implements JsonDeserializer<World> {

    @Override
    public World deserialize(
        JsonElement json,
        Type typeOfT,
        JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        World world = new World();
        world.setId(jsonObject.get("id").getAsInt());
        world.setTitle(jsonObject.get("title").getAsString());

        List<Entity> entities = new ArrayList<>();
        JsonArray jsonEntities = jsonObject.getAsJsonArray("entities");

        for (JsonElement jsonEntity : jsonEntities) {
            if (Objects.equals(jsonEntity.getAsJsonObject().get("title").getAsString(), "Player")) {
                entities.add(context.deserialize(jsonEntity, Player.class));
            } else {
                entities.add(context.deserialize(jsonEntity, Entity.class));
            }
        }

        world.setEntities(entities);

        return world;
    }
}
