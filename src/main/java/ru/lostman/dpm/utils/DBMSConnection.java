package ru.lostman.dpm.utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBMSConnection {
    private final String address;
    private final int port;
    private final String user;
    private final String password;

    private MysqlDataSource source;

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
}