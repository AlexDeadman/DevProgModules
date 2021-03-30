package ru.lostman;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int difficulty = 1;
        double defaultAttackDistance = 2.0;
        double defaultVisibilityRange = 20.0;

        World earth = new World(0, "Earth");
        List<World> epicWorlds = new ArrayList<>();
        epicWorlds.add(earth);

        List<Entity> humanoidz = new ArrayList<>(
                List.of(
                    new Entity(
                            "Woman",
                            1.0,
                            1.0,
                            100,
                            100,
                            7,
                            defaultAttackDistance,
                            defaultVisibilityRange,
                            true,
                            earth
                    ),
                    new Entity(
                            "Stranger",
                            11.0,
                            11.0,
                            20,
                            100,
                            1,
                            defaultAttackDistance,
                            defaultVisibilityRange,
                            false,
                            earth
                    ),
                    new Player(
                            "TRUE MAN",
                            1.1,
                            1.1,
                            150,
                            150,
                            30,
                            defaultAttackDistance,
                            defaultVisibilityRange,
                            earth
                    ),
                    new Entity(
                            "EPIC BOSS",
                            16.0,
                            13.0,
                            200,
                            200,
                            15,
                            5.0,
                            30.0,
                            true,
                            earth
                    )
                )
        );

        earth.setEntities(humanoidz);

        GameServer coolServer = new GameServer(ip, difficulty, epicWorlds, 500);

        coolServer.printEntities();

        int ticksQuantity = 30;

        for (int i = 0; i <= ticksQuantity; i++) {
            coolServer.updateServer();
        }

        coolServer.printEntities();
    }
}
