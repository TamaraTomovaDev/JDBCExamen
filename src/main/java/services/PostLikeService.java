package services;

import models.PostLike;
import repositories.PostLikeRepository;

import java.sql.SQLException;
import java.util.List;

public class PostLikeService {
    private final PostLikeRepository postLikeRepository = new PostLikeRepository();

    public int addPostLike(PostLike postLike) {
        try {
            return postLikeRepository.create(postLike);
        } catch (SQLException e) {
            System.out.println("Fout bij toevoegen van post_like: " + e.getMessage());
            return -1;
        }
    }

    public List<PostLike> getAllPostLikes() {
        try {
            return postLikeRepository.read();
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen van post_like: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updatePostLike(int id, PostLike postLike) {
        try {
            return postLikeRepository.update(id, postLike) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij updaten van post_like: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePostLike(int id) {
        try {
            return postLikeRepository.delete(id) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij verwijderen van posts: " + e.getMessage());
            return false;
        }
    }
}
