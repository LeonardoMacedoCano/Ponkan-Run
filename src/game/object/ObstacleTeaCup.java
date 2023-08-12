package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;
import game.utils.ObstacleType;

public class ObstacleTeaCup extends DefaultObstacle{
    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(PonkanRun.DEFAULT_WIDTH);
        setY(StageBackground.FLOOR_HEIGHT - getHeight());
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/teaCup.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {
        setType(ObstacleType.TEA_CUP);
    }

    @Override
    protected void afterCreateObject() {

    }
}
