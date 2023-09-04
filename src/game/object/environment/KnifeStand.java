package game.object.environment;

import game.PonkanRun;
import game.object.Object2D;
import game.utils.LibraryUtils;

public class KnifeStand extends Object2D {
    public KnifeStand(PonkanRun game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(getGame().getScreenEdge().left);
        setY(330);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_ENVIRONMENT_KNIFE_STAND, getFrame());
    }

    @Override
    protected void beforeCreateObject() {
        setFrame(1);
    }

    @Override
    protected void afterCreateObject() {

    }
}
