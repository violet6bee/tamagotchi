package ru.pushok;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Cat cat;
    private JLabel catImageLabel;
    private JProgressBar satietyBar;
    private JProgressBar playBar;
    private JLabel infoLabel;

    public GameWindow(Cat cat) {
        this.cat = cat;

        setTitle("Жизнь кота: " + cat.getName());
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Верхняя панель с информацией
        infoLabel = new JLabel("Информация о коте", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(infoLabel, BorderLayout.NORTH);

        // 2. Центральная панель с картинкой
        catImageLabel = new JLabel("Тут будет кот", SwingConstants.CENTER);
        catImageLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        add(catImageLabel, BorderLayout.CENTER);

        // 3. Панель с полосками (сытость и игра)
        JPanel statsPanel = new JPanel((new GridLayout(2, 2, 10, 10)));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsPanel.add(new JLabel("Сытость:"));
        satietyBar = new JProgressBar(0, 100);
        satietyBar.setStringPainted(true);
        statsPanel.add(satietyBar);

        statsPanel.add(new JLabel("Желание играть:"));
        playBar = new JProgressBar(0, 100);
        playBar.setStringPainted(true);
        statsPanel.add(playBar);

        add(statsPanel, BorderLayout.SOUTH);

        // 4. Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton feedButton = new JButton("Покормить");
        JButton playButton = new JButton("Играть");
        JButton sleepButton = new JButton("Спать");

        buttonPanel.add(feedButton);
        buttonPanel.add(playButton);
        buttonPanel.add(sleepButton);
        add(buttonPanel, BorderLayout.PAGE_END);

        // 5. Обработчики кнопок
        feedButton.addActionListener(e -> {
            // Тут будет логика выбора еды
            cat.feed(Food.FISH);
            updateUI();
        });

        playButton.addActionListener(e -> {
            cat.play(Toys.TOY_MOUSE);
            updateUI();
        });

        sleepButton.addActionListener(e -> {
            // Логика сна
            updateUI();
        });

        // Обновляем интерфейс при старте
        updateUI();
    }

    // Метод для обновления картинки и полосок
    private void updateUI() {
        // Обновляем текст
        infoLabel.setText(String.format("Возраст: %d | Вес: %.2f | Настроение: %s",
                cat.getAge(), cat.getWeight(), cat.getMood().getMoodName()));

        // Обновляем полоски
        satietyBar.setValue(cat.getSatiety());
        playBar.setValue(cat.getPlaySatisfaction());

        // Меняем цвет полоски, если кот голоден
        if (cat.getSatiety() < 20) {
            satietyBar.setForeground(Color.RED);
        } else {
            satietyBar.setForeground(new Color(50, 200, 50)); // Зеленый
        }

        // Тут будет смена картинки (пока просто текст)
        if (cat.getSatiety() < 20) {
            catImageLabel.setText("Кот голоден! (Тут будет грустная картинка)");
        } else {
            catImageLabel.setText("Кот доволен! (Тут будет веселая картинка)");
        }
    }
}
