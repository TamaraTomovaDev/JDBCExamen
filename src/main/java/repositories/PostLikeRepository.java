package repositories;

import config.MySqlConfig;
import models.PostLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostLikeRepository {
    public int create(PostLike postLike) throws SQLException {
        String query = "INSERT INTO post_like ( post_id, user_id) VALUES (?, ?)";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, postLike.getPost_id());
            stmt.setInt(2, postLike.getUser_id());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }

    public List<PostLike> read() throws SQLException {
        List<PostLike> postLikes = new ArrayList<>();
        String query = "SELECT * FROM post_like";
        try (Connection conn = MySqlConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PostLike postLike = new PostLike();
                postLike.setId(rs.getInt("id"));
                postLike.setPost_id(rs.getInt("post_id"));
                postLike.setUser_id(rs.getInt("user_id"));
                postLikes.add(postLike);
            }
        }
        return postLikes;
    }

    public int update(int id, PostLike postLike) throws SQLException {
        String query = "UPDATE post_like SET post_id=?, user_id=? WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, postLike.getPost_id());
            stmt.setInt(2, postLike.getUser_id());
            stmt.setInt(3, id);
            return stmt.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM post_like WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}
