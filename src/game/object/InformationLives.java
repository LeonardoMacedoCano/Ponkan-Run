package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;

import java.awt.Graphics2D;

public class InformationLives extends DefaultInformation {
    public InformationLives(PonkanRun game) {
        super(game);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/panelLives%d.png", LibraryUtils.PATH_IMG_INFORMATION, Player.getCurrentTotalLives());
    }

    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

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

    @Override
    protected void afterCreateObject() {

    }

    @Override
    protected void createTextBox(Graphics2D graphics2D) {

    }
}
