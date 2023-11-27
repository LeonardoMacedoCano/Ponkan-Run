package game.object.character;

import game.PonkanRun;
import game.object.Object2D;
import game.object.item.Leaf;
import game.object.obstacle.DefaultObstacle;
import game.utils.LibraryUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Object2D {
    private int frameBase;
    private boolean isRolling;
    private int velocity;
    private int currentTotalLives;
    private int currentTotalLeaf;
    private int remainingJumps;
    private List<Leaf> listLeaf;
    private final int LAST_FRAME_JUMPING = 3;
    private final int LAST_FRAME_STOPPED = 4;
    private final int LAST_FRAME_ROLLING = 4;
    private final int LAST_FRAME_WALKING = 8;
    private final int MAX_LIVES = 3;
    private final int MAX_LEAF = 3;
    private final int MAX_JUMPS = 2;

    public Player(PonkanRun game) {
        super(game);
        setListLeaf(new ArrayList<>());
    }

    @Override
    public void update() {
        updateVelocity();
        updateFrame();
        setImage();
        setHeight();
        setWidth();
        updateYCoordinate();
    }

    @Override
    protected void updateFrame() {
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
        if (LibraryUtils.StageType.PLAY.equals(getGame().getCurrentStage().getCurrentStageType())) {
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
        setCurrentTotalLeaf(MAX_LEAF);
    }

    @Override
    protected void afterCreateObject() {
        setRemainingJumps(MAX_JUMPS);
        setRolling(false);
    }

    public void paintPlayerItems(Graphics2D graphics2D) {
        paintListLeaf(graphics2D);
    }

    private void paintListLeaf(Graphics2D graphics2D) {
        for (Leaf leaf : getListLeaf()) {
            graphics2D.drawImage(leaf.getImage(), leaf.getX(), leaf.getY(), leaf.getWidth(), leaf.getHeight(), getGame());
        }
    }

    public void updatePlayerItems() {
        updateListLeaf();
    }

    private void updateListLeaf() {
        for (Leaf leaf : getListLeaf()) {
            leaf.setX(leaf.getX() + getGame().getCurrentStage().getCurrentVelocity());
            leaf.update();
        }
    }

    public boolean checkObstacleCollisionWithListItem(DefaultObstacle obstacle) {
        boolean isCollision = false;
        List<Leaf> leafToRemove = new ArrayList<>();

        for (Leaf leaf : getListLeaf()) {
            if (LibraryUtils.checkCollisionBetweenObjects2D(leaf, obstacle)) {
                leafToRemove.add(leaf);
                isCollision = true;
            } else {
                leaf.setX(leaf.getX() + getGame().getCurrentStage().getCurrentVelocity());
            }
        }

        getListLeaf().removeAll(leafToRemove);
        return isCollision;
    }

    public void prepareStagePlay() {
        setFrame(1);
        setFrameBase(10);
        setVelocity(0);
        setCurrentTotalLives(MAX_LIVES);
        setCurrentTotalLeaf(MAX_LEAF);
        getListLeaf().clear();
    }

    public void throwLeaf() {
        if (getCurrentTotalLeaf() > 0) {
            getListLeaf().add(new Leaf(getGame()));
            setCurrentTotalLeaf(getCurrentTotalLeaf() -1);
        }
    }

    public void jump() {
        if (getRemainingJumps() > 0) {
            int FORCE_JUMP = 28;

            setVelocity(-FORCE_JUMP);
            setRemainingJumps(getRemainingJumps() -1);
        }
    }

    public void roll() {
        setRolling(true);
    }

    public void getUp() {
        setRolling(false);
    }

    public void loseLife()  {
        setCurrentTotalLives(getCurrentTotalLives() - 1);
    }

    private void updateVelocity() {
        setVelocity(getVelocity() + getGame().getCurrentStage().GRAVITATIONAL_FORCE);
    }

    private void updateYCoordinate() {
        if (isJumping()) {
            setY(getY() + getVelocity());
        } else {
            setPlayerOnTheFloor();
            setRemainingJumps(MAX_JUMPS);
        }
    }

    private boolean isJumping() {
        return ((getY() + getVelocity()) < (getGame().getCurrentStage().getGroundPosition() - getHeight()));
    }

    private void setPlayerOnTheFloor() {
        setY(getGame().getCurrentStage().getGroundPosition() - getHeight());
    }

    private int getLastFrame() {
        if (LibraryUtils.StageType.PLAY.equals(getGame().getCurrentStage().getCurrentStageType())) {
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

    private void setCurrentTotalLives(int currentTotalLives) {
        this.currentTotalLives = currentTotalLives;
    }

    public int getCurrentTotalLives() {
        return currentTotalLives;
    }

    private void setCurrentTotalLeaf(int currentTotalLeaf) {
        this.currentTotalLeaf = currentTotalLeaf;
    }

    public int getCurrentTotalLeaf() {
        return currentTotalLeaf;
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

    private void setListLeaf(List<Leaf> listLeaf) {
        this.listLeaf = listLeaf;
    }

    private List<Leaf> getListLeaf() {
        return listLeaf;
    }
}