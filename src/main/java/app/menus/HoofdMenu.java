package app.menus;

import services.*;
import utils.MenuHelper;

import java.util.Scanner;

public class HoofdMenu {
    // Scanner voor het lezen van gebruikersinvoer
    private final Scanner scanner;
    // Constructor voor HoofdMenu.
    // Ontvangt een Scanner-object van buitenaf zodat invoer gedeeld en consistent is
    // over alle menu's. Dit voorkomt problemen met meerdere Scanner-instanties.
    public HoofdMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    // Services worden geïnitialiseerd en doorgegeven aan de submenus
    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private final TopicService topicService = new TopicService();
    private final PostLikeService postLikeService = new PostLikeService();
    private final PostTopicService postTopicService = new PostTopicService();

    // Start het hoofdmenu en navigeert naar de juiste submenu op basis van gebruikerskeuze.
    public void start() {
        // Submenu-instanties
        UserMenu userMenu = new UserMenu(scanner, userService, postService, postLikeService);
        PostMenu postMenu = new PostMenu(scanner, postService, userService, postLikeService, postTopicService);
        TopicMenu topicMenu = new TopicMenu(scanner, topicService, postTopicService);
        LikeMenu likeMenu = new LikeMenu(scanner, postLikeService, postService, userService);
        KoppelingMenu koppelingMenu = new KoppelingMenu(scanner, postTopicService, postService, topicService);

        boolean exit = false;
        // Menu-opties
        String[] opties = {
                "Gebruikersbeheer",
                "Postsbeheer",
                "Topicsbeheer",
                "Likesbeheer",
                "Post-Topic koppeling",
                "Stoppen"
        };

        while (!exit) {
            // Toon menu en vraag keuze
            int keuze = MenuHelper.toonMenu(scanner, "BLOG BEHEER SYSTEEM", opties);

            // Verwerk keuze
            switch (keuze) {
                case 1 -> userMenu.show();
                case 2 -> postMenu.show();
                case 3 -> topicMenu.show();
                case 4 -> likeMenu.show();
                case 5 -> koppelingMenu.show();
                case 6 -> exit = true;
            }
        }
        scanner.close();
        System.out.println("Programma afgesloten.");
    }
}