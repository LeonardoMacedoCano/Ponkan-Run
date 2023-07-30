package game.object;

import game.Container;
import game.utils.LibraryUtils;

public class InformationPlay extends DefaultInformation {

    @Override
    protected String getImageFrame() {
        return String.format("%s/panelPlay.png", LibraryUtils.PATH_IMG_INFORMATION);
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
}
