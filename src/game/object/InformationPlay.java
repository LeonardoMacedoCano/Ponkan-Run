package game.object;

import game.Container;
import game.utils.LibraryUtils;

import java.awt.*;

public class InformationPlay extends DefaultInformation {

    @Override
    protected String getImageFrame() {
        return String.format("%s/panelPlay.png", LibraryUtils.PATH_IMG_INFORMATION);
    }

    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX((Container.DEFAULT_WIDTH - getWidth()) / 2);
        setY((Container.DEFAULT_HEIGHT - getHeight()) / 2);
    }

    @Override
    protected void beforeCreateObject() {
        setUsedTextBox(false);
    }

    @Override
    protected void afterCreateObject() {

    }

    @Override
    protected void createTextBox(Graphics2D graphics2D) {

    }
}
