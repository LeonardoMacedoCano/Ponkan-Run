package game.object.obstacle;

import game.PonkanRun;
import game.utils.LibraryUtils;

public class ObstacleKnife extends DefaultObstacle {
    public ObstacleKnife(PonkanRun game) {
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
        setX(getGame().getScreenWidth());
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
