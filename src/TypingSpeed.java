import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class TypingSpeed extends JFrame {

    private JTextArea textArea;
    private JTextField typingField;
    private JLabel timerLabel;
    private JLabel wpmLabel;
    private Timer timer;
    private int timeElapsed;
    private static final String SAMPLE_TEXT = "- .... . --.- ..- .. -.-. -.-  -... .-. --- .-- -.  ..-. --- -..-  .--- ..- -- .--. ...  --- ...- . .-.  - .... .  .-.. .- --.. -.--  -.. --- --.";

    public TypingSpeed() {
        initUI();
    }

    private void initUI() {
        setTitle("Typing Speed Test");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Typing Speed Test");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        textArea = new JTextArea(SAMPLE_TEXT);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        textArea.setEditable(false);
        mainPanel.add(textArea);

        typingField = new JTextField();
        typingField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        typingField.setMaximumSize(new Dimension(600, 30));
        typingField.setAlignmentX(Component.CENTER_ALIGNMENT);
        typingField.addActionListener(new TypingFieldListener());
        mainPanel.add(typingField);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));

        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statsPanel.add(timerLabel);

        wpmLabel = new JLabel("WPM: 0");
        wpmLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statsPanel.add(wpmLabel);

        mainPanel.add(statsPanel);

        add(mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        startTimer();
    }

    private void startTimer() {
        timeElapsed = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeElapsed++;
                SwingUtilities.invokeLater(() -> timerLabel.setText("Time: " + timeElapsed + "s"));
            }
        }, 1000, 1000);
    }

    private class TypingFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String typedText = typingField.getText();
            String[] words = typedText.split("\\s+");
            int wordCount = words.length;
            double minutes = timeElapsed / 60.0;
            int wpm = (int) (wordCount / minutes);

            wpmLabel.setText("WPM: " + wpm);

            if (typedText.equals(SAMPLE_TEXT)) {
                timer.cancel();
                JOptionPane.showMessageDialog(null, "Test complete! Your WPM is: " + wpm);
            }
        }
    }
}