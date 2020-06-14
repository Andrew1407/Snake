package sample;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Main extends Application {
    //Values
    private static boolean gameOver = false;
    final private static int SQUARE_WIDTH = 30;
    final private static int SQUARE_HEIGHT = 20;
    final private static int BODY_SIZE = 25;

    //interactive objects
    final private static Controller controller = new Controller(SQUARE_WIDTH, SQUARE_HEIGHT);
    final private static SnakeBody Snake = new SnakeBody(SQUARE_WIDTH, SQUARE_HEIGHT);
    final private static Pill pill = new Pill(SQUARE_WIDTH, SQUARE_HEIGHT);
    final private static FrameView frame = new FrameView(SQUARE_WIDTH, SQUARE_HEIGHT, BODY_SIZE);

    // MAIN (launch)
    public static void main(String[] args) { launch(); }

    //Visualization
    public void start(Stage primaryStage) {
        pill.Spawn(Snake);
        frame.setPrimaryStage(primaryStage);            //window generation

        //timer (actions per frame)
        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick();
                    return;
                }
                if (now - lastTick > 1_000_000_000 / pill.getFPS()) {
                    lastTick = now;
                    tick();
                }
            }
        }.start();

        controller.readKey(primaryStage, frame.getScene());         //Snake moving control
        frame.showStage();                                          //show window
    }

    public static void tick() {
        if (gameOver) {
            frame.gameOver();
            return;
        }

        Snake.changePosition();                                 //Snake moves
        gameOver = controller.setSnakeDirection(Snake);         //set next snake direction with key

        if (gameOver) {
            frame.gameOver();
            return;
        }

        pill.isEaten(Snake);                    //if the snake eats the pill
        gameOver = Snake.deadEnd();             //dead end (self-eating case)

        frame.showPill(pill);                   //pill drawing
        frame.showSnake(Snake);                 //snake drawing
        frame.showScore(pill.getFPS());         //current score drawing
    }
}