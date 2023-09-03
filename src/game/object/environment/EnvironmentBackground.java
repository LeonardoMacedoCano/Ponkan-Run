package game.object.environment;

import game.PonkanRun;
import game.object.Object2D;
import game.utils.LibraryUtils;

import java.util.Objects;

public class EnvironmentBackground extends Object2D {
    public final int FLOOR_HEIGHT = 665;

    public EnvironmentBackground(PonkanRun game) {
        super(game);
    }

    @Override
    public void update() {
        updateFrame();
        setImage();
    }

    @Override
    public void updateFrame() {
        int LAST_FRAME = 34;
        if ((Objects.equals(this.game.getCurrentStage().getCurrentStageType(), LibraryUtils.StageType.PLAYING)) && (getFrame() < LAST_FRAME)) {
            setFrame(getFrame() +1);
        } else {
            setFrame(1);
        }
    }

    @Override
    protected void setStartPosition() {
        setX(game.getScreenEdge().left);
        setY(game.getScreenEdge().top);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_ENVIRONMENT_BG, getFrame());
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }
}
