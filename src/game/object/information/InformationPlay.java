package game.object.information;

import game.PonkanRun;
import game.utils.LibraryUtils;

import java.awt.Graphics2D;

public class InformationPlay extends DefaultInformation {

    public InformationPlay(PonkanRun game) {
        super(game);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%spanelPlay.png", LibraryUtils.PATH_IMG_INFORMATION);
    }

    @Override
    public void update() {

    }

    @Override
    protected void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX((getGame().getScreenWidth() - getWidth()) / 2);
        setY((getGame().getScreenHeight() - getHeight()) / 2);
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
