package app.menus;

import app.controllers.TopicController;
import models.Topic;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class TopicMenu {
    private final Scanner scanner;
    private final TopicController topicController;

    public TopicMenu(Scanner scanner, TopicController topicController) {
        this.scanner = scanner;
        this.topicController = topicController;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Topicsbeheer ---");
            System.out.println("1. Nieuw topic toevoegen");
            System.out.println("2. Alle topics tonen");
            System.out.println("3. Topic bijwerken");
            System.out.println("4. Topic verwijderen");
            System.out.println("5. Terug naar hoofdmenu");

            int keuze = InputHelper.getIntInputInRange(scanner, "Kies: ", 1, 5);

            switch (keuze) {
                case 1 -> addTopic();
                case 2 -> listTopics();
                case 3 -> updateTopic();
                case 4 -> deleteTopic();
                case 5 -> back = true;
            }
        }
    }

    private void addTopic() {
        String name = InputHelper.getStringInput(scanner, "Naam: ");
        String slug = InputHelper.getStringInput(scanner, "Slug: ");

        long id = topicController.createTopic(name, slug);
        System.out.println(id == -1 ? "Fout bij toevoegen van topic." : "Topic toegevoegd met ID: " + id);
    }

    private void listTopics() {
        List<Topic> topics = topicController.getAllTopics();
        if (topics.isEmpty()) {
            System.out.println("Geen topics gevonden.");
        } else {
            DisplayHelper.toonTopics(topics);
        }
    }

    private void updateTopic() {
        int id = InputHelper.getIntInput(scanner, "ID van topic om te updaten: ");
        String name = InputHelper.getStringInput(scanner, "Nieuwe naam: ");
        String slug = InputHelper.getStringInput(scanner, "Nieuwe slug: ");

        boolean success = topicController.updateTopic(id, name, slug);
        System.out.println(success ? "Topic bijgewerkt." : "Bijwerken mislukt.");
    }

    private void deleteTopic() {
        int id = InputHelper.getIntInput(scanner, "ID van topic om te verwijderen: ");
        boolean success = topicController.deleteTopic(id);
        System.out.println(success ? "Topic verwijderd." : "Verwijderen mislukt.");
    }
}