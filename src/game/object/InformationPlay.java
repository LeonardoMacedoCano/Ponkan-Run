package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;

import java.awt.Graphics2D;

public class InformationPlay extends DefaultInformation {

    public InformationPlay(PonkanRun game) {
        super(game);
    }

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
        setX((PonkanRun.DEFAULT_WIDTH - getWidth()) / 2);
        setY((PonkanRun.DEFAULT_HEIGHT - getHeight()) / 2);
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
