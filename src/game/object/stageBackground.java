package game.object;

import game.utils.LibraryUtils;

public class stageBackground extends Object2D{

    @Override
    public void updateObject() {
        setImage();
    }

    @Override
    protected void setStartPosition() {
        setX(0);
        setY(0);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_STAGE_BG, 1);
    }
}
