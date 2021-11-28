package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Asset {
    protected int x,y;

    public abstract void update(GameContainer gameContainer, int delta);
    public abstract void render(Graphics graphics);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
