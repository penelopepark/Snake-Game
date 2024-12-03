package background;

import java.awt.*;
import javax.swing.JPanel;

public class SnowyBackgroundStrategy implements BackgroundStrategy {

    @Override
    public void renderBackground(Graphics g, JPanel panel) {
        // white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        // SNOWFLAKES! (they're pretty good actually)
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * panel.getWidth());
            int y = (int) (Math.random() * panel.getHeight());
            g.fillOval(x, y, 5, 5);
        }
    }
}