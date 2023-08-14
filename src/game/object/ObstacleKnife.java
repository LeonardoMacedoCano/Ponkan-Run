package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;
import game.utils.ObstacleType;

public class ObstacleKnife extends DefaultObstacle{
    public ObstacleKnife(PonkanRun game) {
        super(game);
    }

    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(PonkanRun.DEFAULT_WIDTH);
        setY(315);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/knife.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {
        setType(ObstacleType.KNIFE);
    }

    @Override
    protected void afterCreateObject() {

    }
}
