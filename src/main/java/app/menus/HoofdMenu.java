package app.menus;

import controllers.*;
import services.*;
import utils.MenuHelper;

import java.util.Scanner;

public class HoofdMenu {
    private final Scanner scanner;

    public HoofdMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        // Services
        UserService userService = new UserService();
        PostService postService = new PostService();
        TopicService topicService = new TopicService();
        PostLikeService likeService = new PostLikeService();
        PostTopicService koppelingService = new PostTopicService();

        // Controllers
        UserController userController = new UserController(userService);
        PostController postController = new PostController(postService);
        TopicController topicController = new TopicController(topicService);
        PostLikeController likeController = new PostLikeController(likeService);
        PostTopicController koppelingController = new PostTopicController(koppelingService);

        // Menus
        UserMenu userMenu = new UserMenu(scanner, userController);
        PostMenu postMenu = new PostMenu(scanner, postController);
        TopicMenu topicMenu = new TopicMenu(scanner, topicController);
        LikeMenu likeMenu = new LikeMenu(scanner, likeController);
        KoppelingMenu koppelingMenu = new KoppelingMenu(scanner, koppelingController);

        boolean exit = false;
        String[] opties = {
                "Gebruikersbeheer",
                "Postsbeheer",
                "Topicsbeheer",
                "Likesbeheer",
                "Post-Topic koppeling",
                "Stoppen"
        };

        while (!exit) {
            int keuze = MenuHelper.toonMenu(scanner, "BLOG BEHEER SYSTEEM", opties);

            switch (keuze) {
                case 1 -> userMenu.show();
                case 2 -> postMenu.show();
                case 3 -> topicMenu.show();
                case 4 -> likeMenu.show();
                case 5 -> koppelingMenu.show();
                case 6 -> exit = true;
            }
        }
        System.out.println("Programma afgesloten.");
    }
}