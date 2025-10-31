package repositories;

import config.MySqlConfig;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public long create(User user) throws SQLException {
        String query = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getLong(1);
        }
        return -1;
    }

    public List<User> read() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (Connection conn = MySqlConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }
        }
        return users;
    }

    public int update(int id, User user) throws SQLException {
        String query = "UPDATE user SET username=?, email=?, password=? WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, id);
            return stmt.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM user WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}