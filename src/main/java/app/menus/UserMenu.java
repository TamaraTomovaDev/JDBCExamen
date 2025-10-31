package app.menus;

import models.User;
import services.PostLikeService;
import services.PostService;
import services.UserService;
import utils.CascadeWarningHelper;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final Scanner scanner;
    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    public UserMenu(Scanner scanner, UserService userService, PostService postService, PostLikeService postLikeService) {
        this.scanner = scanner;
        this.userService = userService;
        this.postService = postService;
        this.postLikeService = postLikeService;
    }

    // Toont het gebruikersmenu en verwerkt keuzes.
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gebruikersbeheer ---");
            System.out.println("1. Nieuwe gebruiker toevoegen");
            System.out.println("2. Alle gebruikers tonen");
            System.out.println("3. Gebruiker bijwerken");
            System.out.println("4. Gebruiker verwijderen");
            System.out.println("5. Terug naar hoofdmenu");

            int keuze = InputHelper.getIntInputInRange(scanner, "Kies: ", 1, 5);

            switch (keuze) {
                case 1 -> addUser();
                case 2 -> listUsers();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> back = true;
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }

    // Voegt een nieuwe gebruiker toe.
    private void addUser() {
        String username = InputHelper.getStringInput(scanner, "Username: ");
        String email = InputHelper.getStringInput(scanner, "Email: ");
        String password = InputHelper.getStringInput(scanner, "Password: ");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        long id = userService.addUser(user);
        System.out.println(id == -1 ? "Fout bij toevoegen van gebruiker." : "Gebruiker toegevoegd met ID: " + id);
    }

    // Toont alle gebruikers.
    private void listUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Geen gebruikers gevonden.");
        } else {
            DisplayHelper.toonGebruikers(users);
        }
    }

    // Werkt een bestaande gebruiker bij.
    private void updateUser() {
        int id = InputHelper.getIntInput(scanner, "ID van gebruiker om te updaten: ");
        String username = InputHelper.getStringInput(scanner, "Nieuwe username: ");
        String email = InputHelper.getStringInput(scanner, "Nieuwe email: ");
        String password = InputHelper.getStringInput(scanner, "Nieuw password: ");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userService.updateUser(id, user);
        System.out.println(success ? "Gebruiker bijgewerkt." : "Bijwerken mislukt.");
    }

    // Verwijdert een gebruiker, met waarschuwing en bevestiging.
    private void deleteUser() {
        int id = InputHelper.getIntInput(scanner, "ID van gebruiker om te verwijderen: ");
        CascadeWarningHelper.waarschuwBijVerwijderenUser(id, postService, postLikeService);

        boolean bevestiging = CascadeWarningHelper.confirmDelete(scanner, "deze gebruiker");
        if (!bevestiging) {
            System.out.println("Verwijderen geannuleerd.");
            return;
        }

        boolean success = userService.deleteUser(id);
        System.out.println(success ? "Gebruiker verwijderd." : "Verwijderen mislukt.");
    }
}