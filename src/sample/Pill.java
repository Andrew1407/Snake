package sample;

import java.util.Random;

public class Pill {

    final private Random rand = new Random();
    private int SQUARE_WIDTH, SQUARE_HEIGHT;
    private int FPS = 3;                        //speed of snake movement
    private int x, y;                           //pill cords

    final public int getFPS() { return FPS; }
    final public int getX() { return x; }
    final public int getY() { return y; }

    public Pill(final int ...size)
    {
        SQUARE_WIDTH = size[0];
        SQUARE_HEIGHT = size[1];
    }

    final public void Spawn(final SnakeBody Snake)
    {
        boolean go_to_start = false;
        while (true) {
            x = rand.nextInt(SQUARE_WIDTH);
            y = rand.nextInt(SQUARE_HEIGHT);

            for (int i = 0; i < Snake.length(); i++) {
                final int snakeX = Snake.getBodyPartX(i);
                final int snakeY = Snake.getBodyPartY(i);
                if(snakeX == x && snakeY == y) {
                    go_to_start = true;
                    break;
                }
            }
            if (go_to_start) continue;
            //else increase speed (after cords generating)
            FPS++;
            break;
        }
    }

    final public void isEaten(final SnakeBody Snake)
    {
        final int headX = Snake.getBodyPartX(0);
        final int headY = Snake.getBodyPartY(0);
        if (x == headX && y == headY) {
            Snake.addNewPart();
            Spawn(Snake);
        }
    }
}
