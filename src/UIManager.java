import javax.swing.*;
import java.awt.*;

public class UIManager {
    private static Color backgroundColor = Color.WHITE;
    private static Color textColor = Color.BLACK;
    private static int textSize = 14;
    private static ImageIcon backgroundImage = null;

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color bgColor) {
        backgroundColor = bgColor;
    }

    public static Color getTextColor() {
        return textColor;
    }

    public static void setTextColor(Color color) {
        textColor = color;
    }

    public static int getTextSize() {
        return textSize;
    }

    public static void setTextSize(int size) {
        textSize = size;
    }

    public static ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public static void setBackgroundImage(ImageIcon image) {
        backgroundImage = image;
    }
}
