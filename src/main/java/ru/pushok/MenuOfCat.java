package ru.pushok;

import java.util.Random;
import java.util.Scanner;

public class MenuOfCat {
    private int menuCallCount = 0;
    private final Random random = new Random();

    private static final int TICKS_PER_YEAR = 20;
    private static final double WEIGHT_LOSS_PER_TICK = 0.001;
    private static final int HUNGER_LOSS_PER_TICK = 1;
    private static final int PLAY_DESIRE_LOSS_PER_TICK = 1;

    public void askName(Cat cat, Scanner scanner) {
        System.out.print("Введите имя кота: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            cat.setName("Пушок");
        } else {
            cat.setName(input);
        }
        System.out.println("Кот создан!");
    }

    public void InfoAboutCat(Cat cat) {
        System.out.println("(ฅ^•ﻌ•^ฅ) Информация о коте (ฅ^•ﻌ•^ฅ)");
        System.out.println("Имя: " + cat.getName());
        System.out.println(cat.getWeightInfo());
        System.out.println(cat.getAgeInfo());
        System.out.println("Сытость: " + cat.getSatiety() + " (0 – сильно хочет есть, 100 – сыт)");
        System.out.println("Желание играть: " + cat.getWantToPlay() + " (0 – не хочет, 100 – хочет)");
        System.out.println("Настроение: " + cat.getMood().getMoodName());
    }

    public void showFoodMenu() {
        System.out.println("Выберите еду:");
        Food[] foods = Food.values();
        for (int i = 0; i < foods.length; i++) {
            System.out.println((i + 1) + ". " + foods[i].getName() +
                    " (вес +" + foods[i].getWeightGain() +
                    " кг, сытость +" + foods[i].getSatietyIncrease() + ")");
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

    public void sleep(Cat cat) {
        int hours = random.nextInt(9) + 4;
        System.out.println("😴 " + cat.getName() + " уснул на " + hours + " часов...");
        for (int i = 0; i < hours; i++) {
            tick(cat);
            if (cat.isDead()) {
                System.out.println("💀 " + cat.getName() + " не проснулся...");
                return;
            }
        }
        System.out.println("☀️ " + cat.getName() + " проснулся!");
        InfoAboutCat(cat);
    }

    public void randomEvents(Cat cat) {
        if (random.nextInt(100) + 1 <= 10) {
            int eventId = random.nextInt(4) + 1;
            System.out.println("\n✨ СЛУЧАЙНОЕ СОБЫТИЕ ✨");

            switch (eventId) {
                case 1:
                    System.out.println("🐭 " + cat.getName() + " поймал мышь! Сытость +5, вес +0.05 кг");
                    cat.increaseSatiety(5);
                    cat.increaseWeight(0.05);
                    break;
                case 2:
                    System.out.println("💤 " + cat.getName() + " долго спал и пропустил обед. Сытость -5");
                    cat.decreaseSatiety(5);
                    break;
                case 3:
                    System.out.println("🎁 Сосед подарил игрушку! Желание играть +10");
                    cat.increaseWantToPlay(10);
                    break;
                case 4:
                    System.out.println("🌧️ На улице дождь. " + cat.getName() + " грустит. Желание играть -5");
                    cat.decreaseWantToPlay(5);
                    break;
            }
        }
    }

    public void showMainMenu() {
        System.out.println(" (ฅ^•ﻌ•^ฅ) ГЛАВНОЕ МЕНЮ (ฅ^•ﻌ•^ฅ) ");
        String[] choiceMenu = {"1 – состояние", "2 – кормить", "3 – играть", "4 - спать",
                "S – выход"};
        for (String s : choiceMenu) {
            System.out.println(s);
        }
    }

    public void tick(Cat cat) {
        menuCallCount++;
        cat.decreaseWeight(WEIGHT_LOSS_PER_TICK);
        cat.decreaseSatiety(HUNGER_LOSS_PER_TICK);
        cat.decreaseWantToPlay(PLAY_DESIRE_LOSS_PER_TICK);

        if (menuCallCount % TICKS_PER_YEAR  == 0) {
            cat.increaseAge();
        }
    }

    public void countMainMenu(Cat cat) {
        tick(cat);

        System.out.println("⏳ Прошло время: сытость −1, вес −1 г");

        if (menuCallCount % TICKS_PER_YEAR == 0) {
            System.out.println("🎂 " + cat.getName() + " стал старше! " + cat.getAgeInfo());
        }

        randomEvents(cat);
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
            case "4":
                sleep(cat);
                return true;
            case "S":
                return false;
            default:
                System.out.println("Неверный выбор. Выберите одну из этих команд" +
                        " \"1 – состояние\", \"2 – кормить\", \"3 – играть\", , \"4 – спать\"," +
                        "\"S – выход\"");
                return true;
        }
        countMainMenu(cat);
        return true;
    }

}
