package game.object;

import game.utils.LibraryUtils;

public class InformationLives extends DefaultInformation {
    @Override
    protected String getImageFrame() {
        return String.format("%s/panelLives%d.png", LibraryUtils.PATH_IMG_INFORMATION, Player.getCurrentTotalLives());
    }

    @Override
    protected void setStartPosition() {
        setX(0);
        setY(10);
    }

    @Override
    protected void beforeCreateObject() {
        setUsedTextBox(false);
    }
}
