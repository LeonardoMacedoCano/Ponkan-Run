package game.object;

import game.Stage;
import game.utils.LibraryUtils;

public class Player extends Object2D {
    private final int LAST_FRAME_STOPPED = 4;
    private int frameBase;
    private int velocity;

    @Override
    public void updateObject() {
        updateVelocity();
        updateFrame();
        setImage();
        updateYCoordinate();
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

    public void prepareStagePlay() {
        setFrame(1);
        setFrameBase(10);
        setVelocity(0);
    }

    private void updateVelocity() {
        setVelocity(getVelocity() + Stage.GRAVITATIONAL_FORCE);
    }

    private void updateYCoordinate() {
        if ((getY() + getVelocity()) > (StageBackground.FLOOR_HEIGTH - getHeight())) {
            setY(StageBackground.FLOOR_HEIGTH - getHeight());
        } else {
            setY(getY() + getVelocity());
        }
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

    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    private int getVelocity() {
        return velocity;
    }
}
