package game.object;

import game.Container;
import game.utils.LibraryUtils;
import game.utils.ObstacleType;

public class ObstacleCup extends DefaultObstacle {
    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(Container.DEFAULT_WIDTH);
        setY(StageBackground.FLOOR_HEIGHT - getHeight());
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/cup.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {
        setType(ObstacleType.CUP);
    }

    @Override
    protected void afterCreateObject() {

    }
}
