package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;
import game.utils.ObstacleType;

public class ObstacleCup extends DefaultObstacle {
    public ObstacleCup(PonkanRun game) {
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
        setX(this.game.DEFAULT_WIDTH);
        setY(this.game.currentStage.background.FLOOR_HEIGHT - getHeight());
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
