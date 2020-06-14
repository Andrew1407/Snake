package sample;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private int SQUARE_WIDTH, SQUARE_HEIGHT;
    private enum Way { UP, DOWN, LEFT, RIGHT }
    final private Map<KeyCode, Way> Keys = new HashMap<>();
    private KeyCode key = KeyCode.W;

    public Controller(final int ...size)
    {
        SQUARE_WIDTH = size[0];
        SQUARE_HEIGHT = size[1];

        //initializing moving keys
        Keys.put(KeyCode.W, Way.UP);
        Keys.put(KeyCode.S, Way.DOWN);
        Keys.put(KeyCode.A, Way.LEFT);
        Keys.put(KeyCode.D, Way.RIGHT);
        Keys.put(KeyCode.UP, Way.UP);
        Keys.put(KeyCode.DOWN, Way.DOWN);
        Keys.put(KeyCode.LEFT, Way.LEFT);
        Keys.put(KeyCode.RIGHT, Way.RIGHT);
    }

    final public void readKey(final Stage ps, final Scene scene)
    {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
            final KeyCode kc = k.getCode();
            if (kc.equals(KeyCode.ESCAPE)) ps.close();
            key = kc;
        });
    }

    final public boolean setSnakeDirection(final SnakeBody Snake)
    {
        boolean gameOver = false;
        switch (Keys.get(key)) {
            case UP:
                Snake.setBodyPartY(Snake.getBodyPartY(0) - 1, 0);
                if (Snake.getBodyPartY(0) < 0) gameOver = true;
                break;
            case DOWN:
                Snake.setBodyPartY(Snake.getBodyPartY(0) + 1, 0);
                if (Snake.getBodyPartY(0) >= SQUARE_HEIGHT) gameOver = true;
                break;
            case LEFT:
                Snake.setBodyPartX(Snake.getBodyPartX(0) - 1, 0);
                if (Snake.getBodyPartX(0) < 0) gameOver = true;
                break;
            case RIGHT:
                Snake.setBodyPartX(Snake.getBodyPartX(0) + 1, 0);
                if (Snake.getBodyPartX(0) >= SQUARE_WIDTH) gameOver = true;
                break;
            default: gameOver = true;
        }
        return gameOver;
    }
}
