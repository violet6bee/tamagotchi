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
    void catShouldRefuseToEatWhenCompletelyFull() {
        Cat cat = new Cat("Барсик", 4.0, 0, 100, 50);
        cat.feed(Food.FISH);
        assertEquals(100, cat.getSatiety());
        assertEquals(4.0, cat.getWeight(), 0.01);
    }

    @Test
    void catShouldNotPlayIfHungry () {
        Cat cat = new Cat("Барсик", 3.0, 0, 20, 50);
        cat.play(Toys.TOY_MOUSE);
        assertEquals(50, cat.getPlaySatisfaction());
    }

    @Test
    void catShouldNotPlayWhenTooFull() {
        Cat cat = new Cat("Барсик", 3.0, 0, 100, 50);
        cat.play(Toys.TOY_MOUSE);
        assertEquals(50, cat.getPlaySatisfaction());
    }

    @Test
    void catShouldPlay() {
        Cat cat = new Cat();
        cat.play(Toys.TOY_MOUSE);
        assertEquals(65, cat.getPlaySatisfaction());
    }

    @Test
    void catShouldNotPlayWhenPlaySatisfactionIsFull() {
        Cat cat = new Cat("Барсик", 3.0, 0, 50, 100);
        cat.play(Toys.TOY_MOUSE);
        assertEquals(100, cat.getPlaySatisfaction());
    }

    @Test
    void catShouldDieFromMinWeight() {
        Cat cat = new Cat("Барсик", 0.9, 0, 50, 100);
        assertTrue(cat.isDead());
        assertEquals("Умер от истощения", cat.getDeathReason());
    }

    @Test
    void catShouldDieFromMaxWeight() {
        Cat cat = new Cat("Барсик", 15.1, 0, 50, 100);
        assertTrue(cat.isDead());
        assertEquals("Умер от ожирения", cat.getDeathReason());
    }

    @Test
    void catShouldDieFromHungry() {
        Cat cat = new Cat("Барсик", 4.1, 0, 0, 100);
        assertTrue(cat.isDead());
        assertEquals("Умер от голода", cat.getDeathReason());
    }

    @Test
    void catShouldDieFromMaxAge() {
        Cat cat = new Cat("Барсик", 5.1, 15, 10, 100);
        assertTrue(cat.isDead());
        assertEquals("Умер от старости", cat.getDeathReason());
    }

}