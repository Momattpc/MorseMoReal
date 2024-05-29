import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lessons extends JFrame {
    private JLabel lessonLabel;
    private JButton nextWordButton;
    private JButton quizButton;
    private JButton stopButton;
    private JTextField userInputField;
    private JLabel feedbackLabel;
    private JPanel buttonPanel;
    private JPanel quizPanel;
    private CardLayout cardLayout;
    private JPanel cards;

    private Map<String, List<String>> lessons;
    private String currentCategory;
    private int currentWordIndex;

    public Lessons() {
        initUI();
        loadLessons();
    }

    private void initUI() {
        setTitle("Morse Code Lessons");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel lessonModePanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JLabel headerLabel = new JLabel("Lessons to teach you morse code");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(headerLabel);
        lessonModePanel.add(topPanel, BorderLayout.NORTH);

        lessonLabel = new JLabel("Select a category to start learning");
        lessonLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lessonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lessonModePanel.add(lessonLabel, BorderLayout.CENTER);

        nextWordButton = new JButton("Next Word");
        nextWordButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        nextWordButton.addActionListener(new NextWordButtonListener());
        lessonModePanel.add(nextWordButton, BorderLayout.SOUTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        lessonModePanel.add(buttonPanel, BorderLayout.EAST);

        // Quiz Panel
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setVisible(false);

        quizButton = new JButton("Quiz");
        quizButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        quizButton.addActionListener(new QuizButtonListener());
        quizPanel.add(quizButton);

        userInputField = new JTextField();
        userInputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        quizPanel.add(userInputField);

        feedbackLabel = new JLabel("Pending input");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quizPanel.add(feedbackLabel);

        JButton nextQuestionButton = new JButton("Next Question");
        nextQuestionButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        nextQuestionButton.addActionListener(new NextQuestionButtonListener());
        quizPanel.add(nextQuestionButton);

        stopButton = new JButton("Stop");
        stopButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stopButton.addActionListener(new StopButtonListener());
        quizPanel.add(stopButton);

        cards.add(lessonModePanel, "LessonMode");
        cards.add(quizPanel, "QuizMode");

        add(cards);



        setVisible(true);
    }

    private void loadLessons() {
        lessons = new HashMap<>();
        lessons.put("letters", List.of("a: .-", "b: -...", "c: -.-.", "d: -..", "e: .", "f: ..-.", "g: --.", "h: ...."
                , "i: ..", "j: .---", "k: -.-", "l: .-..", "m: --", "n: -.", "o: ---", "p: .--."
                , "q: --.-", "r: .-.", "s: ...", "t: -", "u: ..-", "v: ...-", "w: .--", "x: -..-"
                , "y: -.--", "z: --."));
        lessons.put("basic phrases", List.of("how are you: .... --- .-- / .- .-. . / -.-- --- ..-",
                "hello: .... . .-.. .-.. --- ",
                "goodbye: --. --- --- -.. -... -.-- . ",
                "thank you: - .... .- -. -.- / -.-- --- ..-",
                "please: .--. .-.. . .- ... . ",
                "yes: -.-- . ... ",
                "no: -. --- ",
                "sorry: ... --- .-. .-. -.--",
                "good morning: --. --- --- -.. / -- --- .-. -. .. -. --. ",
                "good afternoon: --. --- --- -.. / .- ..-. - . .-. -. --- --- -.",
                "good evening: --. --- --- -.. / . ...- . -. .. -. --. ",
                "how are you doing?: .... --- .-- / .- .-. . / -.-- --- ..- / -.. --- .. -. --. .-..",
                "nice to meet you: -. .. -.-. . / - --- / -- . . - / -.-- --- ..- ",
                "have a good day: .... .- ...- . / .- / --. --- --- -.. / -.. .- -.--",
                "see you later: ... . . / -.-- --- ..- / .-.. .- - . .-.",
                "I don't understand: .. / -.. --- -. .-..-. - / ..- -. -.. . .-. ... - .- -. -.. ",
                "can you repeat that?: -.-. .- -. / -.-- --- ..- / .-. . .--. . .- - / - .... .- -",
                "what time is it?: .-- .... .- - / - .. -- . / .. ... / .. - .-..",
                "where is the bathroom?: .-- .... . .-. . / .. ... / - .... . / -... .- - .... .-. --- --- -- .-.."));
        lessons.put("nature", List.of("tree: - .-. . . ",
                "flower: ..-. .-.. --- .-- . .-.",
                "sun: ... ..- -. ",
                "moon: -- --- --- -..",
                "mountain: -- --- ..- -. - .- .. -.",
                "river: .-. .. ...- . .-.",
                "ocean: --- -.-. . .- -.",
                "forest: ..-. --- .-. . ... - ",
                "bird: -... .. .-. -.. ",
                "sky: ... -.- -.--"));

        for (String category : lessons.keySet()) {
            JButton button = new JButton(category);
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            button.addActionListener(new CategoryButtonListener(category));
            buttonPanel.add(button);
        }
    }

    private class CategoryButtonListener implements ActionListener {
        private String category;

        public CategoryButtonListener(String category) {
            this.category = category;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentCategory = category;
            currentWordIndex = 0;
            updateLessonLabel();
        }
    }

    private class NextWordButtonListener implements ActionListener {
        @Override

    public void actionPerformed(ActionEvent e) {
        if (currentCategory != null) {
            currentWordIndex = (currentWordIndex + 1) % lessons.get(currentCategory).size();
            updateLessonLabel();
        }
    }
}

private class QuizButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(cards, "QuizMode");
        feedbackLabel.setText("Pending input");
        userInputField.setText("");
        userInputField.requestFocus();
        if (currentCategory != null) {
            currentWordIndex = 0;
            updateLessonLabel();
        }
    }
}

private class NextQuestionButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = userInputField.getText().trim();
        if (currentCategory != null && !userInput.isEmpty()) {
            String[] lessonParts = lessons.get(currentCategory).get(currentWordIndex).split(": ");
            String correctMorse = lessonParts[1];
            if (userInput.equals(correctMorse)) {
                feedbackLabel.setText("Correct!");
            } else {
                feedbackLabel.setText("Incorrect. Your input: " + userInput);
            }
            currentWordIndex = (currentWordIndex + 1) % lessons.get(currentCategory).size();
            updateLessonLabel();
        }
    }
}

private class StopButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(cards, "LessonMode");
    }
}

    private void updateLessonLabel() {
        if (currentCategory != null) {
            String lesson = lessons.get(currentCategory).get(currentWordIndex);
            lessonLabel.setText("Word: " + lesson);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lessons::new);
    }
}