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

    public Object2D() {
        setImage();
        setHeight();
        setWidth();
        setStartPosition();
    }

    public abstract void updateObject();

    protected abstract void setStartPosition();

    protected abstract String getImageFrame();

    public void paintObject(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

    protected void setImage() {
        ImageIcon image = new ImageIcon(getImageFrame());
        this.image = image.getImage();
    }

    private Image getImage() {
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

    private int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    private int getY() {
        return y;
    }
}
