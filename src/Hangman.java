import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Hangman extends JFrame {

    private JLabel wordLabel;
    private JTextField letterInput;
    private JLabel feedbackLabel;
    private JLabel triesLabel;
    private String secretWord;
    private StringBuilder displayedWord;
    private Set<Character> guessedLetters;
    private int triesLeft;

    public Hangman() {
        initUI();
    }

    private void initUI() {
        setTitle("Hangman");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Hangman");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        wordLabel = new JLabel();
        wordLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(wordLabel);

        letterInput = new JTextField();
        letterInput.setFont(new Font("SansSerif", Font.PLAIN, 18));
        letterInput.setMaximumSize(new Dimension(200, 30));
        letterInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        letterInput.addActionListener(new LetterInputListener());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Add some space
        mainPanel.add(letterInput);

        feedbackLabel = new JLabel("Enter a letter:");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(feedbackLabel);

        triesLabel = new JLabel();
        triesLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        triesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(triesLabel);

        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        startNewGame();
    }

    private void startNewGame() {
        secretWord = "example";  // For now, let's use a static word for simplicity
        displayedWord = new StringBuilder("_".repeat(secretWord.length()));
        guessedLetters = new HashSet<>();
        triesLeft = 6;  // Number of allowed wrong guesses

        wordLabel.setText(displayedWord.toString());
        triesLabel.setText("Tries left: " + triesLeft);
        letterInput.setText("");
        letterInput.requestFocus();
    }

    private class LetterInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = letterInput.getText().toLowerCase();
            if (input.isEmpty() || input.length() > 1) {
                feedbackLabel.setText("Please enter a single letter.");
                return;
            }

            char letter = input.charAt(0);
            if (guessedLetters.contains(letter)) {
                feedbackLabel.setText("You already guessed that letter.");
                return;
            }

            guessedLetters.add(letter);
            letterInput.setText("");

            if (secretWord.contains(Character.toString(letter))) {
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == letter) {
                        displayedWord.setCharAt(i, letter);
                    }
                }
                wordLabel.setText(displayedWord.toString());
                feedbackLabel.setText("Good guess!");

                if (displayedWord.toString().equals(secretWord)) {
                    feedbackLabel.setText("Congratulations! You won!");
                }
            } else {
                triesLeft--;
                triesLabel.setText("Tries left: " + triesLeft);
                feedbackLabel.setText("Wrong guess!");

                if (triesLeft == 0) {
                    feedbackLabel.setText("Game over! The word was: " + secretWord);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Hangman::new);
    }
}