import java.awt.*;
import javax.swing.JPanel;

public class SunnyBackgroundStrategy implements BackgroundStrategy {
    @Override
    public void renderBackground(Graphics g, JPanel panel) {
        // skyblue
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        g.setColor(Color.yellow);
        g.fillOval(panel.getWidth() - 100, 50, 80, 80);
    }
}