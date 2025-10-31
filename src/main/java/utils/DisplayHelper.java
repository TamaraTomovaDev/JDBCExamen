package utils;

import models.*;

import java.util.List;

public class DisplayHelper {

    // Toont een lijst van gebruikers in de console.
    public static void toonGebruikers(List<User> users) {
        System.out.println("\n--- Gebruikerslijst ---");
        users.forEach(user -> System.out.println("ID: " + user.getId() +
                ", Username: " + user.getUsername() +
                ", Email: " + user.getEmail()));
    }

    // Toont een lijst van posts in de console.
    public static void toonPosts(List<Post> posts) {
        System.out.println("\n--- Posts ---");
        posts.forEach(post -> System.out.println("ID: " + post.getId() +
                ", Titel: " + post.getTitle() +
                ", Gepubliceerd: " + post.isPublished()));
    }

    // Toont een lijst van topics in de console.
    public static void toonTopics(List<Topic> topics) {
        System.out.println("\n--- Topics ---");
        topics.forEach(topic -> System.out.println("ID: " + topic.getId() +
                ", Naam: " + topic.getName() +
                ", Slug: " + topic.getSlug()));
    }

    // Toont een lijst van likes in de console.
     public static void toonLikes(List<PostLike> likes) {
        System.out.println("\n--- Likes ---");
        likes.forEach(like -> System.out.println("ID: " + like.getId() +
                ", Post ID: " + like.getPost_id() +
                ", User ID: " + like.getUser_id()));
    }

    // Toont een lijst van post-topic koppelingen in de console.
    public static void toonKoppelingen(List<PostTopic> koppelingen) {
        System.out.println("\n--- Post-Topic koppelingen ---");
        koppelingen.forEach(pt -> System.out.println("ID: " + pt.getId() +
                ", Post ID: " + pt.getPostId() +
                ", Topic ID: " + pt.getTopicId()));
    }
}