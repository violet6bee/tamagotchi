package ru.pushok;

public class Cat {
    private String name;
    private double weight;
    private int age;
    private int satiety;
    private int playSatisfaction;

    private static final int FEED_THRESHOLD = 50;
    private static final int MAX_AGE = 15;
    private static final int MIN_HUNGER_TO_PLAY = 20;
    private static final int MAX_HUNGER_TO_PLAY = 90;
    private static final double MIN_HEALTHY_WEIGHT = 1.0;
    private static final double MAX_HEALTHY_WEIGHT = 15.0;

    public Cat() {
        this.weight = 3.0;
        this.age = 0;
        this.satiety = 50;
        this.playSatisfaction = 50;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSatiety() {
        return satiety;
    }

    public int getPlaySatisfaction() {
        return playSatisfaction;
    }

    public void feed(Food food) {
        if (this.satiety > FEED_THRESHOLD) {
            System.out.println("Кот не хочет есть");
            return;
        }

        this.weight = this.weight + food.getWeightGain();
        this.satiety = Math.min(100, this.satiety + food.getSatietyIncrease());
        System.out.println("Кот поел " + "\"" + food.getName() + "\"" + ". " +
                "Вес: " + weight + " кг, сытость: " + satiety);
    }

    public void play(Toys toys) {
        if (this.playSatisfaction == 100) {
            System.out.println(name + " не хочет играть, потому что он наигрался");
            return;
        }

        if (this.satiety <= MIN_HUNGER_TO_PLAY ) {
            System.out.println(name + " не хочет играть, потому что он голодный");
            return;
        } else if (this.satiety >= MAX_HUNGER_TO_PLAY) {
            System.out.println(name + " не хочет играть, потому что он сытый");
            return;
        }

        this.playSatisfaction = Math.min(100, this.playSatisfaction + toys.getPlaySatisfactionIncrease());
        System.out.println("Кот поиграл " + "\"" + toys.getName() + "\"" + ". " +
                "Желание играть: " + playSatisfaction);
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

    public void increaseSatiety(int satiety) {
        this.satiety = Math.min(100, this.satiety + satiety);
    }

    public void decreaseSatiety(int satiety) {
        this.satiety = Math.max(0, this.satiety - satiety);
    }

    public void increaseWantToPlay(int wantToPlay) {
        this.playSatisfaction = Math.min(100, this.playSatisfaction + wantToPlay);
    }

    public void decreaseWantToPlay(int wantToPlay){
        this.playSatisfaction = Math.max(0, this.playSatisfaction - wantToPlay);
    }

    public String getDeathReason(){
        if (weight < MIN_HEALTHY_WEIGHT) {
            return "Умер от истощения";
        } else if (weight > MAX_HEALTHY_WEIGHT) {
            return "Умер от ожирения";
        } else if (satiety <= 0) {
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
        return Mood.of(this.satiety, this.playSatisfaction);
    }
}