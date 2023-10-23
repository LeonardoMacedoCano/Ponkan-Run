package game.object.environment;

import game.PonkanRun;
import game.object.Object2D;
import game.utils.LibraryUtils;

public class EnvironmentFloor extends Object2D {
    public EnvironmentFloor(PonkanRun game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    protected void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(getGame().getScreenEdge().left);
        setY(getGame().getScreenHeight() - getHeight());
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_ENVIRONMENT_FLOOR, getFrame());
    }

    @Override
    protected void beforeCreateObject() {
        setFrame(1);
    }

    @Override
    protected void afterCreateObject() {

    }
}
