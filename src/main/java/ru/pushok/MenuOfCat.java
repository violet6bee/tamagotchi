package ru.pushok;

import java.util.Scanner;

public class MenuOfCat {
    private int menuCallCount = 0;

    public void InfoAboutCat(Cat cat) {
        System.out.println("(ฅ^•ﻌ•^ฅ) Информация о коте (ฅ^•ﻌ•^ฅ)");
        System.out.println("Имя: " + cat.getName());
        System.out.println(cat.getWeightInfo());
        System.out.println(cat.getAgeInfo());
        System.out.println("Сытость: " + cat.getHunger() + " (0 – сильно хочет есть, 100 – сыт)");
        System.out.println("Желание играть: " + cat.getWantToPlay() + " (0 – не хочет, 100 – хочет)");
    }

    public void showFoodMenu() {
        System.out.println("Выберите еду:");
        Food[] foods = Food.values();
        for (int i = 0; i < foods.length; i++) {
            System.out.println((i + 1) + ". " + foods[i].getName() +
                    " (вес +" + foods[i].getWeightGain() +
                    " кг, сытость +" + foods[i].getHungerIncrease() + ")");
        }
        System.out.println("0. Назад");
    }

    public void handleFeeding(Cat cat, Scanner scanner) {
        showFoodMenu();
        String choice = scanner.nextLine().trim();
        int number;
        try {
            number = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Нужно ввести число.");
            return;
        }
        if (number == 0) {
            return;
        }
        if (number >= 1 && number <= Food.values().length) {
            Food selected = Food.values()[number - 1];
            cat.feed(selected);
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    public void showPlayMenu() {
        System.out.println("Выберите игру:");
        Toys[] toys = Toys.values();
        for (int i = 0; i < toys.length; i++) {
            System.out.println((i + 1) + ". " + toys[i].getName() +
                    " (удовлетворение игрой +" + toys[i].getWantToPlay() + ")");
        }
        System.out.println("0. Назад");
    }

    public void play (Cat cat, Scanner scanner) {
        showPlayMenu();
        String choice = scanner.nextLine().trim();
        int number;
        try {
            number = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Нужно ввести число.");
            return;
        }
        if (number == 0) {
            return;
        }
        if (number >= 1 && number <= Toys.values().length) {
            Toys selected = Toys.values()[number - 1];
            cat.play(selected);
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    public void showMainMenu() {
        System.out.println(" (ฅ^•ﻌ•^ฅ) ГЛАВНОЕ МЕНЮ (ฅ^•ﻌ•^ฅ) ");
        String[] choiceMenu = {"1 – состояние", "2 – кормить", "3 – играть", "S – выход"};
        for (String s : choiceMenu) {
            System.out.println(s);
        }
    }

    public void countMainMenu(Cat cat) {
        menuCallCount++;
        cat.decreaseWeight(0.001);
        cat.increaseHunger(1);
        cat.decreaseWantToPlay(1);
        System.out.println("⏳ Прошло время: сытость −1, вес −1 г");

        if (menuCallCount % 20 == 0) {
            cat.increaseAge();
            System.out.println("🎂 " + cat.getName() + " стал старше! " + cat.getAgeInfo());
        }


    }

    public boolean handleMainCommand(Cat cat, Scanner scanner) {
        String choice = scanner.nextLine().trim().toUpperCase();
        switch (choice) {
            case "1":
                InfoAboutCat(cat);
                break;
            case "2":
                handleFeeding(cat, scanner);
                break;
            case "3":
                play(cat, scanner);
                break;
            case "S":
                return false;
            default:
                System.out.println("Неверный выбор. Выберите одну из этих команд" +
                        " \"1 – состояние\", \"2 – кормить\", \"3 – играть\", \"S – выход\"");
        }
        countMainMenu(cat);
        return true;
    }


}
