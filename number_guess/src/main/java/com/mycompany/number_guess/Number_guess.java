/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.number_guess;

/**
 *
 * @author NEELIMA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Number_guess extends JFrame {
    private int Targetnum;
    private int attemptsLeft;
    private final JTextField guess;
    private final JButton submitButton;
    private final JLabel infoLabel;
    private final JLabel attemptsLabel;

    public Number_guess() {
        setTitle("Number Guessing Game");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));
        JPanel panel = new JPanel();
        add(panel);

        generateTargetNumber();

        infoLabel = new JLabel("Guess a number between 1 and 100");
        panel.add(infoLabel);
        guess = new JTextField(10);
        panel.add(guess);
        submitButton = new JButton("Submit Guess");
        panel.add(submitButton);
        attemptsLabel = new JLabel("Attempts left: 10");
        add(attemptsLabel);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
    }

    private void generateTargetNumber() {
        Random random = new Random();
        Targetnum = random.nextInt(100) + 1; 
        attemptsLeft = 10;
    }

    private void processGuess() {
        String input = guess.getText();
        int guessnum;

        try {
            guessnum = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
            guess.setText("");
            return;
        }

        attemptsLeft--;
        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        if (guessnum == Targetnum) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed the number!");
            playAgain();
        } else if (guessnum > Targetnum) {
            JOptionPane.showMessageDialog(this, "Try a lower number!");
        } else {
            JOptionPane.showMessageDialog(this, "Try a higher number!");
        }

        if (attemptsLeft == 0) {
            JOptionPane.showMessageDialog(this, "Out of attempts. The number was " + Targetnum + ".");
            playAgain();
        }

        guess.setText("");
    }

    private void playAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            generateTargetNumber();
            attemptsLeft = 10;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
        } else {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Number_guess().setVisible(true);
            }
        });
    }
}