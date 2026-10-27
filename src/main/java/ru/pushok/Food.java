package ru.pushok;

public enum Food {
    FISH("Рыба", 0.1, 20),
    CANNED_TUNA("Консервированный тунец", 0.3, 30),
    BLACK_CAVIAR("Чёрная икра", 0.5, 40);

    private final String name;
    private final double weightGain;
    private final int satietyIncrease;

    Food(String name, double weightGain, int satietyIncrease) {
        this.name = name;
        this.weightGain = weightGain;
        this.satietyIncrease = satietyIncrease;
    }

    public String getName() {
        return name;
    }

    public double getWeightGain() {
        return weightGain;
    }

    public int getSatietyIncrease() {
        return satietyIncrease;
    }
}
