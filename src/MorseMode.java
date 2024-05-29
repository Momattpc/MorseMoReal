import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MorseMode extends JFrame {

    public MorseMode() {
        initUI();
    }

    // Initialize the main user interface
    private void initUI() {
        // Set the layout and default close operation
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the main panel with a white background
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create and add the header label
        JLabel headerLabel = new JLabel("Welcome to MorseMode! Please click a button below to explore the functionality of this program!");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        // Create buttons for each feature
        String[] buttons = {"Translator", "Lessons", "Listening Challenge", "Typing Speed", "Settings", "AI Tool", "Hangman"};
        for (String buttonName : buttons) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new MainButtonListener());
            mainPanel.add(button);
        }

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setTitle("MorseMode");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    // Action listener for the main buttons
    private class MainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Translator":
                    new Translator();
                    break;
                case "Lessons":
                    new LessonsPage();
                    break;
                case "Listening Challenge":
                    new ListeningChallenge();
                    break;
                case "Typing Speed":
                    new TypingSpeed();
                    break;
                case "Settings":
                    new Settings();
                    break;
                case "AI Tool":
                    try {
                        Desktop.getDesktop().browse(new java.net.URI("http://placeholder-link.com"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Hangman":
                    new Hangman();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MorseMode ex = new MorseMode();
            ex.setVisible(true);
        });
    }
}