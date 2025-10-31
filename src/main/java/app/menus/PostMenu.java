package app.menus;

import app.controllers.PostController;
import models.Post;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class PostMenu {
    private final Scanner scanner;
    private final PostController postController;

    public PostMenu(Scanner scanner, PostController postController) {
        this.scanner = scanner;
        this.postController = postController;
    }

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

    private void addPost() {
        int userId = InputHelper.getIntInput(scanner, "User ID: ");
        String title = InputHelper.getStringInput(scanner, "Titel: ");
        String slug = InputHelper.getStringInput(scanner, "Slug: ");
        String image = InputHelper.getStringInput(scanner, "Afbeelding URL: ");
        String body = InputHelper.getStringInput(scanner, "Body: ");
        boolean published = InputHelper.getBooleanInput(scanner, "Gepubliceerd? (true/false): ");

        long id = postController.createPost(userId, title, slug, image, body, published);
        System.out.println(id == -1 ? "Fout bij toevoegen van post." : "Post toegevoegd met ID: " + id);
    }

    private void listPosts() {
        List<Post> posts = postController.getAllPosts();
        if (posts.isEmpty()) {
            System.out.println("Geen posts gevonden.");
        } else {
            DisplayHelper.toonPosts(posts);
        }
    }

    private void updatePost() {
        int id = InputHelper.getIntInput(scanner, "ID van post om te updaten: ");
        String title = InputHelper.getStringInput(scanner, "Nieuwe titel: ");
        String slug = InputHelper.getStringInput(scanner, "Nieuwe slug: ");
        String image = InputHelper.getStringInput(scanner, "Nieuwe afbeelding URL: ");
        String body = InputHelper.getStringInput(scanner, "Nieuwe body: ");
        boolean published = InputHelper.getBooleanInput(scanner, "Gepubliceerd? (true/false): ");

        boolean success = postController.updatePost(id, title, slug, image, body, published);
        System.out.println(success ? "Post bijgewerkt." : "Bijwerken mislukt.");
    }

    private void deletePost() {
        int id = InputHelper.getIntInput(scanner, "ID van post om te verwijderen: ");
        boolean success = postController.deletePost(id);
        System.out.println(success ? "Post verwijderd." : "Verwijderen mislukt.");
    }
}