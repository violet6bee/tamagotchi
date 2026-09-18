package ru.pushok;

import java.util.Scanner;

public class Cat {
    private String name;
    private double weight;
    private int age;
    private int hunger;
    private int wantToPlay;


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
        if (this.hunger > 50) {
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

        if (this.hunger <= 20 ) {
            System.out.println(name + " не хочет играть, потому что он голодный");
            return;
        } else if (this.hunger >= 90) {
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

    public void increaseHunger(int hunger) {
        this.hunger = Math.max(0, this.hunger - hunger);
    }

    public void decreaseWantToPlay(int wantToPlay){
        this.wantToPlay = Math.max(0, this.wantToPlay  - wantToPlay);
    }
}