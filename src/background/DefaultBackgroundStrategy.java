package background;

import java.awt.*;
import javax.swing.JPanel;

public class DefaultBackgroundStrategy implements BackgroundStrategy {
    @Override
    public void renderBackground(Graphics g, JPanel panel) {
        // default background, nothin special :)
        g.setColor(Color.white);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }
}