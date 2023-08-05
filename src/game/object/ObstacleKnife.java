package game.object;

import game.Container;
import game.utils.LibraryUtils;

public class ObstacleKnife extends DefaultObstacle{
    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(Container.DEFAULT_WIDTH);
        setY(315);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/knife.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }
}
