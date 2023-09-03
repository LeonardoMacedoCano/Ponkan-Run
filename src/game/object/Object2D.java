package game.object;

import game.PonkanRun;
import game.utils.Animation;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Object2D implements Animation {
    private int x;
    private int y;
    private int height;
    private int width;
    private Image image;
    private int frame;
    private PonkanRun game;

    public Object2D(PonkanRun game) {
        beforeCreateObject();

        setGame(game);
        setFrame(1);
        setImage();
        setHeight();
        setWidth();
        setStartPosition();

        afterCreateObject();
    }

    public abstract void update();

    public void paint(Graphics2D graphics2D){
        graphics2D.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), getGame());
    }

    public abstract void updateFrame();

    protected abstract void setStartPosition();

    protected abstract String getImageFrame();

    protected abstract void beforeCreateObject();

    protected abstract void afterCreateObject();

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    protected void setHeight() {
        this.height = this.image.getHeight(null);
    }

    public int getHeight() {
        return height;
    }

    protected void setWidth() {
        this.width = this.image.getWidth(null);
    }

    public int getWidth() {
        return width;
    }

    protected void setImage() {
        ImageIcon image = new ImageIcon(getImageFrame());
        this.image = image.getImage();
    }

    public Image getImage() {
        return image;
    }

    protected void setFrame(int frame) {
        this.frame = frame;
    }

    protected int getFrame() {
        return frame;
    }

    public void setGame(PonkanRun game) {
        this.game = game;
    }

    public PonkanRun getGame() {
        return game;
    }
}
