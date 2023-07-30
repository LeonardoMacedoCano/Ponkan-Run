package game.object;

import game.utils.LibraryUtils;

public class StageBackground extends Object2D{
    private final int LAST_FRAME = 34;

    @Override
    public void updateObject() {
        updateFrame();
        setImage();
    }

    @Override
    public void updateFrame() {
        if (getFrame() < LAST_FRAME) {
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
}
