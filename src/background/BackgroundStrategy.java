package background;

import java.awt.*;
import javax.swing.JPanel;

public interface BackgroundStrategy {
    void renderBackground(Graphics g, JPanel panel);
}