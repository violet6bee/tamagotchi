package ru.pushok;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    @Test
    void catShouldBeCreatedWithDefaultStats() {
        Cat cat = new Cat();
        assertEquals(3.0, cat.getWeight(), 0.01);
        assertEquals(0, cat.getAge());
        assertEquals(50, cat.getSatiety());
        assertEquals(50, cat.getPlaySatisfaction());
    }

    @Test
    void catShouldEatWhenHungry() {
        Cat cat = new Cat();
        cat.feed(Food.FISH);
        assertEquals(70, cat.getSatiety());
    }

    @Test
    void satietyShouldNotExceedMaxLimit() {
        Cat cat = new Cat();
        cat.feed(Food.FISH);
        cat.feed(Food.FISH);
        cat.feed(Food.FISH);
        assertEquals(100, cat.getSatiety());
    }

    @Test
    void catShouldRefuseToEatWhenCompletelyFull() {
        Cat cat = new Cat();
        cat.feed(Food.BLACK_CAVIAR);
        cat.feed(Food.BLACK_CAVIAR);
        cat.feed(Food.FISH);
        assertEquals(100, cat.getSatiety());
        assertEquals(4.0, cat.getWeight());
    }
}