package ru.lostman;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class GameConnection {
    private final String address;
    private final int port;
    private final String database;
    private final String user;
    private final String password;

    private MysqlDataSource source;

    public GameConnection(
            String address,
            int port,
            String database,
            String user,
            String password
    ) {
        this.address = address;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public GameConnection(
            String address,
            String database,
            String user,
            String password
    ) {
        this(
                address,
                3306,
                database,
                user,
                password
        );
    }

    public Connection getConnection() throws SQLException {
        if (source == null) {
            source = new MysqlDataSource();

            source.setServerName(address);
            source.setPort(port);
            source.setDatabaseName(database);
            source.setUser(user);
            source.setPassword(password);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }

        return source.getConnection();
    }
}