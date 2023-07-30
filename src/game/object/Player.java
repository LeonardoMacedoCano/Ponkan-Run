package game.object;

import game.utils.LibraryUtils;

public class Player extends Object2D {
    private final int LAST_FRAME_STOPPED = 4;
    private int frameBase;

    @Override
    public void updateObject() {
        updateFrame();
        setImage();
    }

    @Override
    public void updateFrame() {
        if (getFrame() < (getFrameBase() * LAST_FRAME_STOPPED)) {
            setFrame(getFrame() +1);
        } else {
            setFrame(1);
        }
    }

    @Override
    protected void setStartPosition() {
        setX(40);
        setY(500);
    }

    @Override
    protected String getImageFrame() {
        String imageName = getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_STOPPED, getFrameBase(), LAST_FRAME_STOPPED);

        return String.format("%s.png", imageName);
    }

    @Override
    protected void beforeCreateObject() {
        setFrameBase(10);
    }

    @Override
    protected void afterCreateObject() {

    }

    private String getPlayerFrame(String pathImage, int frameBase, int lastFrame) {
        int frame = 0;
        int currentFrame = getFrame();

        for (int i = 1; i <= lastFrame; i++) {
            int startInterval = (i - 1) * frameBase + 1;
            int endInterval = i *  frameBase;

            if (currentFrame >= startInterval && currentFrame <= endInterval) {
                frame = i;
                break;
            }
        }
        return String.format("%s%d", pathImage, frame);
    }

    private void setFrameBase(int frameBase) {
        this.frameBase = frameBase;
    }

    private int getFrameBase() {
        return frameBase;
    }
}
