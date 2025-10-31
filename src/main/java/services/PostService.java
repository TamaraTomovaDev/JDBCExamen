package services;

import models.Post;
import repositories.PostRepository;

import java.sql.SQLException;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public long addPost(Post post) {
        try {
            return postRepository.create(post);
        } catch (SQLException e) {
            System.out.println("Fout bij toevoegen van post: " + e.getMessage());
            return -1;
        }
    }

    public List<Post> getAllPosts() {
        try {
            return postRepository.read();
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen van post: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updatePost(int id, Post post) {
        try {
            return postRepository.update(id, post) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij updaten van post: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePost(int id) {
        try {
            return postRepository.delete(id) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij verwijderen van post: " + e.getMessage());
            return false;
        }
    }
}
