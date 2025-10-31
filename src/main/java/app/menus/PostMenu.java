package app.menus;

import models.Post;
import services.PostLikeService;
import services.PostService;
import services.PostTopicService;
import services.UserService;
import utils.CascadeWarningHelper;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

// Menu voor het beheren van posts in het blogbeheersysteem.
public class PostMenu {
    private final Scanner scanner;
    private final PostService postService;
    private final UserService userService;
    private final PostLikeService postLikeService;
    private final PostTopicService postTopicService;

    public PostMenu(Scanner scanner, PostService postService, UserService userService,
                    PostLikeService postLikeService, PostTopicService postTopicService) {
        this.scanner = scanner;
        this.postService = postService;
        this.userService = userService;
        this.postLikeService = postLikeService;
        this.postTopicService = postTopicService;
    }

    // Toont het hoofdmenu voor postsbeheer.
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Postsbeheer ---");
            System.out.println("1. Nieuwe post toevoegen");
            System.out.println("2. Alle posts tonen");
            System.out.println("3. Post bijwerken");
            System.out.println("4. Post verwijderen");
            System.out.println("5. Terug naar hoofdmenu");

            int keuze = InputHelper.getIntInputInRange(scanner, "Kies: ", 1, 5);

            switch (keuze) {
                case 1 -> addPost();
                case 2 -> listPosts();
                case 3 -> updatePost();
                case 4 -> deletePost();
                case 5 -> back = true;
            }
        }
    }

    // Voegt een nieuwe post toe na invoer van gegevens.
    private void addPost() {
        int userId = InputHelper.getIntInput(scanner, "User ID: ");
        boolean userExists = userService.getAllUsers().stream().anyMatch(u -> u.getId() == userId);

        if (!userExists) {
            System.out.println("Kan geen post toevoegen: User ID bestaat niet.");
            return;
        }

        String title = InputHelper.getStringInput(scanner, "Titel: ");
        String slug = InputHelper.getStringInput(scanner, "Slug: ");
        String image = InputHelper.getStringInput(scanner, "Afbeelding URL: ");
        String body = InputHelper.getStringInput(scanner, "Body: ");
        boolean published = InputHelper.getBooleanInput(scanner, "Gepubliceerd? (true/false): ");

        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setSlug(slug);
        post.setImage(image);
        post.setBody(body);
        post.setPublished(published);

        long id = postService.addPost(post);
        System.out.println(id == -1 ? "Fout bij toevoegen van post." : " Post toegevoegd met ID: " + id);
    }

    // Toont alle posts in de console.
    private void listPosts() {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            System.out.println("Geen posts gevonden.");
        } else {
            DisplayHelper.toonPosts(posts);
        }
    }

    // Werkt een bestaande post bij op basis van ID.
    private void updatePost() {
        int id = InputHelper.getIntInput(scanner, "ID van post om te updaten: ");
        boolean exists = postService.getAllPosts().stream().anyMatch(p -> p.getId() == id);

        if (!exists) {
            System.out.println("Post met dit ID bestaat niet.");
            return;
        }

        String title = InputHelper.getStringInput(scanner, "Nieuwe titel: ");
        String slug = InputHelper.getStringInput(scanner, "Nieuwe slug: ");
        String image = InputHelper.getStringInput(scanner, "Nieuwe afbeelding URL: ");
        String body = InputHelper.getStringInput(scanner, "Nieuwe body: ");
        boolean published = InputHelper.getBooleanInput(scanner, "Gepubliceerd? (true/false): ");

        Post post = new Post();
        post.setTitle(title);
        post.setSlug(slug);
        post.setImage(image);
        post.setBody(body);
        post.setPublished(published);

        boolean success = postService.updatePost(id, post);
        System.out.println(success ? "Post bijgewerkt." : " Bijwerken mislukt.");
    }

    // Verwijdert een post na waarschuwing en bevestiging.
    private void deletePost() {
        int id = InputHelper.getIntInput(scanner, "ID van post om te verwijderen: ");
        boolean exists = postService.getAllPosts().stream().anyMatch(p -> p.getId() == id);

        if (!exists) {
            System.out.println("Post met dit ID bestaat niet.");
            return;
        }

        CascadeWarningHelper.waarschuwBijVerwijderenPost(id, postLikeService, postTopicService);

        if (CascadeWarningHelper.confirmDelete(scanner, "deze post")) {
            boolean success = postService.deletePost(id);
            System.out.println(success ? "Post verwijderd." : " Verwijderen mislukt.");
        } else {
            System.out.println("Verwijderen geannuleerd.");
        }
    }
}