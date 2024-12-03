package game;

import weather.WeatherService;
import weather.OpenWeatherMapService;
import javax.swing.SwingUtilities;

public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherService weatherService = new OpenWeatherMapService("0b4fbd9381a0f4dee2472a7e9783c5a2", "Antarctica");
            HighScoreManager highScoreManager = HighScoreManager.getInstance();
            GameFrame gameFrame = new GameFrame(weatherService, highScoreManager);
            gameFrame.setVisible(true);
        });
    }
}