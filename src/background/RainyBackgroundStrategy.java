package background;

import java.awt.*;
import javax.swing.JPanel;

public class RainyBackgroundStrategy implements BackgroundStrategy {

    @Override
    public void renderBackground(Graphics g, JPanel panel) {
        //dark grey background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        // raindrops (i tried)
        g.setColor(Color.CYAN);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * panel.getWidth());
            int y = (int) (Math.random() * panel.getHeight());
            g.drawLine(x, y, x, y + 10);
        }
    }
}