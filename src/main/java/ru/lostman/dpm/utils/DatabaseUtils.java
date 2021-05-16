package ru.lostman.dpm.utils;

import ru.lostman.dpm.entity.Entity;
import ru.lostman.dpm.entity.Player;

import java.sql.*;
import java.util.List;

public abstract class DatabaseUtils {
    public static void restoreDatabase(DBMSConnection dbmsConnection, String dumpPath) throws SQLException {
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

    public static void updateEntityDeath(DBMSConnection dbmsConnection, Entity entity) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "update dpm.entities set death_datetime = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setLong(2, entity.getId());

            ps.executeUpdate();
        }
    }

//    PLAYERS

    public static void insertPlayer(DBMSConnection dbmsConnection, Player player) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "insert into dpm.players(id, nickname, exp) values(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, player.getId());
            ps.setString(2, player.getNickname());
            ps.setDouble(3, player.getExperience());

            ps.executeUpdate();
        }
    }

    public static void updatePlayerExp(DBMSConnection dbmsConnection, Player player) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query = "update dpm.players set exp = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setDouble(1, player.getExperience());
            ps.setLong(2, player.getId());

            ps.executeUpdate();
        }
    }

//    BATTLE LOGS

    public static void insertBattleLog(
            DBMSConnection dbmsConnection,
            Entity killer,
            Entity victim
    ) throws SQLException {
        try (Connection connection = dbmsConnection.getConnection()) {
            String query =
                    "insert into dpm.battle_logs(killer_id, victim_id, kill_datetime) values(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, killer.getId());
            ps.setLong(2, victim.getId());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();
        }
    }
}
