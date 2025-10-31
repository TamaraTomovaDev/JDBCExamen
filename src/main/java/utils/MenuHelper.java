package utils;

import java.util.Scanner;

public class MenuHelper {
    // Toont een menu met een titel en opties, en vraagt de gebruiker om een geldige keuze.
    public static int toonMenu(Scanner scanner, String titel, String[] opties) {
        System.out.println("\n=== " + titel + " ===");
        for (int i = 0; i < opties.length; i++) {
            System.out.println((i + 1) + ". " + opties[i]);
        }
        return InputHelper.getIntInputInRange(scanner, "Kies een optie: ", 1, opties.length);
    }
}