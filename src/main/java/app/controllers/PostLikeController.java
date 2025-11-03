package app.controllers;

import models.PostLike;
import services.PostLikeService;

import java.util.List;

public class PostLikeController {
    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    public long createLike(int postId, int userId) {
        PostLike like = new PostLike();
        like.setPost_id(postId);
        like.setUser_id(userId);
        return postLikeService.addPostLike(like);
    }

    public List<PostLike> getAllLikes() {
        return postLikeService.getAllPostLikes();
    }

    public boolean deleteLike(int id) {
        return postLikeService.deletePostLike(id);
    }
}
