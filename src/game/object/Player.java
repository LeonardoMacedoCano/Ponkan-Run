package game.object;

import game.Stage;
import game.utils.LibraryUtils;

public class Player extends Object2D {
    private final int LAST_FRAME_STOPPED = 4;
    private final int LAST_FRAME_WALKING = 8;
    private final int MAX_LIVES = 3;
    private final int MAX_JUMPS = 2;
    private static final int FORCE_JUMP = 28;
    private int frameBase;
    private static int velocity;
    private static int currentTotalLives;
    private static int remainingJumps;

    @Override
    public void updateObject() {
        updateVelocity();
        updateFrame();
        setImage();
        updateYCoordinate();
    }

    @Override
    public void updateFrame() {
        int lastFrame = (LibraryUtils.StageType.PLAY.equals(Stage.getCurrentStageType())) ? LAST_FRAME_STOPPED : LAST_FRAME_WALKING;

        if (getFrame() < (getFrameBase() * lastFrame)) {
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
        if (LibraryUtils.StageType.PLAY.equals(Stage.getCurrentStageType())) {
            return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_STOPPED, getFrameBase(), LAST_FRAME_STOPPED));
        } else {
            return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_WALKING, getFrameBase(), LAST_FRAME_WALKING));
        }
    }

    @Override
    protected void beforeCreateObject() {
        setFrameBase(10);
        setCurrentTotalLives(MAX_LIVES);
    }

    @Override
    protected void afterCreateObject() {
        setRemainingJumps(MAX_JUMPS);
    }

    public void prepareStagePlay() {
        setFrame(1);
        setFrameBase(10);
        setVelocity(0);
        setCurrentTotalLives(MAX_LIVES);
    }

    private void updateVelocity() {
        setVelocity(getVelocity() + Stage.GRAVITATIONAL_FORCE);
    }

    private void updateYCoordinate() {
        if (isJump()) {
            setY(getY() + getVelocity());
        } else {
            setPlayerOnTheFloor();
            setRemainingJumps(MAX_JUMPS);
        }
    }

    public static void jump() {
        setVelocity(-FORCE_JUMP);
        setRemainingJumps(getRemainingJumps() -1);
    }

    private boolean isJump() {
        return ((getY() + getVelocity()) < (StageBackground.FLOOR_HEIGTH - getHeight()));
    }

    private void setPlayerOnTheFloor() {
        setY(StageBackground.FLOOR_HEIGTH - getHeight());
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

    private static void setVelocity(int velocity) {
        Player.velocity = velocity;
    }

    private int getVelocity() {
        return velocity;
    }

    private void setCurrentTotalLives(int currentTotalLives) {
        Player.currentTotalLives = currentTotalLives;
    }

    protected static int getCurrentTotalLives() {
        return currentTotalLives;
    }

    private static void setRemainingJumps(int remainingJumps) {
        Player.remainingJumps = remainingJumps;
    }

    public static int getRemainingJumps() {
        return remainingJumps;
    }
}
