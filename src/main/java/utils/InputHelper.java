package utils;

import java.util.Scanner;

public class InputHelper {

    // Vraagt om een integer en valideert invoer. Blijft vragen tot een geldig getal is ingevoerd.
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer! Voer een geldig nummer in.");
            }
        }
    }

    // Vraagt om een integer binnen een bereik.
    public static int getIntInputInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int value = getIntInput(scanner, prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Ongeldige keuze! Kies een nummer tussen " + min + " en " + max + ".");
            }
        }
    }

    // Vraagt om een string.
    public static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Vraagt om een boolean (true/false).
    public static boolean getBooleanInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Ongeldige invoer! Typ 'true' of 'false'.");
        }
    }
}