package ru.pushok;

public enum Toys {
    A_BALL_OF_THREAD("Клубок ниток", 10),
    TOY_MOUSE("Игрушечная мышь", 15),
    CANDY_WRAPPER("Фантик", 5);

    private final String name;
    private final int playSatisfactionIncrease;

    Toys(String name, int wantToPlay) {
        this.name = name;
        this.playSatisfactionIncrease = wantToPlay;
    }

    public String getName() {
        return name;
    }

    public int getPlaySatisfactionIncrease() {
        return playSatisfactionIncrease;
    }
}
