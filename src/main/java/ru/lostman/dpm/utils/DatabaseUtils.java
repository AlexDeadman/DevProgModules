package ru.lostman.dpm.utils;

import ru.lostman.dpm.entity.Entity;

import java.sql.*;
import java.util.List;

public abstract class DatabaseUtils {
    public static void createDatabase(DBMSConnection dbmsConnection, String dumpPath) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            List<String> dump = FileUtils.loadDbDump(dumpPath);
            Statement statement = connection.createStatement();

            if (dump != null) {
                for (String query : dump) {
                    statement.addBatch(query);
                }
                statement.executeBatch();
            }
        }
    }

//    ENTITIES

    public static void selectAllEntities(DBMSConnection dbmsConnection) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "select * from dpm.entities";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                printEntityRow(resultSet);
            }
        }
    }

    public static void selectEntityByID(DBMSConnection dbmsConnection, long id) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "select * from dpm.entities where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                printEntityRow(resultSet);
            }
        }
    }

    private static void printEntityRow(ResultSet resultSet) throws SQLException {
        System.out.println(
            resultSet.getInt("id") + " " +
                resultSet.getString("title") + " " +
                resultSet.getTimestamp("create_datetime") + " " +
                resultSet.getTimestamp("death_datetime")
        );
    }

    public static void insertEntity(DBMSConnection dbmsConnection, Entity entity) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "insert into dpm.entities(title, create_datetime) values(?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, entity.getTitle());

            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getLong(1));
            }
        }
    }

//    TODO
//    private static void insertIntoBattleLog(DBMSConnection dbmsConnection, )
}
