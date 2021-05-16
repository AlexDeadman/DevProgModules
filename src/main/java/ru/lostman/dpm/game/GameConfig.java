package ru.lostman.dpm.game;

public class GameConfig {
    private String serverIp = "127.0.0.1";
    private int serverPort = 25655;
    private int difficulty = 2;
    private int tickDelay = 1000;
    private int savePeriod = 5;
    private String dbAddress = "127.0.0.1";
    private int dbPort = 3306;
    private String dbUsername = "root";
    private String dbPassword = "";

    public GameConfig() {
    }

    public GameConfig(
            String serverIp,
            int serverPort,
            int difficulty,
            int tickDelay,
            int savePeriod,
            String dbAddress,
            int dbPort,
            String dbUsername,
            String dbPassword
    ) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.difficulty = difficulty;
        this.tickDelay = tickDelay;
        this.savePeriod = savePeriod;
        this.dbAddress = dbAddress;
        this.dbPort = dbPort;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "serverIp='" + serverIp + '\'' +
                ", serverPort=" + serverPort +
                ", difficulty=" + difficulty +
                ", tickDelay=" + tickDelay +
                ", savePeriod=" + savePeriod +
                ", dbAddress='" + dbAddress + '\'' +
                ", dbPort=" + dbPort +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                '}';
    }

    public String getServerIp() {
        return serverIp;
    }

    public GameConfig setServerIp(String serverIp) {
        this.serverIp = serverIp;
        return this;
    }

    public int getServerPort() {
        return serverPort;
    }

    public GameConfig setServerPort(int serverPort) {
        this.serverPort = serverPort;
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

    public String getDbAddress() {
        return dbAddress;
    }

    public GameConfig setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
        return this;
    }

    public int getDbPort() {
        return dbPort;
    }

    public GameConfig setDbPort(int dbPort) {
        this.dbPort = dbPort;
        return this;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public GameConfig setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
        return this;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public GameConfig setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
        return this;
    }
}
