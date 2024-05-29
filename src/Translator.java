import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class Translator extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;

    // Morse code mappings
    private static final HashMap<Character, String> ENGLISH_TO_MORSE = new HashMap<>();
    private static final HashMap<String, Character> MORSE_TO_ENGLISH = new HashMap<>();

    static {
        ENGLISH_TO_MORSE.put('A', ".-");
        ENGLISH_TO_MORSE.put('B', "-...");
        ENGLISH_TO_MORSE.put('C', "-.-.");
        ENGLISH_TO_MORSE.put('D', "-..");
        ENGLISH_TO_MORSE.put('E', ".");
        ENGLISH_TO_MORSE.put('F', "..-.");
        ENGLISH_TO_MORSE.put('G', "--.");
        ENGLISH_TO_MORSE.put('H', "....");
        ENGLISH_TO_MORSE.put('I', "..");
        ENGLISH_TO_MORSE.put('J', ".---");
        ENGLISH_TO_MORSE.put('K', "-.-");
        ENGLISH_TO_MORSE.put('L', ".-..");
        ENGLISH_TO_MORSE.put('M', "--");
        ENGLISH_TO_MORSE.put('N', "-.");
        ENGLISH_TO_MORSE.put('O', "---");
        ENGLISH_TO_MORSE.put('P', ".--.");
        ENGLISH_TO_MORSE.put('Q', "--.-");
        ENGLISH_TO_MORSE.put('R', ".-.");
        ENGLISH_TO_MORSE.put('S', "...");
        ENGLISH_TO_MORSE.put('T', "-");
        ENGLISH_TO_MORSE.put('U', "..-");
        ENGLISH_TO_MORSE.put('V', "...-");
        ENGLISH_TO_MORSE.put('W', ".--");
        ENGLISH_TO_MORSE.put('X', "-..-");
        ENGLISH_TO_MORSE.put('Y', "-.--");
        ENGLISH_TO_MORSE.put('Z', "--..");

        for (Character key : ENGLISH_TO_MORSE.keySet()) {
            MORSE_TO_ENGLISH.put(ENGLISH_TO_MORSE.get(key), key);
        }
    }

    // Translate Morse code to English
    public static String translateMorseToEnglish(String morse) {
        StringBuilder englishText = new StringBuilder();
        for (String morseChar : morse.split(" ")) {
            String englishChar = String.valueOf(MORSE_TO_ENGLISH.get(morseChar));
            if (englishChar != null) {
                englishText.append(englishChar);
            } else {
                englishText.append("||");
            }
        }
        return englishText.toString();
    }

    // Translate English to Morse code
    public static String translateEnglishToMorse(String english) {
        StringBuilder morseText = new StringBuilder();
        for (char c : english.toUpperCase().toCharArray()) {
            String morseChar = ENGLISH_TO_MORSE.get((c));
            if (morseChar != null) {
                morseText.append(morseChar).append(" ");
            } else {
                morseText.append("||");
            }
        }
        return morseText.toString().trim();
    }


    public Translator() {
        initUI();
    }

    private void initUI() {
        setTitle("Translator");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Translate back and forth between Morse and English.");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        JLabel subheaderLabel = new JLabel("Notice: Spaces in the translation are indicated with '||' and during input, make sure to place spaces in between your words / morse. Enjoy");
        subheaderLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        subheaderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subheaderLabel);


        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        textPanel.add(new JScrollPane(inputArea));
        textPanel.add(new JScrollPane(outputArea));
        mainPanel.add(textPanel);

        JPanel buttonPanel = new JPanel();
        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(new TranslateButtonListener());
        buttonPanel.add(translateButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> outputArea.setText(""));
        buttonPanel.add(resetButton);

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> {
            this.dispose();
            new MorseMode().setVisible(true);
        });
        buttonPanel.add(homeButton);

        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Action listener for the Translate button
    private class TranslateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputArea.getText().trim();
            if (inputText.isEmpty()) {
                outputArea.setText("");
                return;
            }

            if (isMorseCode(inputText)) {
                outputArea.setText(translateMorseToEnglish(inputText));
            } else {
                outputArea.setText(translateEnglishToMorse(inputText));
            }
        }

        // Check if the input string is Morse code
        private boolean isMorseCode(String text) {
            return text.matches("[\\.\\- ]+");
        }


    }
}