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
        boolean validPosition = false;
        while (!validPosition) {
            appleX = (int)(Math.random()*(PANEL_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            appleY = (int)(Math.random()*(PANEL_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            validPosition = true;
            // we make sure apple does not spawn on snake
            for (int i = 0; i < bodyParts; ++i) {
                if (x[i] == appleX && y[i] == appleY) {
                    validPosition = false;
                    break;
                }
            }
        }
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
        if (running) {
            // apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // snake
            for (int i = 0; i < bodyParts; ++i) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            String scoreText = "Score: "+applesEaten;
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString(scoreText, (PANEL_WIDTH-metrics.stringWidth(scoreText))/2, g.getFont().getSize());
        } else {
            showGameOver(g);
        }
    }
    // displays the game over screen with final score as well as highscores
    private void showGameOver(Graphics g) {
        //"Game Over" text
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        String gameOverText = "Game Over";
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString(gameOverText, (PANEL_WIDTH-metrics1.stringWidth(gameOverText))/2, (PANEL_HEIGHT/2)-50);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        String scoreText = "Score: " + applesEaten;
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(scoreText, (PANEL_WIDTH-metrics2.stringWidth(scoreText))/2, PANEL_HEIGHT/2);

        // high scores
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String highScoresText = "High Scores:";
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString(highScoresText, (PANEL_WIDTH-metrics3.stringWidth(highScoresText))/2, (PANEL_HEIGHT/2) + 50);

        // retrieve + display high scores
        java.util.List<Integer> highScores = highScoreManager.getHighScores();
        for (int i = 0; i < highScores.size(); ++i) {
            String hs = (i+1)+". "+highScores.get(i);
            g.drawString(hs, (PANEL_WIDTH-metrics3.stringWidth(hs))/2, (PANEL_HEIGHT/2)+80+(i*30));
        }
        // restart
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String restartText = "Press ENTER to Restart!";
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString(restartText, (PANEL_WIDTH-metrics4.stringWidth(restartText))/2, (PANEL_HEIGHT/2)+200);
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

