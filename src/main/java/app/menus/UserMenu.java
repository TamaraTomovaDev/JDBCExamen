package app.menus;

import controllers.UserController;
import models.User;
import utils.DisplayHelper;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final Scanner scanner;
    private final UserController userController;

    public UserMenu(Scanner scanner, UserController userController) {
        this.scanner = scanner;
        this.userController = userController;
    }

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
            }
        }
    }

    private void addUser() {
        String username = InputHelper.getStringInput(scanner, "Username: ");
        String email = InputHelper.getStringInput(scanner, "Email: ");
        String password = InputHelper.getStringInput(scanner, "Password: ");

        long id = userController.createUser(username, email, password);
        System.out.println(id == -1 ? "Fout bij toevoegen van gebruiker." : "Gebruiker toegevoegd met ID: " + id);
    }

    private void listUsers() {
        List<User> users = userController.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Geen gebruikers gevonden.");
        } else {
            DisplayHelper.toonGebruikers(users);
        }
    }

    private void updateUser() {
        int id = InputHelper.getIntInput(scanner, "ID van gebruiker om te updaten: ");
        String username = InputHelper.getStringInput(scanner, "Nieuwe username: ");
        String email = InputHelper.getStringInput(scanner, "Nieuwe email: ");
        String password = InputHelper.getStringInput(scanner, "Nieuw password: ");

        boolean success = userController.updateUser(id, username, email, password);
        System.out.println(success ? "Gebruiker bijgewerkt." : "Bijwerken mislukt.");
    }

    private void deleteUser() {
        int id = InputHelper.getIntInput(scanner, "ID van gebruiker om te verwijderen: ");
        boolean success = userController.deleteUser(id);
        System.out.println(success ? "Gebruiker verwijderd." : "Verwijderen mislukt.");
    }
}