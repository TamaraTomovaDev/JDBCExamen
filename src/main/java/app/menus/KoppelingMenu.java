package app.menus;

import controllers.PostTopicController;
import models.PostTopic;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class KoppelingMenu {
    private final Scanner scanner;
    private final PostTopicController koppelingController;

    public KoppelingMenu(Scanner scanner, PostTopicController koppelingController) {
        this.scanner = scanner;
        this.koppelingController = koppelingController;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Post-Topic koppeling ---");
            System.out.println("1. Koppeling toevoegen");
            System.out.println("2. Alle koppelingen tonen");
            System.out.println("3. Koppeling verwijderen");
            System.out.println("4. Terug naar hoofdmenu");

            int keuze = InputHelper.getIntInputInRange(scanner, "Kies: ", 1, 4);

            switch (keuze) {
                case 1 -> addKoppeling();
                case 2 -> listKoppelingen();
                case 3 -> deleteKoppeling();
                case 4 -> back = true;
            }
        }
    }

    private void addKoppeling() {
        int postId = InputHelper.getIntInput(scanner, "Post ID: ");
        int topicId = InputHelper.getIntInput(scanner, "Topic ID: ");

        long id = koppelingController.createKoppeling(postId, topicId);
        System.out.println(id == -1 ? "Fout bij toevoegen van koppeling." : "Koppeling toegevoegd met ID: " + id);
    }

    private void listKoppelingen() {
        List<PostTopic> koppelingen = koppelingController.getAllKoppelingen();
        if (koppelingen.isEmpty()) {
            System.out.println("Geen koppelingen gevonden.");
        } else {
            DisplayHelper.toonKoppelingen(koppelingen);
        }
    }

    private void deleteKoppeling() {
        int id = InputHelper.getIntInput(scanner, "ID van koppeling om te verwijderen: ");
        boolean success = koppelingController.deleteKoppeling(id);
        System.out.println(success ? "Koppeling verwijderd." : "Verwijderen mislukt.");
    }
}