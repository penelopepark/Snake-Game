package game;

import weather.WeatherService;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(WeatherService weatherService, HighScoreManager highScoreManager) {
        setTitle("Snake Game!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel(weatherService, highScoreManager));
        pack();
        setLocationRelativeTo(null);
    }
}
