package game.object.information;

import game.PonkanRun;
import game.utils.LibraryUtils;

import java.awt.*;

public class InformationLeaf extends DefaultInformation {

    public InformationLeaf(PonkanRun game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX((getGame().getScreenWidth() - getGame().getScreenEdge().left) - getWidth());
        setY(getGame().getScreenEdge().top + 10);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%spanelLeaf%d.png", LibraryUtils.PATH_IMG_INFORMATION, getGame().getPlayer().getCurrentTotalLeaf());
    }

    @Override
    protected void afterCreateObject() {

    }

    @Override
    protected void createTextBox(Graphics2D graphics2D) {

    }
}
