package ru.lostman;

public class GameConfig {
    private String ip = "127.0.0.1";
    private int port = 25655;
    private int difficulty = 2;
    private int tickDelay = 1000;
    private int savePeriod = 5;

    @Override
    public String toString() {
        return "GameConfig{" +
            "ip='" + ip + '\'' +
            ", port=" + port +
            ", difficulty=" + difficulty +
            ", updatePeriod=" + tickDelay +
            ", savePeriod=" + savePeriod +
            '}';
    }

    public String getIp() {
        return ip;
    }

    public GameConfig setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public GameConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public GameConfig setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public GameConfig setTickDelay(int tickDelay) {
        this.tickDelay = tickDelay;
        return this;
    }

    public int getSavePeriod() {
        return savePeriod;
    }

    public GameConfig setSavePeriod(int savePeriod) {
        this.savePeriod = savePeriod;
        return this;
    }
}
