package ru.pushok;

public enum Toys {
    A_BALL_OF_THREAD("Клубок ниток", 10),
    TOY_MOUSE("Игрушечная мышь", 15),
    CANDY_WRAPPER("Фантик", 5);

    private final String name;
    private final int wantToPlay;

    Toys(String name, int wantToPlay) {
        this.name = name;
        this.wantToPlay = wantToPlay;
    }

    public String getName() {
        return name;
    }

    public int getWantToPlay() {
        return wantToPlay;
    }
}
