package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class FrameView {

    private Stage primaryStage;
    private GraphicsContext gc;
    private int SQUARE_WIDTH, SQUARE_HEIGHT, BODY_SIZE;
    final private Random rand = new Random();
    final private static Color[] pillColors = {
            Color.OLIVE, Color.RED, Color.AQUA, Color.PURPLE,
            Color.LIGHTGREEN, Color.ORANGE, Color.YELLOW, Color.CORAL
    };
    private Scene scene;
    final public Scene getScene() { return scene; }

    public FrameView(final int ...size)
    {
        SQUARE_WIDTH = size[0];
        SQUARE_HEIGHT = size[1];
        BODY_SIZE = size[2];
    }

    final public void setPrimaryStage(final Stage ps)
    {
        primaryStage = ps;
        primaryStage.setTitle("Snake");
        VBox root = new VBox();
        Canvas canvas = new Canvas(SQUARE_WIDTH * BODY_SIZE, SQUARE_HEIGHT * BODY_SIZE);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        scene = new Scene(root,SQUARE_WIDTH * BODY_SIZE, SQUARE_HEIGHT * BODY_SIZE);
    }

    final public void showStage()
    {
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    final public void gameOver()
    {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial Black", 50));
        gc.fillText("GAME OVER", 190, 250);
    }

    final public void showPill(final Pill pill)
    {
        //background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SQUARE_WIDTH * BODY_SIZE, SQUARE_HEIGHT * BODY_SIZE);

        final int pill_color = rand.nextInt(pillColors.length);
        gc.setFill(pillColors[pill_color]);
        gc.fillOval(pill.getX() * BODY_SIZE, pill.getY() * BODY_SIZE, BODY_SIZE - 5, BODY_SIZE - 5);
    }

    final public void showSnake(final SnakeBody Snake)
    {
        for (int i = 0; i < Snake.length(); i++) {
            final int snakeX = Snake.getBodyPartX(i);
            final int snakeY = Snake.getBodyPartY(i);
            if (i == 0) {
                gc.setFill(Color.DARKGREEN);
                gc.fillOval(snakeX * BODY_SIZE, snakeY * BODY_SIZE, BODY_SIZE , BODY_SIZE);
                gc.setFill(Color.RED);
                gc.fillOval(snakeX * BODY_SIZE + 5, snakeY * BODY_SIZE + 5, BODY_SIZE - 10, BODY_SIZE - 10);
            } else {
                gc.setFill(Color.GREEN);
                gc.fillOval(snakeX * BODY_SIZE, snakeY * BODY_SIZE, BODY_SIZE - 2, BODY_SIZE - 2);
                gc.setFill(Color.YELLOW);
                gc.fillOval(snakeX * BODY_SIZE + 6, snakeY * BODY_SIZE + 6, BODY_SIZE - 12, BODY_SIZE - 12);
            }
        }
    }

    final public void showScore(final int score)
    {
        final int score_out = 10 * (score - 4);
        final Color color = (score_out > 0) ? Color.LIGHTGREEN : Color.RED;
        gc.setFill(color);
        gc.setFont(new Font("papyrus", 30));
        gc.fillText("Score: " + score_out, 10, 30);
    }
}
