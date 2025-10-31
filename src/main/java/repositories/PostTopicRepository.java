package repositories;

import config.MySqlConfig;
import models.PostTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostTopicRepository {
    public int create(PostTopic postTopic) throws SQLException {
        String query = "INSERT INTO post_topic (post_id, topic_id) VALUES (?, ?)";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, postTopic.getPostId());
            stmt.setInt(2, postTopic.getTopicId());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }

    public List<PostTopic> read() throws SQLException {
        List<PostTopic> postTopics = new ArrayList<>();
        String query = "SELECT * FROM post_topic";
        try (Connection conn = MySqlConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PostTopic postTopic = new PostTopic();
                postTopic.setId(rs.getInt("id"));
                postTopic.setPostId(rs.getInt("post_id"));
                postTopic.setTopicId(rs.getInt("topic_id"));
                postTopics.add(postTopic);
            }
        }
        return postTopics;
    }

    public int update(int id, PostTopic postTopic) throws SQLException {
        String query = "UPDATE post_topic SET post_id=?, topic_id=? WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, postTopic.getPostId());
            stmt.setInt(2, postTopic.getTopicId());
            stmt.setInt(3, id);
            return stmt.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM post_topic WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}
