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
        beforeCreateObject();
        
        setFrame(1);
        setImage();
        setHeight();
        setWidth();
        setStartPosition();

        afterCreateObject();
    }

    public abstract void updateObject();

    public abstract void updateFrame();

    protected abstract void setStartPosition();

    protected abstract String getImageFrame();

    protected abstract void beforeCreateObject();

    protected abstract void afterCreateObject();

    public void paintObject(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

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

    protected void setX(int x) {
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
