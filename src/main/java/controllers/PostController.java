package controllers;

import models.Post;
import services.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public long createPost(int userId, String title, String slug, String image, String body, boolean published) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setSlug(slug);
        post.setImage(image);
        post.setBody(body);
        post.setPublished(published);
        return postService.addPost(post);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public boolean updatePost(int id, String title, String slug, String image, String body, boolean published) {
        Post post = new Post();
        post.setTitle(title);
        post.setSlug(slug);
        post.setImage(image);
        post.setBody(body);
        post.setPublished(published);
        return postService.updatePost(id, post);
    }

    public boolean deletePost(int id) {
        return postService.deletePost(id);
    }
}
