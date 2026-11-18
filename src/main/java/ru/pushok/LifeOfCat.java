package ru.pushok;

import javax.swing.*;
import java.util.Scanner;

public class LifeOfCat {
    public static void main(String[] args) {
        // 1. Запрашиваем имя в консоли (пока оставим так для простоты)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя кота: ");
        String name = scanner.nextLine();

        // 2. Создаем кота
        Cat cat = new Cat();
        cat.setName(name);

        // 3. Запускаем графическое окно (Swing требует запуска в отдельном потоке)
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow(cat);
            window.setVisible(true);
        });
    }
}
