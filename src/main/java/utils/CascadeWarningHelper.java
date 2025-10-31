package utils;

import services.PostService;
import services.PostLikeService;
import services.PostTopicService;

import java.util.Scanner;

public class CascadeWarningHelper {
    // Waarschuwt als een gebruiker gekoppelde posts of likes heeft.
    public static void waarschuwBijVerwijderenUser(int userId, PostService postService, PostLikeService postLikeService) {
        boolean heeftPosts = postService.getAllPosts().stream()
                .anyMatch(post -> post.getUserId() == userId);
        boolean heeftLikes = postLikeService.getAllPostLikes().stream()
                .anyMatch(like -> like.getUser_id() == userId);

        if (heeftPosts || heeftLikes) {
            System.out.println("Let op: bij het verwijderen van deze gebruiker worden ook gekoppelde posts en/of likes automatisch verwijderd.");
        }
    }

    // Waarschuwt als een post gekoppelde likes of topic-koppelingen heeft.
    public static void waarschuwBijVerwijderenPost(int postId, PostLikeService postLikeService, PostTopicService postTopicService) {
        boolean heeftLikes = postLikeService.getAllPostLikes().stream()
                .anyMatch(like -> like.getPost_id() == postId);
        boolean heeftKoppelingen = postTopicService.getAllPostTopics().stream()
                .anyMatch(pt -> pt.getPostId() == postId);

        if (heeftLikes || heeftKoppelingen) {
            System.out.println("⚠️ Let op: bij het verwijderen van deze post worden ook gekoppelde likes en/of topic-koppelingen automatisch verwijderd.");
        }
    }

    // Waarschuwt als een topic gekoppelde post-topic relaties heeft.
    public static void waarschuwBijVerwijderenTopic(int topicId, PostTopicService postTopicService) {
        boolean heeftKoppelingen = postTopicService.getAllPostTopics().stream()
                .anyMatch(pt -> pt.getTopicId() == topicId);

        if (heeftKoppelingen) {
            System.out.println("⚠️ Let op: bij het verwijderen van deze topic worden ook gekoppelde post-topic koppelingen automatisch verwijderd.");
        }
    }

    // Vraagt om bevestiging vóór verwijderen.
    public static boolean confirmDelete(Scanner scanner, String entityName) {
        System.out.print("Weet je zeker dat je " + entityName + " wilt verwijderen? (ja/nee): ");
        String antwoord = scanner.nextLine().trim().toLowerCase();
        return antwoord.equals("ja");
    }
}