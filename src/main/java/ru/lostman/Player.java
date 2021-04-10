package ru.lostman;

public class Player extends Entity {
    private String nickname = "UnknownPlayer";

    public Player() {
    }

    public Player(
        double posX,
        double posZ,
        int health,
        int maxHealth,
        int attackDamage,
        double attackDistance,
        double visionRange,
        int worldId,
        String nickname
    ) {
        super(
            "Player",
            posX,
            posZ,
            health,
            maxHealth,
            attackDamage,
            attackDistance,
            visionRange,
            false,
            worldId
        );
        this.nickname = nickname;
    }

    public Player(
        double posX,
        double posZ,
        int health,
        int maxHealth,
        int attackDamage,
        double attackDistance,
        double visionRange,
        World world,
        String nickname
    ) {
        super(
            "Player",
            posX,
            posZ,
            health,
            maxHealth,
            attackDamage,
            attackDistance,
            visionRange,
            false,
            world
        );
        this.nickname = nickname;
    }

    // ----------------------------------------------------------------------------------------------------

    @Override
    public void update() {
        super.update();
        if (GameServer.getInstance().getServerTick() % 2 == 0) {
            if (this.health < this.maxHealth) {
                this.health++;
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------

    public String getNickname() {
        return nickname;
    }

    public Player setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
