package ru.lostman;

public class Player extends Entity{
    private String nickname;

    public Player(String nickname,
                  double posX,
                  double posZ,
                  int health,
                  int maxHealth,
                  int attackDamage,
                  double attackDistance,
                  double visibilityRange,
                  World world
    ) {
        super("Player",
                posX,
                posZ,
                health,
                maxHealth,
                attackDamage,
                attackDistance,
                visibilityRange,
                false,
                world

        );
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", attackDamage=" + attackDamage +
                ", attackDistance=" + attackDistance +
                ", visibilityRange=" + visionRange +
                ", world=" + world +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    // ----------------------------------------------------------------------------------------------------

    @Override
    public void update() {
        super.update();
        if(GameServer.getInstance().getServerTick() % 2 == 0) {
            if (this.health < this.maxHealth) {
                this.health++;
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
