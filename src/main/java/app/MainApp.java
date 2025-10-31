package app;

import app.menus.HoofdMenu;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // Scanner wordt gedeeld met alle menu's
        Scanner scanner = new Scanner(System.in);

        // Start het hoofdmenu
        HoofdMenu hoofdMenu = new HoofdMenu(scanner);
        hoofdMenu.start();

        // Scanner netjes afsluiten
        scanner.close();
    }
}