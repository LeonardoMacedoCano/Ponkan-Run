package game.object;

import game.Stage;
import game.utils.LibraryUtils;

public class StageBackground extends Object2D {
    private final int LAST_FRAME = 34;
    public static final int FLOOR_HEIGHT = 665;

    @Override
    public void updateObject() {
        updateFrame();
        setImage();
    }

    @Override
    public void updateFrame() {
        if ((Stage.getCurrentStageType() == LibraryUtils.StageType.PLAYING) && (getFrame() < LAST_FRAME)) {
            setFrame(getFrame() +1);
        } else {
            setFrame(1);
        }
    }

    @Override
    protected void setStartPosition() {
        setX(0);
        setY(0);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_STAGE_BG, getFrame());
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }
}
