package game.object;

import game.PonkanRun;
import game.utils.LibraryUtils;

public class Player extends Object2D {
    private int frameBase;
    private boolean isRolling;
    private int velocity;
    private int currentTotalLives;
    private int remainingJumps;
    private final int LAST_FRAME_JUMPING = 3;
    private final int LAST_FRAME_STOPPED = 4;
    private final int LAST_FRAME_ROLLING = 4;
    private final int LAST_FRAME_WALKING = 8;
    private final int MAX_LIVES = 3;
    private final int MAX_JUMPS = 2;

    public Player(PonkanRun game) {
        super(game);
    }

    @Override
    public void updateObject() {
        updateVelocity();
        updateFrame();
        setImage();
        setHeight();
        setWidth();
        updateYCoordinate();
    }

    @Override
    public void updateFrame() {
        if (getFrame() < (getFrameBase() * getLastFrame())) {
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
        if (LibraryUtils.StageType.PLAY.equals(this.game.currentStage.getCurrentStageType())) {
            return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_STOPPED, getFrameBase(), LAST_FRAME_STOPPED));
        } else {
            if (isJumping() && getRemainingJumps() == 1) {
                return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_JUMPING, getFrameBase(), LAST_FRAME_JUMPING));
            } else if (isRolling() || isJumping()) {
                return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_ROLLING, getFrameBase(), LAST_FRAME_ROLLING));
            } else {
                return String.format("%s.png", getPlayerFrame(LibraryUtils.PATH_IMG_PLAYER_WALKING, getFrameBase(), LAST_FRAME_WALKING));
            }
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
        setRolling(false);
    }

    public void prepareStagePlay() {
        setFrame(1);
        setFrameBase(10);
        setVelocity(0);
        setCurrentTotalLives(MAX_LIVES);
    }

    private void updateVelocity() {
        setVelocity(getVelocity() + this.game.currentStage.GRAVITATIONAL_FORCE);
    }

    private void updateYCoordinate() {
        if (isJumping()) {
            setY(getY() + getVelocity());
        } else {
            setPlayerOnTheFloor();
            setRemainingJumps(MAX_JUMPS);
        }
    }

    public void jump() {
        int FORCE_JUMP = 28;
        setVelocity(-FORCE_JUMP);
        setRemainingJumps(getRemainingJumps() -1);
    }

    private boolean isJumping() {
        return ((getY() + getVelocity()) < (this.game.currentStage.background.FLOOR_HEIGHT - getHeight()));
    }

    public void roll() {
        setRolling(true);
    }

    public void getUp() {
        setRolling(false);
    }

    private void setPlayerOnTheFloor() {
        setY(this.game.currentStage.background.FLOOR_HEIGHT - getHeight());
    }

    private int getLastFrame() {
        if (LibraryUtils.StageType.PLAY.equals(this.game.currentStage.getCurrentStageType())) {
            return LAST_FRAME_STOPPED;
        } else if (isJumping() && getRemainingJumps() == 1){
            return LAST_FRAME_JUMPING;
        } else if (isRolling() || isJumping()) {
            return LAST_FRAME_ROLLING;
        } else {
            return LAST_FRAME_WALKING;
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

    public void setCurrentTotalLives(int currentTotalLives) {
        this.currentTotalLives = currentTotalLives;
    }

    public int getCurrentTotalLives() {
        return currentTotalLives;
    }

    private void setRemainingJumps(int remainingJumps) {
        this.remainingJumps = remainingJumps;
    }

    public int getRemainingJumps() {
        return remainingJumps;
    }

    private void setRolling(boolean isRolling) {
        this.isRolling = isRolling;
    }

    private boolean isRolling() {
        return isRolling;
    }
}
