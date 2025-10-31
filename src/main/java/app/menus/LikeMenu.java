package app.menus;

import models.PostLike;
import services.PostLikeService;
import services.PostService;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class LikeMenu {
    private final Scanner scanner;
    private final PostLikeService postLikeService;
    private final PostService postService;
    private final UserService userService;

    public LikeMenu(Scanner scanner, PostLikeService postLikeService, PostService postService, UserService userService) {
        this.scanner = scanner;
        this.postLikeService = postLikeService;
        this.postService = postService;
        this.userService = userService;
    }

    // Toont het menu en handelt gebruikerskeuzes af.
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Likesbeheer ---");
            System.out.println("1. Like toevoegen");
            System.out.println("2. Alle likes tonen");
            System.out.println("3. Like verwijderen");
            System.out.println("4. Terug naar hoofdmenu");

            int keuze = getIntInput("Kies: ");

            switch (keuze) {
                case 1 -> addLike();         // Voeg een nieuwe like toe
                case 2 -> listLikes();       // Toon alle likes
                case 3 -> deleteLike();      // Verwijder een like
                case 4 -> back = true;       // Ga terug naar hoofdmenu
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }

    // Voegt een like toe aan een post door een gebruiker.
    private void addLike() {
        int postId = getIntInput("Post ID: ");
        int userId = getIntInput("User ID: ");


        // Controleer of de post en gebruiker bestaan.
        boolean postExists = postService.getAllPosts().stream().anyMatch(p -> p.getId() == postId);
        boolean userExists = userService.getAllUsers().stream().anyMatch(u -> u.getId() == userId);

        if (!postExists) {
            System.out.println("Kan geen Like toevoegen: Post ID bestaat niet.");
            return;
        }
        if (!userExists) {
            System.out.println("Kan geen Like toevoegen: User ID bestaat niet.");
            return;
        }

        // Maak en voeg de like toe.
        PostLike like = new PostLike();
        like.setPost_id(postId);
        like.setUser_id(userId);

        long id = postLikeService.addPostLike(like);
        System.out.println(id == -1 ? "Fout bij toevoegen van Like." : "Like toegevoegd met ID: " + id);
    }

    // Toon alle bestaande likes.
    private void listLikes() {
        List<PostLike> likes = postLikeService.getAllPostLikes();
        if (likes.isEmpty()) {
            System.out.println("Geen likes gevonden.");
        } else {
            likes.forEach(System.out::println);
        }
    }

    // Verwijder een like op basis van ID.
    private void deleteLike() {
        int id = getIntInput("ID van like om te verwijderen: ");
        boolean success = postLikeService.deletePostLike(id);
        System.out.println(success ? "Like verwijderd." : "Verwijderen mislukt.");
    }

    // Hulpmethode om een integer in te lezen met foutafhandeling.
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }
}