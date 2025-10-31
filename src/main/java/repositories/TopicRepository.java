package repositories;

import config.MySqlConfig;
import models.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicRepository {
    public long create(Topic topic) throws SQLException {
        String query = "INSERT INTO topic (name, slug) VALUES (?, ?)";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, topic.getName());
            stmt.setString(2, topic.getSlug());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getLong(1);
        }
        return -1;
    }

    public List<Topic> read() throws SQLException {
        List<Topic> topics = new ArrayList<>();
        String query = "SELECT * FROM topic";

        try (Connection conn = MySqlConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                topic.setSlug(rs.getString("slug"));
                topics.add(topic);
            }
        }
        return topics;
    }

    public int update(int id, Topic topic) throws SQLException {
        String query = "UPDATE topic SET name=?, slug=? WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, topic.getName());
            stmt.setString(2, topic.getSlug());
            stmt.setInt(3, id);
            return stmt.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM topic WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}
