package ru.pushok;

public enum Mood {
    HAPPY("Счастлив"),
    BORING("Скучает"),
    HUNGRY("Голоден"),
    SAD("Грустит");

    private final String moodName;

    Mood(String moodName) {
        this.moodName = moodName;
    }

    public String getMoodName() {
        return moodName;
    }

    public static Mood of(int satiety, int wantToPlay) {
        boolean wellFed = satiety > 50;
        boolean wantsToPlay = wantToPlay > 50;

        if (wellFed && wantsToPlay) {
            return HAPPY;
        } else if (wellFed && !wantsToPlay) {
            return BORING;
        } else if (!wellFed && wantsToPlay) {
            return HUNGRY;
        }
        return SAD;
    }
}
