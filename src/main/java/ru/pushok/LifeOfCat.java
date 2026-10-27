package ru.pushok;

import java.util.Scanner;

public class LifeOfCat {
    public static void main(String[] args) {
        Cat cat = new Cat();
        MenuOfCat menuOfCat = new MenuOfCat();
        Scanner scanner = new Scanner(System.in);

        menuOfCat.askName(cat, scanner);

        System.out.println("Стартовое состояние кота:");
        menuOfCat.InfoAboutCat(cat);

        boolean running = true;

        while (running) {
            System.out.println("\n" + "─".repeat(40));
            menuOfCat.showMainMenu();
            running = menuOfCat.handleMainCommand(cat, scanner);

            if (cat.isDead()) {
                System.out.println("💀 " + cat.getName() + " " + cat.getDeathReason());
                break;
            }
        }

        scanner.close();
    }
}
