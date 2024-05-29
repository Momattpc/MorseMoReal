import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MorseChallenge extends JFrame {
    private JLabel morseLabel;
    private JTextField userInputField;
    private JLabel feedbackLabel;
    private JButton nextQuestionButton;
    private JButton stopButton;
    private JButton startQuizButton;
    private JButton homeButton;
    private Map<String, String> morseToEnglish;
    private String currentMorseCode;

    public MorseChallenge() {
        initUI();
        loadMorseCodes();
    }

    private void initUI() {
        setTitle("Morse Code Challenge");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Start Quiz button
        startQuizButton = new JButton("Start Quiz");
        startQuizButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        startQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startQuizButton.addActionListener(new StartQuizButtonListener());
        panel.add(startQuizButton);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Morse code label
        morseLabel = new JLabel("Press 'Start Quiz' to begin");
        morseLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        morseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(morseLabel);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // User input field
        userInputField = new JTextField();
        userInputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        userInputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(userInputField);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Feedback label
        feedbackLabel = new JLabel("Pending input");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(feedbackLabel);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Next Question button
        nextQuestionButton = new JButton("Submit -> Next Question");
        nextQuestionButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        nextQuestionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextQuestionButton.addActionListener(new NextQuestionButtonListener());
        nextQuestionButton.setVisible(false);
        panel.add(nextQuestionButton);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Stop button
        stopButton = new JButton("Stop");
        stopButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.addActionListener(new StopButtonListener());
        stopButton.setVisible(false);
        panel.add(stopButton);

        // Spacer
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Home button
        homeButton = new JButton("Home");
        homeButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> {
                this.dispose();
                new MorseMode().setVisible(true);

        });
        panel.add(homeButton);

        add(panel);
        setVisible(true);
    }

    private void loadMorseCodes() {
        morseToEnglish = new HashMap<>();
        morseToEnglish.put(".... . .-.. .-.. ---", "hello");
        morseToEnglish.put("... --- ...", "sos");
        morseToEnglish.put("-... -.-- .", "bye");
        morseToEnglish.put("..-. --- ---", "foo");
        morseToEnglish.put("-... .- .-.", "bar");
    }

    private class StartQuizButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random random = new Random();
            Object[] keys = morseToEnglish.keySet().toArray();
            currentMorseCode = (String) keys[random.nextInt(keys.length)];
            morseLabel.setText(currentMorseCode);
            feedbackLabel.setText("Pending input");
            userInputField.setText("");
            userInputField.requestFocus();
            nextQuestionButton.setVisible(true);
            stopButton.setVisible(true);
        }
    }

    private class NextQuestionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = userInputField.getText().trim();
            if (userInput.equalsIgnoreCase(morseToEnglish.get(currentMorseCode))) {
                feedbackLabel.setText("Correct!");
            } else {
                feedbackLabel.setText("Incorrect. Your input: " + userInput + " Correct Answer is: " + morseToEnglish.get(currentMorseCode));

            }

            Random random = new Random();
            Object[] keys = morseToEnglish.keySet().toArray();
            currentMorseCode = (String) keys[random.nextInt(keys.length)];
            morseLabel.setText(currentMorseCode);
            userInputField.setText("");
            userInputField.requestFocus();
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logic to navigate back to the lessons page
            morseLabel.setText("Press 'Start Quiz' to begin");
            feedbackLabel.setText("Pending input");
            userInputField.setText("");
            nextQuestionButton.setVisible(false);
            stopButton.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MorseChallenge::new);
    }
}