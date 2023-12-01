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
    protected void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX(getGame().getScreenWidth());
        setY(getGame().getCurrentStage().getKnifeStand().getY() + getGame().getCurrentStage().getKnifeStand().getHeight());
    }

    @Override
    protected String getImageFrame() {
        return String.format("%sknife.png", LibraryUtils.PATH_IMG_OBSTACLE);
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }
}
