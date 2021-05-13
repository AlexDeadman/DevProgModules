package ru.lostman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseUtils {
    public static void selectFromEntities(GameConnection gameConnection) throws SQLException {
        try (Connection connection = gameConnection.getConnection()) {
            String sql = "SELECT * FROM entities";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("| " +
                    resultSet.getInt("id") + " | " +
                    resultSet.getString("title") + " | " +
                    resultSet.getTimestamp("create_datetime") + " | " +
                    resultSet.getTimestamp("death_datetime") + " |"
                );
            }
        }
    }
}
