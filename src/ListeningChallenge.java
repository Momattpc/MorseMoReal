import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ListeningChallenge extends JFrame {

    private JLabel instructionLabel;
    private JTextField answerInput;
    private JLabel feedbackLabel;
    private String currentMorseCode;
    private String currentAnswer;

    // Morse code mappings
    private static final Map<Integer, String> numberFileMap = new HashMap<>();
    private static final Map<Integer, String> fileAnswerMap = new HashMap<>();

    static {

        numberFileMap.put(1, ("file1.txt"));
        numberFileMap.put(2, ("file2.txt"));
        numberFileMap.put(3, ("file3.txt"));
        numberFileMap.put(4, ("file4.txt"));
        numberFileMap.put(5, ("file5.txt"));

        // need to add some words here
        fileAnswerMap.put(1, (""));
        fileAnswerMap.put(2, ("file2.txt"));
        fileAnswerMap.put(3, ("file3.txt"));
        fileAnswerMap.put(4, ("file4.txt"));
        fileAnswerMap.put(5, ("file5.txt"));
        // ...


    }



    public ListeningChallenge() {
        initUI();
    }

    private void initUI() {
        setTitle("Listening Challenge");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Listening Challenge");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        instructionLabel = new JLabel("Listen to the Morse code and type the translation:");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(instructionLabel);

        answerInput = new JTextField();
        answerInput.setFont(new Font("SansSerif", Font.PLAIN, 18));
        answerInput.setMaximumSize(new Dimension(400, 30));
        answerInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(answerInput);

        feedbackLabel = new JLabel("");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(feedbackLabel);

        JPanel buttonPanel = new JPanel();
        JButton playMorseButton = new JButton("Play Morse Code");
        playMorseButton.addActionListener(new PlayMorseButtonListener());
        buttonPanel.add(playMorseButton);

        JButton checkAnswerButton = new JButton("Check Answer");
        checkAnswerButton.addActionListener(new CheckAnswerButtonListener());
        buttonPanel.add(checkAnswerButton);

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

    private class PlayMorseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] morseWords = {".-.. . .- .-. -. .. -. --.", ".--. .-. .- -.-. - .. -.-. ."};
            //need a separate hashmap here
            currentMorseCode = morseWords[(int) (Math.random() * morseWords.length)];
            currentAnswer = Translator.translateMorseToEnglish(currentMorseCode);
            playMorseCode();
        }

        private void playMorseCode() {
            Random random = new Random();

            playSound(numberFileMap.get(random));
            JOptionPane.showMessageDialog(null, "Playing Morse Code");
        }


    }

    private class CheckAnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userAnswer = answerInput.getText().trim().toUpperCase();
            if (userAnswer.equals(currentAnswer)) {
                feedbackLabel.setText("Correct!");
            } else {
                feedbackLabel.setText("Incorrect. The correct answer was: " + currentAnswer);
            }
        }
    }
    private void playSound(String filePath) {
        try {
            // Load the sound file
            File soundFile = new File(filePath);
            Clip clip = AudioSystem.getClip();

            // Open the audio input stream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(audioInputStream);

            // Start playing the sound
            clip.start();

            // Wait for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Clean up resources
            clip.close();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}