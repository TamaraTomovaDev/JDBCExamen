package app.menus;

import app.controllers.PostLikeController;
import models.PostLike;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class LikeMenu {
    private final Scanner scanner;
    private final PostLikeController likeController;

    public LikeMenu(Scanner scanner, PostLikeController likeController) {
        this.scanner = scanner;
        this.likeController = likeController;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Likesbeheer ---");
            System.out.println("1. Like toevoegen");
            System.out.println("2. Alle likes tonen");
            System.out.println("3. Like verwijderen");
            System.out.println("4. Terug naar hoofdmenu");

            int keuze = InputHelper.getIntInputInRange(scanner, "Kies: ", 1, 4);

            switch (keuze) {
                case 1 -> addLike();
                case 2 -> listLikes();
                case 3 -> deleteLike();
                case 4 -> back = true;
            }
        }
    }

    private void addLike() {
        int postId = InputHelper.getIntInput(scanner, "Post ID: ");
        int userId = InputHelper.getIntInput(scanner, "User ID: ");

        long id = likeController.createLike(postId, userId);
        System.out.println(id == -1 ? "Fout bij toevoegen van Like." : "Like toegevoegd met ID: " + id);
    }

    private void listLikes() {
        List<PostLike> likes = likeController.getAllLikes();
        if (likes.isEmpty()) {
            System.out.println("Geen likes gevonden.");
        } else {
            DisplayHelper.toonLikes(likes);
        }
    }

    private void deleteLike() {
        int id = InputHelper.getIntInput(scanner, "ID van like om te verwijderen: ");
        boolean success = likeController.deleteLike(id);
        System.out.println(success ? "Like verwijderd." : "Verwijderen mislukt.");
    }
}