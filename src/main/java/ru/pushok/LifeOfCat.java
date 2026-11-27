package ru.pushok;

import javax.swing.*;
import java.util.Scanner;

public class LifeOfCat {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() ->{
             String name = "";

             while (name == null || name.trim().isEmpty()) {
                 name = JOptionPane.showInputDialog(
                         null,
                         "Как вы назовете своего кота?",
                         "Создание кота",
                         JOptionPane.QUESTION_MESSAGE
                 );

                 if (name == null) {
                     System.exit(0);
                 }
             }

             Cat cat = new Cat();
             cat.setName(name.trim());


             GameWindow window = new GameWindow(cat);
             window.setVisible(true);
         });

    }
}
