package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeBody {

    final private List<BodyPart> Snake = new ArrayList<>();
    final public int length() { return Snake.size(); }

    public SnakeBody(final int ...randoms)
    {
        //first (head) snake element initializing
        final Random rand = new Random();
        final int headX = rand.nextInt(randoms[0]);
        final int headY =  rand.nextInt(randoms[1]);
        final BodyPart HEAD = new BodyPart(headX, headY);
        Snake.add(HEAD);
    }

    final public void addNewPart()
    {
        final BodyPart tail = new BodyPart(-1, -1);
        Snake.add(tail);
    }

    final public void changePosition()
    {
        for (int i = Snake.size() - 1; i >= 1; i--) {
            Snake.get(i).x = Snake.get(i - 1).x;
            Snake.get(i).y = Snake.get(i - 1).y;
        }
    }

    final public boolean deadEnd()
    {
        final int headX = Snake.get(0).x;
        final int headY = Snake.get(0).y;
        for (int i = 1; i < Snake.size(); i++)
            if (headX == Snake.get(i).x && headY == Snake.get(i).y) return true;
        return false;
    }

    //inner anemic class for each body part
    final private static class BodyPart {
        private int x, y;
        public BodyPart(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final public int getBodyPartX(final int index) { return Snake.get(index).x; }
    final public int getBodyPartY(final int index) { return Snake.get(index).y; }
    final public void setBodyPartX(final int value, final int index) { Snake.get(index).x = value; }
    final public void setBodyPartY(final int value, final int index) { Snake.get(index).y = value; }
}
