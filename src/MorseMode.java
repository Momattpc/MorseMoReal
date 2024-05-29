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
        mainPanel.setBackground(UIManager.getBackgroundColor());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create and add the header label
        JLabel headerLabel = new JLabel("Welcome to MorseMode! Please click a button below to explore the functionality of this program!");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        // Create buttons for each feature
        String[] buttons = {"Translator", "Lessons", "Morse Challenge", "Listening Challenge", "Typing Speed", "Settings", "AI Tool", "Hangman"};
        for (String buttonName : buttons) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new MainButtonListener());
            mainPanel.add(button);
        }


        setContentPane(mainPanel);
        updateUIColors();
        updateUIFont();
        setBackgroundImage();
        // Add the main panel to the frame

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
                    new Lessons();
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
                case "Morse Challenge":
                    new MorseChallenge();
                    break;
                case "AI Tool":
                    try {
                        Desktop.getDesktop().browse(new java.net.URI("https://poe.com/MorseMode"));
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

    private void updateUIColors() {
        getContentPane().setBackground(UIManager.getBackgroundColor());
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JLabel || component instanceof JButton) {
                component.setForeground(UIManager.getTextColor());
            }
        }
    }

    private void updateUIFont() {
        Font newFont = new Font("SansSerif", Font.PLAIN, UIManager.getTextSize());
        for (Component component : getContentPane().getComponents()) {
            component.setFont(newFont);
        }
        revalidate();
        repaint();
    }

    private void setBackgroundImage() {
        if (UIManager.getBackgroundImage() != null) {
            JLabel backgroundLabel = new JLabel(UIManager.getBackgroundImage());
            backgroundLabel.setLayout(new FlowLayout());
            setContentPane(backgroundLabel);

        } else {
            getContentPane().setBackground(UIManager.getBackgroundColor());
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MorseMode ex = new MorseMode();
            ex.setVisible(true);
        });
    }

    public static class dummy {
    }
}