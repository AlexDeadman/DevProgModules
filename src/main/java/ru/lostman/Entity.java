package ru.lostman;

import java.util.List;

public class Entity {
    protected String title;
    protected double posX;
    protected double posZ;
    protected int health;
    protected int maxHealth;
    protected int attackDamage;
    protected double attackDistance;
    protected double visionRange;
    protected Entity target;
    protected World world;

    protected static int idCounter = 1;
    protected final long id;

    private boolean agressive;

    public Entity(
            String title,
            double posX,
            double posZ,
            int health,
            int maxHealth,
            int attackDamage,
            double attackDistance,
            double visionRange,
            boolean agressive,
            World world
    ) {
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.attackDistance = attackDistance;
        this.visionRange = visionRange;
        this.agressive = agressive;
        this.world = world;

        this.id = idCounter++;
    }

    @Override
    public String toString() {
        return "Entity{" +
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
                ", agressive=" + agressive +
                '}';
    }

    // ----------------------------------------------------------------------------------------------------

    public void attack(Entity target) {
        target.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();

        if (target instanceof Player) {
            if (target.health > 0) {
                target.attack(this);
            }
        }

        if (target.health <= 0) {
            String killer;
            if (this instanceof Player) {
                killer = ((Player) this).getNickname();
            } else {
                killer = this.title;
            }

            String victim;
            if (target instanceof Player) {
                victim = ((Player) target).getNickname();
            } else {
                victim = target.title;
            }

            System.out.printf(
                    "\n\n%s was slain by %s (%s has %d HP; server tick: %d)",
                    victim, killer, killer, this.health, GameServer.getInstance().getServerTick()
                    );

            this.target = null;
        }
    }

    private Entity searchTarget() {
        List<Entity> sortedEntities =
                World.getEntitiesNearEntity(this, this.world.getEntities());

        // в дальнейшем можно будет возвращать несколько целей
        return sortedEntities.get(0);
    }

    public void update() {
        if (this.agressive) {
            if (this.target == null) {
                target = searchTarget();
            }

            // searchTarget может вернуть null
            if (this.target != null) {
                double distanceToTarget = World.getDistanceToEntity(this.getPosX(), this.getPosZ(), target);
                if (distanceToTarget < this.visionRange) {
                    if (distanceToTarget < this.attackDistance) {
                        attack(target);
                    } else {
                        if ((target.getPosX() - this.posX) > 0) {
                            this.posX++;
                        } else {
                            this.posX--;
                        }
                        if ((target.getPosZ() - this.posZ) > 0) {
                            this.posZ++;
                        } else {
                            this.posZ--;
                        }
                    }
                }
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosZ() {
        return posZ;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getAttackDistance() {
        return attackDistance;
    }

    public double getVisionRange() {
        return visionRange;
    }

    public Entity getTarget() {
        return target;
    }

    public World getWorld() {
        return world;
    }

    public long getId() {
        return id;
    }

    public boolean isAgressive() {
        return agressive;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAgressive(boolean agressive) {
        this.agressive = agressive;
    }

    public void setAttackDistance(double attackDistance) {
        this.attackDistance = attackDistance;
    }

    public void setVisionRange(double visionRange) {
        this.visionRange = visionRange;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
