package game;

import java.io.*;
import java.util.*;

// manages the high scores using  Singleton pattern
public class HighScoreManager {

    private static HighScoreManager instance;
    private final List<Integer> highScores;
    private static final String HIGH_SCORE_FILE = "highscores.dat";

    // private constructor for singleton pattern
    private HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    // retrieves the "SINGLE" instance of highscore manager
    public static synchronized HighScoreManager getInstance() {
        if (instance == null) {
            instance = new HighScoreManager();
        }
        return instance;
    }

    // add score to the list and save it
    public void addScore(int score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        // save only the top 5 :)
        if (highScores.size() > 5) {
            highScores.remove(highScores.size() - 1);
        }
        saveHighScores();
    }

    // getter for the highscores
    public List<Integer> getHighScores() {
        return new ArrayList<>(highScores);
    }
    // loads the highscores from file (this file will be SAVED AND RETRIEVED in the same directory as this file)
    public void loadHighScores() {
        highScores.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                List<?> loadedScores = (List<?>) obj;
                for (Object score : loadedScores) {
                    if (score instanceof Integer) {
                        highScores.add((Integer) score);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // no highscores yet?!?!
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // save the highscores to a file called highscores.dat (DEFINED ABOVE)
    public void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}