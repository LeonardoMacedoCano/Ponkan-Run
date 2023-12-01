package game.object.obstacle;

import game.PonkanRun;
import game.utils.LibraryUtils;

public class ObstacleCup extends DefaultObstacle {
    public ObstacleCup(PonkanRun game) {
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
        setX(getGame().getScreenWidth());
        setY(getGame().getCurrentStage().getGroundPosition() - getHeight());
    }

    @Override
    protected String getImageFrame() {
        return String.format("%scup.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }
}
