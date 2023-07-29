package game.object;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Object2D {
    private int x;
    private int y;
    private int height;
    private int width;
    private Image image;
    private int frame;

    public Object2D() {
        setFrame(1);
        setImage();
        setHeight();
        setWidth();
        setStartPosition();
    }

    public abstract void updateObject();

    public abstract void updateFrame();

    protected abstract void setStartPosition();

    protected abstract String getImageFrame();

    public void paintObject(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

    protected void setImage() {
        ImageIcon image = new ImageIcon(getImageFrame());
        this.image = image.getImage();
    }

    protected Image getImage() {
        return image;
    }

    private void setWidth() {
        this.width = this.image.getWidth(null);
    }

    private int getWidth() {
        return width;
    }

    private void setHeight() {
        this.height = this.image.getHeight(null);
    }

    private int getHeight() {
        return height;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getY() {
        return y;
    }

    protected void setFrame(int frame) {
        this.frame = frame;
    }

    protected int getFrame() {
        return frame;
    }
}
