package game.object.obstacle;

import game.PonkanRun;
import game.utils.LibraryUtils;
import game.utils.ObstacleType;

public class ObstacleTeaCup extends DefaultObstacle {
    public ObstacleTeaCup(PonkanRun game) {
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
        setX(this.game.DEFAULT_WIDTH);
        setY(this.game.currentStage.background.FLOOR_HEIGHT - getHeight());
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
