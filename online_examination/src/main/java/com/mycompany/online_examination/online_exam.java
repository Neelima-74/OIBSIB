/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online_examination;

/**
 *
 * @author NEELIMA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
interface LoginListener {
    void onLogin(String username, String password);
}
interface ProfileUpdateListener {
    void onProfileUpdate(String name, int age, String newPassword);
}
class User {
    private String username;
    private String password;
    private String name;
    private int age;
    public User(String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }
}
class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;
private int selectedOption = -1;
    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public String getQuestionText() {
        return questionText;
    }
    public List<String> getOptions() {
        return options;
    }
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}
class Exam {
    private List<Question> questions;
    public Exam(List<Question> questions) {
        this.questions = questions;
    }
    public List<Question> getQuestions() {
        return questions;
    }
}

class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginListener loginListener;
    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (loginListener != null) {
                    loginListener.onLogin(username, password);
                }
            }
        });
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(loginButton);
        add(panel);
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
class ProfileFrame extends JFrame {
      private JTextField nameField;
    private JTextField ageField;
    private JPasswordField passwordField;
    private JButton updateButton;

    private ProfileUpdateListener profileUpdateListener; 

    public ProfileFrame() {
        setTitle("Update Profile");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel passwordLabel = new JLabel("New Password:");

        nameField = new JTextField();
        ageField = new JTextField();
        passwordField = new JPasswordField();
        updateButton = new JButton("Update");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String newPassword = new String(passwordField.getPassword());

                if (profileUpdateListener != null) {
                    profileUpdateListener.onProfileUpdate(name, age, newPassword);
                    dispose(); 
                }
            }
        });
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(updateButton);
        add(panel);
    }

    public void setProfileUpdateListener(ProfileUpdateListener listener) {
        this.profileUpdateListener = listener;
    }
}

class ExamFrame extends JFrame {
private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    public ExamFrame(List<Question> questions) {
        setTitle("Online Exam");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.questions = questions;
        initComponents();
        showCurrentQuestion();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        questionLabel = new JLabel();
        panel.add(questionLabel);

        optionButtons = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            panel.add(optionButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = -1;
                for (int i = 0; i < 3; i++) {
                    if (optionButtons[i].isSelected()) {
                        selectedOption = i;
                        break;
                    }
                }
                questions.get(currentQuestionIndex).setSelectedOption(selectedOption);
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showCurrentQuestion();
                } else {
                    if (currentQuestionIndex >= questions.size()) {
                    JOptionPane.showMessageDialog(
                            ExamFrame.this, 
                            "Exam Completed!", 
                            "Exam Completed", 
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    dispose(); 
                }
                   
                }
            }        
        });
        panel.add(nextButton);

        add(panel);
    }

    private void showCurrentQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText(question.getQuestionText());

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            optionButtons[i].setText(options.get(i));
            optionButtons[i].setSelected(false);
        }
    }

}

class TimerThread extends Thread {
     private int seconds;

    public TimerThread(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        for (int i = seconds; i > 0; i--) {
            System.out.println("Time remaining: " + i + " seconds");
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Time's up!");
    }
}

public class online_exam {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
         
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

                loginFrame.setLoginListener(new LoginListener() {
                    @Override
                    public void onLogin(String username, String password) {
                        loginFrame.dispose(); 

                        ProfileFrame profileFrame = new ProfileFrame();
                        profileFrame.setVisible(true);

                        profileFrame.setProfileUpdateListener(new ProfileUpdateListener() {
                            @Override
                            public void onProfileUpdate(String name, int age, String newPassword) {
                                profileFrame.dispose(); 

                                List<Question> questions = loadQuestions(); 
                                ExamFrame examFrame = new ExamFrame(questions);
                                examFrame.setVisible(true);

                                TimerThread timerThread = new TimerThread(10); 
                                timerThread.start();
                            }
                        });
                    }
                });
            }
        });
    }

    private static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Which animal is named biggest animal?", Arrays.asList("elephant", "hippopotamus", "kangaroo"), 1));
        questions.add(new Question("Which planet is nearest to the sun?", Arrays.asList("Mars", "Earth", "Venus"), 3));
        questions.add(new Question("what is the capital of india?", Arrays.asList("delhi", "paris", "dhaka"), 1));
        questions.add(new Question("who is the ceo of 'microdoft inc.'?", Arrays.asList("sundhar pichai", "bill gates", "ratan tata"), 2));

        return questions;
    }
}
