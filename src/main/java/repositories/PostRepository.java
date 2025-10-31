package repositories;

import config.MySqlConfig;
import models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    public long create(Post post) throws SQLException {
        String query = "INSERT INTO post (user_id, title, slug, image, body, published) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getSlug());
            stmt.setString(4, post.getImage());
            stmt.setString(5, post.getBody());
            stmt.setBoolean(6, post.isPublished());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }

    public List<Post> read() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM post";
        try (Connection conn = MySqlConfig.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setTitle(rs.getString("title"));
                post.setSlug(rs.getString("slug"));
                post.setImage(rs.getString("image"));
                post.setBody(rs.getString("body"));
                post.setPublished(rs.getBoolean("published"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUpdatedAt(rs.getTimestamp("updated_at"));
                posts.add(post);
            }
        }
        return posts;
    }

    public int update(int id, Post post) throws SQLException {
        String query = "UPDATE post SET user_id=?, title=?, slug=?, image=?, body=?, published=? WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getSlug());
            stmt.setString(4, post.getImage());
            stmt.setString(5, post.getBody());
            stmt.setBoolean(6, post.isPublished());
            stmt.setInt(7, id);
            return stmt.executeUpdate();
        }
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM post WHERE id=?";
        try (Connection conn = MySqlConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}