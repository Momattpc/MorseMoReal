import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Settings extends JFrame {
    private JComboBox<String> bgColorComboBox;
    private JComboBox<String> textColorComboBox;
    private JSlider textSizeSlider;
    private JButton uploadImageButton;
    private JButton homeButton;

    public Settings() {
        initUI();
    }

    private void initUI() {
        setTitle("Settings");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Background color combo box
        JLabel bgColorLabel = new JLabel("Background Color:");
        bgColorLabel.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        panel.add(bgColorLabel);

        bgColorComboBox = new JComboBox<>(new String[]{"Red", "Blue", "Green", "Grey", "White"});
        bgColorComboBox.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        bgColorComboBox.addActionListener(new BgColorActionListener());
        panel.add(bgColorComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Text color combo box
        JLabel textColorLabel = new JLabel("Text Color:");
        textColorLabel.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        panel.add(textColorLabel);

        textColorComboBox = new JComboBox<>(new String[]{"Red", "Blue", "Green", "Grey", "Black"});
        textColorComboBox.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        textColorComboBox.addActionListener(new TextColorActionListener());
        panel.add(textColorComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Text size slider
        JLabel textSizeLabel = new JLabel("Text Size:");
        textSizeLabel.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        panel.add(textSizeLabel);

        textSizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 30, UIManager.getTextSize());
        textSizeSlider.setMajorTickSpacing(10);
        textSizeSlider.setMinorTickSpacing(1);
        textSizeSlider.setPaintTicks(true);
        textSizeSlider.setPaintLabels(true);
        textSizeSlider.addChangeListener(e -> updateTextSize());
        panel.add(textSizeSlider);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Upload image button
        uploadImageButton = new JButton("Upload Background Image");
        uploadImageButton.setFont(new Font("SansSerif", Font.PLAIN, UIManager.getTextSize()));
        uploadImageButton.addActionListener(new UploadImageButtonListener());
        panel.add(uploadImageButton);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Home button
        homeButton = new JButton("Home");
        homeButton.setFont(new Font("SansSerif", Font.PLAIN,UIManager.getTextSize()));
        homeButton.addActionListener(e -> goHome());
        panel.add(homeButton);

        // Set initial UI settings
        updateUIColors();
        updateUIFont();
        setBackgroundImage();

        setContentPane(panel);
        setVisible(true);
    }

    private void updateBackgroundColor() {
        String selectedColor = (String) bgColorComboBox.getSelectedItem();
        switch (selectedColor) {
            case "Red":
                UIManager.setBackgroundColor(Color.RED);
                break;
            case "Blue":
                UIManager.setBackgroundColor(Color.BLUE);
                break;
            case "Green":
                UIManager.setBackgroundColor(Color.GREEN);
                break;
            case "Grey":
                UIManager.setBackgroundColor(Color.GRAY);
                break;
            case "White":
                UIManager.setBackgroundColor(Color.WHITE);
                break;
        }
        updateUIColors();
    }

    private void updateTextColor() {
        String selectedColor = (String) textColorComboBox.getSelectedItem();
        switch (selectedColor) {
            case "Red":
                UIManager.setTextColor(Color.RED);
                break;
            case "Blue":
                UIManager.setTextColor(Color.BLUE);
                break;
            case "Green":
                UIManager.setTextColor(Color.GREEN);
                break;
            case "Grey":
                UIManager.setTextColor(Color.GRAY);
                break;
            case "Black":
                UIManager.setTextColor(Color.BLACK);
                break;
        }
        updateUIColors();
    }

    private void updateTextSize() {
        UIManager.setTextSize(textSizeSlider.getValue());
        updateUIFont();
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

    private class BgColorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateBackgroundColor();
        }
    }

    private class TextColorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTextColor();
        }
    }

    private class UploadImageButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(Settings.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                UIManager.setBackgroundImage(new ImageIcon(selectedFile.getAbsolutePath()));
                setBackgroundImage();
            }
        }
    }

    private void setBackgroundImage() {
        if (UIManager.getBackgroundImage() != null) {
            JLabel backgroundLabel = new JLabel(UIManager.getBackgroundImage());
            backgroundLabel.setLayout(new FlowLayout());
            setContentPane(backgroundLabel);
            // Add components back to the new content pane
            backgroundLabel.add(homeButton);
            backgroundLabel.add(bgColorComboBox);
            backgroundLabel.add(textColorComboBox);
            backgroundLabel.add(textSizeSlider);
            backgroundLabel.add(uploadImageButton);
        } else {
            getContentPane().setBackground(UIManager.getBackgroundColor());
        }
        revalidate();
        repaint();
    }

    private void goHome() {
        homeButton.addActionListener(e -> {
            this.dispose();
            new MorseMode().setVisible(true);


        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Settings::new);
    }
}