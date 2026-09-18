package ru.pushok;

public enum Food {
    FISH("Рыба", 0.1, 20),
    CANNED_TUNA("Консервированный тунец", 0.3, 30),
    BLACK_CAVIAR("Чёрная икра", 0.5, 40);

    private final String name;
    private final double weightGain;
    private final int hungerIncrease;

    Food(String name, double weightGain, int hungerIncrease) {
        this.name = name;
        this.weightGain = weightGain;
        this.hungerIncrease = hungerIncrease;
    }

    public String getName() {
        return name;
    }

    public double getWeightGain() {
        return weightGain;
    }

    public int getHungerIncrease() {
        return hungerIncrease;
    }
}
