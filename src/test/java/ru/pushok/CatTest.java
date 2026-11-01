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
}