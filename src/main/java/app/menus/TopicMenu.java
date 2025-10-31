package app.menus;

import models.Topic;
import services.PostTopicService;
import services.TopicService;
import utils.CascadeWarningHelper;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class TopicMenu {
    private final Scanner scanner;
    private final TopicService topicService;
    private final PostTopicService postTopicService;

    public TopicMenu(Scanner scanner, TopicService topicService, PostTopicService postTopicService) {
        this.scanner = scanner;
        this.topicService = topicService;
        this.postTopicService = postTopicService;
    }

    // Toont het hoofdmenu voor topicsbeheer.
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

    // Voegt een nieuw topic toe na invoer van naam en slug.
    private void addTopic() {
        String name = InputHelper.getStringInput(scanner, "Naam: ");
        String slug = InputHelper.getStringInput(scanner, "Slug: ");

        Topic topic = new Topic();
        topic.setName(name);
        topic.setSlug(slug);

        long id = topicService.addTopic(topic);
        System.out.println(id == -1 ? "Fout bij toevoegen van topic." : " Topic toegevoegd met ID: " + id);
    }

    // Toont alle topics in de console.
    private void listTopics() {
        List<Topic> topics = topicService.getAllTopics();
        if (topics.isEmpty()) {
            System.out.println("Geen topics gevonden.");
        } else {
            DisplayHelper.toonTopics(topics);
        }
    }

    // Werkt een bestaand topic bij op basis van ID.
    private void updateTopic() {
        int id = InputHelper.getIntInput(scanner, "ID van topic om te updaten: ");
        boolean exists = topicService.getAllTopics().stream().anyMatch(t -> t.getId() == id);

        if (!exists) {
            System.out.println("Topic met dit ID bestaat niet.");
            return;
        }

        String name = InputHelper.getStringInput(scanner, "Nieuwe naam: ");
        String slug = InputHelper.getStringInput(scanner, "Nieuwe slug: ");

        Topic topic = new Topic();
        topic.setName(name);
        topic.setSlug(slug);

        boolean success = topicService.updateTopic(id, topic);
        System.out.println(success ? "Topic bijgewerkt." : " Bijwerken mislukt.");
    }

    // Verwijdert een topic na waarschuwing en bevestiging.
    private void deleteTopic() {
        int id = InputHelper.getIntInput(scanner, "ID van topic om te verwijderen: ");
        boolean exists = topicService.getAllTopics().stream().anyMatch(t -> t.getId() == id);

        if (!exists) {
            System.out.println("Topic met dit ID bestaat niet.");
            return;
        }

        CascadeWarningHelper.waarschuwBijVerwijderenTopic(id, postTopicService);

        if (CascadeWarningHelper.confirmDelete(scanner, "deze topic")) {
            boolean success = topicService.deleteTopic(id);
            System.out.println(success ? "Topic verwijderd." : " Verwijderen mislukt.");
        } else {
            System.out.println("Verwijderen geannuleerd.");
        }
    }
}