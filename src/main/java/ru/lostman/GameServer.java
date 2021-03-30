package ru.lostman;

import java.util.List;

public class GameServer {
    private static GameServer instance;

    private String ip;
    private int difficulty;
    private final List<World> worlds;
    private int serverTick = 1;
    private final int tickDelay;

    public GameServer(
            String ip,
            int difficulty,
            List<World> worlds,
            int tickDelay
    ) {
        this.ip = ip;

        if (difficulty < 1) {
            this.difficulty = 1;
        } else {
            this.difficulty = Math.min(difficulty, 3);
        }

        this.worlds = worlds;
        this.tickDelay = tickDelay;

        instance = this;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "ip='" + ip + '\'' +
                ", difficulty=" + difficulty +
                ", worlds=" + worlds +
                ", serverTick=" + serverTick +
                ", tickDelay=" + tickDelay +
                '}';
    }

    // ----------------------------------------------------------------------------------------------------

    public void updateServer() {
        if (this.serverTick == 1) {
            System.out.print("Game was started");
        }
        System.out.print(". ");

        worlds.forEach(World::update);

        try {
            Thread.sleep(this.tickDelay);
            this.serverTick++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printEntities() {
        for (World world : worlds) {
            System.out.print( "\n\n┌──────┬───────────────────┬────────────┬─────────────────────┬──────────────────────┐\n" +
                                  "│  ID  │       Title       │   Health   │   Position X ; Z    │       Nickname       │\n" +
                                  "├──────┼───────────────────┼────────────┼─────────────────────┼──────────────────────┤\n"
            );

            for (Entity entity : world.getEntities()) {
                System.out.printf("│ %-4d │ %-17s │ %-10d │ %-9.2f;%9.2f ",
                        entity.id,
                        entity.title,
                        entity.health,
                        entity.posX,
                        entity.posZ
                );
                if (entity instanceof Player) {
                    System.out.printf("│ %-20s │\n", ((Player) entity).getNickname());
                } else {
                     System.out.print("│                      │\n");
                }
            }
            System.out.println(    "└──────┴───────────────────┴────────────┴─────────────────────┴──────────────────────┘\n");
        }
    }

    // ----------------------------------------------------------------------------------------------------

    public static GameServer getInstance() {
        return instance;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public int getServerTick() {
        return serverTick;
    }
}
