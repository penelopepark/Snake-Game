import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener, KeyListener, WeatherObserver {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (PANEL_WIDTH * PANEL_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int INITIAL_DELAY = 200;
    private final int x[] = new int[GAME_UNITS]; //X coords of snake body
    private final int y[] = new int[GAME_UNITS]; //Y coords of snake body
    private int bodyParts = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;

    private char direction = 'R'; //'U' (up), 'D' (down), 'L' (left), 'R' (right)
    private boolean running = false;
    private javax.swing.Timer timer;

    private final WeatherService weatherService;
    private BackgroundStrategy backgroundStrategy;
    private final HighScoreManager highScoreManager;
    private final Timer weatherTimer;
    private static final long WEATHER_UPDATE_INTERVAL = 10 * 60 * 1000; //weather timer updates every 10 min...

    /**
     * GamePanel(weatherService, highScoreManager) constructs the game panel with various functionalities
     *
     * @param weatherService   service to fetch weather data
     * @param highScoreManager manager for high scores
     */
    public GamePanel(WeatherService weatherService, HighScoreManager highScoreManager) {
        this.weatherService = weatherService;
        this.highScoreManager = highScoreManager;

        //observer to receive weather updates
        this.weatherService.registerObserver(this);

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        startGame();
        this.backgroundStrategy = new DefaultBackgroundStrategy();

        weatherTimer = new Timer();
        weatherTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                weatherService.fetchWeather();
            }
        }, WEATHER_UPDATE_INTERVAL, WEATHER_UPDATE_INTERVAL);
    }

    // initializes game variables and starts the game loop.
    public void startGame() {
    }
    // spawns apple at random position not occupied by snake
    public void spawnApple() {
    }
    // paints the game components on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundStrategy.renderBackground(g, this);
        drawGameElements(g);
    }
    // draws the snake, apple, score on panel
    private void drawGameElements(Graphics g) {
    }
    // displays the game over screen with final score as well as highscores
    private void showGameOver(Graphics g) {

    }
    // updates game state on each tick of timer
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    // moves the snake in curr direction
    public void move() {

    }
    // checks if snake has eaten the apple
    public void checkApple() {

    }
    // verifies collisions with walls or snake's own body
    public void checkCollisions() {

    }
    // handle weather update received from WeatherService (weather is the new weather object)
    public void updateWeather(Weather weather) {

    }
    // adjust settings based on current weather conditions
    private void adjustGameBasedOnWeather(Weather weather) {

    }
    // event handler for key pressing
    public void keyPressed(KeyEvent e) {

    }
    // reset the game to initial state for new session after user loses
    private void resetGame() {

    }

    // required for KeyListener interface
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}