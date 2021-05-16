package ru.lostman.dpm.entity;

import ru.lostman.dpm.game.GameServer;
import ru.lostman.dpm.utils.DatabaseUtils;
import ru.lostman.dpm.world.World;

import java.sql.SQLException;

public class Player extends Entity {
    private String nickname = "UnknownPlayer";
    private double experience = 0.0;

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
        World world,
        String nickname,
        double experience
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
        this.experience = experience;
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
            String nickname,
            double experience
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
        this.experience = experience;
    }

    @Override
    public void update() throws SQLException {
        super.update();
        GameServer gameInstance = GameServer.getInstance();

        if (gameInstance.getServerTick() % 2 == 0) {
            if (this.health < this.maxHealth) {
                this.health++;
            }
        }
        if (gameInstance.getServerTick() % 5 == 0) {
            this.experience += 10.0 * gameInstance.getConfig().getDifficulty();
            DatabaseUtils.updatePlayerExp(gameInstance.getDbmsConnection(), this);
        }
    }

    public String getNickname() {
        return nickname;
    }

    public Player setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public double getExperience() {
        return experience;
    }

    public Player setExperience(double experience) {
        this.experience = experience;
        return this;
    }
}
