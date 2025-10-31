package app.menus;

import models.PostTopic;
import services.PostService;
import services.PostTopicService;
import services.TopicService;
import utils.InputHelper;
import utils.DisplayHelper;

import java.util.List;
import java.util.Scanner;

public class KoppelingMenu {
    private final Scanner scanner;
    private final PostTopicService postTopicService;
    private final PostService postService;
    private final TopicService topicService;

    public KoppelingMenu(Scanner scanner, PostTopicService postTopicService, PostService postService, TopicService topicService) {
        this.scanner = scanner;
        this.postTopicService = postTopicService;
        this.postService = postService;
        this.topicService = topicService;
    }

    // Toont het menu en handelt gebruikerskeuzes af.
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

    // Voegt een nieuwe koppeling toe tussen een post en een topic.
    private void addKoppeling() {
        int postId = InputHelper.getIntInput(scanner, "Post ID: ");
        int topicId = InputHelper.getIntInput(scanner, "Topic ID: ");

        boolean postExists = postService.getAllPosts().stream().anyMatch(p -> p.getId() == postId);
        boolean topicExists = topicService.getAllTopics().stream().anyMatch(t -> t.getId() == topicId);

        if (!postExists) {
            System.out.println("Kan geen koppeling toevoegen: Post ID bestaat niet.");
            return;
        }
        if (!topicExists) {
            System.out.println("Kan geen koppeling toevoegen: Topic ID bestaat niet.");
            return;
        }

        PostTopic pt = new PostTopic();
        pt.setPostId(postId);
        pt.setTopicId(topicId);

        long id = postTopicService.addPostTopic(pt);
        System.out.println(id == -1 ? "Fout bij toevoegen van koppeling." : "Koppeling toegevoegd met ID: " + id);
    }

    // Toont alle bestaande koppelingen tussen posts en topics.
    private void listKoppelingen() {
        List<PostTopic> koppelingen = postTopicService.getAllPostTopics();
        if (koppelingen.isEmpty()) {
            System.out.println("Geen koppelingen gevonden.");
        } else {
            DisplayHelper.toonKoppelingen(koppelingen);
        }
    }

    // Verwijdert een koppeling op basis van ID.
    private void deleteKoppeling() {
        int id = InputHelper.getIntInput(scanner, "ID van koppeling om te verwijderen: ");
        boolean success = postTopicService.deletePostTopic(id);
        System.out.println(success ? "Koppeling verwijderd." : " Verwijderen mislukt.");
    }
}