package ru.pushok;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class GameWindow extends JFrame {
    private Cat cat;
    private JLabel catImageLabel;
    private JProgressBar satietyBar;
    private JProgressBar playBar;
    private JLabel infoLabel;
    private JTextArea eventLog;

    private JButton feedButton;
    private JButton playButton;
    private JButton sleepButton;

    private int tickCount = 0;
    private final Random random = new Random();
    private Timer gameTimer;

    private static final int TICKS_PER_YEAR = 20;
    private static final double WEIGHT_LOSS_PER_TICK = 0.001;
    private static final int SATIETY_LOSS_PER_TICK = 1;
    private static final int PLAY_LOSS_PER_TICK = 1;

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

        // 3. Панель с полосками и логом событий

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel statsPanel = new JPanel((new GridLayout(2, 2, 10, 10)));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsPanel.add(new JLabel("Сытость:"));
        satietyBar = new JProgressBar(0, 100);
        satietyBar.setStringPainted(true);
        statsPanel.add(satietyBar);

        statsPanel.add(new JLabel("Удовлетворенность:"));
        playBar = new JProgressBar(0, 100);
        playBar.setStringPainted(true);
        statsPanel.add(playBar);

        bottomPanel.add(statsPanel);

        // Лог событий (чтобы игрок видел, что происходит)
        eventLog = new JTextArea(5, 40);
        eventLog.setEditable(false);
        eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(eventLog);
        bottomPanel.add(scrollPane);

        // 4. Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        feedButton = new JButton("Покормить");
        playButton = new JButton("Играть");
        sleepButton = new JButton("Спать");

        buttonPanel.add(feedButton);
        buttonPanel.add(playButton);
        buttonPanel.add(sleepButton);

        bottomPanel.add(buttonPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        // 5. Обработчики кнопок
        feedButton.addActionListener(e -> {
            Food[] foods = Food.values();
            Food selected = (Food) JOptionPane.showInputDialog(
                    this,
                    "Выберите еду:",
                    "Кормление",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    foods,
                    foods[0]
            );
            if (selected != null) {
                String result = cat.feed(selected);
                log(result);
                updateUI();
                checkDeath();
            }
        });

        playButton.addActionListener(e -> {
            Toys[] toys = Toys.values();
            Toys selected = (Toys) JOptionPane.showInputDialog(
                    this,
                    "Выберите игру:",
                    "Во что поиграем?",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    toys,
                    toys[0]
            );
            if (selected != null) {
                String result = cat.play(selected);
                log(result);
                updateUI();
                checkDeath();
            }
        });

        sleepButton.addActionListener(e -> {
            sleepCat();
            updateUI();
            checkDeath();
        });

        gameTimer = new Timer(3000, e -> {
            tick();
            updateUI();
            checkDeath();
        });
        gameTimer.start();

        // Обновляем интерфейс при старте
        updateUI();
    }

    private void log(String message) {
        eventLog.append(message + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());

        // для отладки
        System.out.println(message);
    }

    private void tick() {
     tickCount++;
     cat.decreaseWeight(WEIGHT_LOSS_PER_TICK);
     cat.decreaseSatiety(SATIETY_LOSS_PER_TICK);
     cat.decreasePlaySatisfaction(PLAY_LOSS_PER_TICK);

        if (tickCount % TICKS_PER_YEAR == 0) {
            cat.increaseAge();
            log("🎂 " + cat.getName() + " стал старше! " + cat.getAgeInfo());
        }

        randomEvents();
    }

    public void randomEvents() {
        if (random.nextInt(100) + 1 <= 10) {
            int eventId = random.nextInt(4) + 1;

            switch (eventId) {
                case 1:
                    cat.increaseSatiety(5);
                    cat.increaseWeight(0.05);
                    log("🐭 " + cat.getName() + " поймал мышь! Сытость +5, вес +0.05");
                    break;
                case 2:
                    cat.decreaseSatiety(5);
                    log("💤 " + cat.getName() + " пропустил обед. Сытость -5");
                    break;
                case 3:
                    cat.increasePlaySatisfaction(10);
                    log("🎁 Нашел игрушку под диваном. Удовлетворенность играми +10");
                    break;
                case 4:
                    cat.decreasePlaySatisfaction(5);
                    log("🌧️ На улице дождь. " + cat.getName() + " грустит.");
                    break;
            }
        }
    }

    public void sleepCat() {
        gameTimer.stop();
        int hours = random.nextInt(9) + 4;
        log("😴 " + cat.getName() + " уснул на " + hours + " часов...");

        for (int i = 0; i < hours; i++) {
            tick();
            if (cat.isDead()) {
                log("💀 " + cat.getName() + " не проснулся...");
                return;
            }
        }

        log("☀️ " + cat.getName() + " проснулся!");

        if (!cat.isDead()) {
            gameTimer.start();
        }
    }

    private void checkDeath() {
        if (cat.isDead()) {
            gameTimer.stop();
            log("💀 " + cat.getName() + " " + cat.getDeathReason());
            JOptionPane.showMessageDialog(this,
                    "Игра окончена. " + cat.getDeathReason(),
                    "Смерть кота",
                    JOptionPane.ERROR_MESSAGE);

            feedButton.setEnabled(false);
            playButton.setEnabled(false);
            sleepButton.setEnabled(false);
        }
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

        String imagePath = "/images/cat_happy.png";

        if (cat.getMood() == Mood.HUNGRY) {
            imagePath = "/images/cat_hungry.png";
        } else if (cat.getMood() == Mood.SAD) {
            imagePath = "/images/cat_sad.png";
        } else if (cat.getMood() == Mood.BORED) {
            imagePath = "/images/cat_bored.png";
        }

        URL imgUrl = getClass().getResource(imagePath);
        if (imgUrl == null) {
            catImageLabel.setIcon(null);
            catImageLabel.setText("Картинка не найдена");
        } else {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            catImageLabel.setIcon(new ImageIcon(img));
            catImageLabel.setText("");
        }
    }
}
