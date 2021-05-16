package ru.lostman.dpm.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import ru.lostman.dpm.game.GameConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DBMSConnection {
    private String address = "127.0.0.1";
    private int port = 3306;
    private String user = "root";
    private String password = "";

    private MysqlDataSource source;

    public DBMSConnection() {
    }

    public DBMSConnection(GameConfig config) {
        this.address = config.getDbAddress();
        this.port = config.getDbPort();
        this.user = config.getDbUsername();
        this.password = config.getDbPassword();
    }

    public DBMSConnection(
            String address,
            int port,
            String user,
            String password
    ) {
        this.address = address;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public DBMSConnection(
            String address,
            String user,
            String password
    ) {
        this(
                address,
                3306,
                user,
                password
        );
    }

    public Connection getConnection() throws SQLException {
        if (source == null) {
            source = new MysqlDataSource();

            source.setServerName(address);
            source.setPort(port);
            source.setUser(user);
            source.setPassword(password);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }

        return source.getConnection();
    }

    public String getAddress() {
        return address;
    }

    public DBMSConnection setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPort() {
        return port;
    }

    public DBMSConnection setPort(int port) {
        this.port = port;
        return this;
    }

    public String getUser() {
        return user;
    }

    public DBMSConnection setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DBMSConnection setPassword(String password) {
        this.password = password;
        return this;
    }
}