package game.object;

import game.PonkanRun;
import game.utils.Animation;

import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Object2D implements Animation {
    private int x;
    private int y;
    private int height;
    private int width;
    private Image image;
    private int frame;
    protected PonkanRun game;

    public Object2D(PonkanRun game) {
        this.game = game;

        beforeCreateObject();
        setFrame(1);
        setImage();
        setHeight();
        setWidth();
        setStartPosition();
        afterCreateObject();
    }

    public abstract void update();

    public void paint(Graphics2D graphics2D){
        graphics2D.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.game);
    }

    public abstract void updateFrame();

    protected abstract void setStartPosition();

    protected abstract String getImageFrame();

    protected abstract void beforeCreateObject();

    protected abstract void afterCreateObject();

    protected void setImage() {
        ImageIcon image = new ImageIcon(getImageFrame());
        this.image = image.getImage();
    }

    public Image getImage() {
        return image;
    }

    protected void setWidth() {
        this.width = this.image.getWidth(null);
    }

    public int getWidth() {
        return width;
    }

    protected void setHeight() {
        this.height = this.image.getHeight(null);
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    protected void setFrame(int frame) {
        this.frame = frame;
    }

    protected int getFrame() {
        return frame;
    }
}
