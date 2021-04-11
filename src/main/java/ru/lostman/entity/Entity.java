package ru.lostman.entity;

import ru.lostman.game.GameServer;
import ru.lostman.world.World;

import java.util.List;

public class Entity {
    protected String title = "UnknownEntity";
    protected double posX = 0.0;
    protected double posZ = 0.0;
    protected int health = 0;
    protected int maxHealth = 100;
    protected int attackDamage = 0;
    protected double attackDistance = 0.0;
    protected double visionRange = 10.0;
    protected Entity target = null;
    protected int worldId = -1;
    protected transient World world = null;

    protected static int idCounter = 1;
    protected final long id = idCounter++;

    private boolean agressive;

    public Entity() {
    }

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
        int worldId
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
        this.worldId = worldId;

        this.world = GameServer.getInstance().getWorldById(worldId);
    }

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
        this.worldId = world.getId();
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
            ", world=" + world.getTitle() +
            ", worldId=" + worldId +
            ", agressive=" + agressive +
            '}';
    }

    // ----------------------------------------------------------------------------------------------------

    public void attack(Entity target) {
        target.health -= this.attackDamage + 0.5 * GameServer.getInstance().getConfig().getDifficulty();

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

        if (sortedEntities.size() < 1) {
            return null;
        } else {
            return sortedEntities.get(0);
        }
    }

    public void update() {
        if(this.world == null) {
            this.world = GameServer
                .getInstance()
                .getWorldById(worldId);
//            TODO worldId может остаться -1
        }

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

    public Entity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Entity setPosX(double posX) {
        this.posX = posX;
        return this;
    }

    public Entity setPosZ(double posZ) {
        this.posZ = posZ;
        return this;
    }

    public Entity setHealth(int health) {
        this.health = health;
        return this;
    }

    public Entity setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public Entity setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public Entity setAttackDistance(double attackDistance) {
        this.attackDistance = attackDistance;
        return this;
    }

    public Entity setVisionRange(double visionRange) {
        this.visionRange = visionRange;
        return this;
    }

    public Entity setWorldId(int worldId) {
        this.worldId = worldId;
        return this;
    }

    public Entity setWorld(World world) {
        this.world = world;
        return setWorldId(world.getId());
    }

    public static void setIdCounter(int idCounter) {
        Entity.idCounter = idCounter;
    }

    public Entity setAgressive(boolean agressive) {
        this.agressive = agressive;
        return this;
    }
}
