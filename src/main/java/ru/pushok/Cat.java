package ru.pushok;

import java.util.Scanner;

public class Cat {
    private String name;
    private double weight;
    private int age;
    private int hunger;
    private int wantToPlay;

    private static final int FEED_THRESHOLD = 50;
    private static final int MAX_AGE = 15;
    private static final int MIN_HUNGER_TO_PLAY = 20;
    private static final int MAX_HUNGER_TO_PLAY = 90;
    private static final double MIN_HEALTHY_WEIGHT = 1.0;
    private static final double MAX_HEALTHY_WEIGHT = 15.0;

    public Cat() {
        this.weight = 3.0;
        this.age = 0;
        this.hunger = 50;
        this.wantToPlay = 50;
    }

    /*ввести имя кота*/
    public void askName(Scanner scanner) {
        System.out.println("Введите имя кота: ");
        this.name = scanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public String getWeightInfo() {
        return String.format("Вес кота: %.2f кг", weight);
    }

    private String getYearWord(int age) {
        int lastTwo = age % 100;
        int lastOne = age % 10;
        if (lastTwo >= 11 && lastTwo <= 14) return "лет";
        if (lastOne == 1) return "год";
        if (lastOne >= 2 && lastOne <= 4) return "года";
        return "лет";
    }

    public String getAgeInfo() {
        return "Возраст кота: " + age + " " + getYearWord(age);
    }

    public int getHunger() {
        return hunger;
    }

    public int getWantToPlay() {
        return wantToPlay;
    }

    public void feed(Food food) {
        if (this.hunger > FEED_THRESHOLD) {
            System.out.println("Кот не хочет есть");
            return;
        }

        this.weight = this.weight + food.getWeightGain();
        this.hunger = Math.min(100, this.hunger + food.getHungerIncrease());
        System.out.println("Кот поел " + "\"" + food.getName() + "\"" + ". " +
                "Вес: " + weight + " кг, сытость: " + hunger);
    }

    public void play(Toys toys) {
        if (this.wantToPlay == 100) {
            System.out.println(name + " не хочет играть, потому что он наигрался");
            return;
        }

        if (this.hunger <= MIN_HUNGER_TO_PLAY ) {
            System.out.println(name + " не хочет играть, потому что он голодный");
            return;
        } else if (this.hunger >= MAX_HUNGER_TO_PLAY) {
            System.out.println(name + " не хочет играть, потому что он сытый");
            return;
        }

        this.wantToPlay = Math.min(100, this.wantToPlay + toys.getWantToPlay());
        System.out.println("Кот поиграл " + "\"" + toys.getName() + "\"" + ". " +
                "Желание играть: " + wantToPlay);
    }

    public void increaseAge() {
        this.age = this.age + 1;
    }

    public void decreaseWeight(double weight) {
        this.weight = Math.max(0, this.weight - weight);
    }

    public void increaseWeight(double weight) {
        this.weight += weight;
    }

    public void increaseHunger(int hunger) {
        this.hunger = Math.min(100, this.hunger + hunger);
    }

    public void decreaseHunger(int hunger) {
        this.hunger = Math.max(0, this.hunger - hunger);
    }

    public void increaseWantToPlay(int wantToPlay) {
        this.wantToPlay = Math.min(100, this.wantToPlay + wantToPlay);
    }

    public void decreaseWantToPlay(int wantToPlay){
        this.wantToPlay = Math.max(0, this.wantToPlay  - wantToPlay);
    }

    public String getDeathReason(){
        if (weight < MIN_HEALTHY_WEIGHT) {
            return "Умер от истощения";
        } else if (weight > MAX_HEALTHY_WEIGHT) {
            return "Умер от ожирения";
        } else if (hunger <= 0) {
            return "Умер от голода";
        } else if (age >= MAX_AGE) {
            return "Умер от старости";
        }
        return null;
    }

    public boolean isDead() {
        return getDeathReason() != null;
    }

    public Mood getMood() {
        return Mood.of(this.hunger, this.wantToPlay);
    }
}