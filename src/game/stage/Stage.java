package game.stage;

import game.PonkanRun;
import game.object.environment.*;
import game.object.information.*;
import game.object.obstacle.*;
import game.utils.*;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Stage implements Animation {
    private PonkanRun game;
    private static Timer timer;
    private EnvironmentBackground environmentBackground;
    private EnvironmentFloor environmentFloor;
    private List<DefaultInformation> listInformation;
    private List<DefaultObstacle> listObstacle;
    private LibraryUtils.StageType currentStageType;
    private int currentScore;
    private int currentVelocity;
    private int millisUntilNextObstacle;
    private int minDistBetweenObstacles;
    private boolean active;
    public final int GRAVITATIONAL_FORCE = 2;
    public final int INITIAL_VELOCITY = 8;
    public final int INITIAL_MIN_DIST_OBSTACLES = 70;

    public static class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Stage.getTimer().stop();
            System.gc();
        }
    }

    public Stage(PonkanRun game) {
        setGame(game);
        setTimer(new Timer(2000, new Listener()));
        setEnvironmentBackground(new EnvironmentBackground(getGame()));
        setEnvironmentFloor(new EnvironmentFloor(getGame()));
        setListInformation(new ArrayList<>());
        setListObstacle(new ArrayList<>());
        setActive(false);
    }

    public void paint(Graphics2D graphics2D) {
        getEnvironmentBackground().paint(graphics2D);
        getEnvironmentFloor().paint(graphics2D);
        paintListInformation(graphics2D);
        paintListObstacle(graphics2D);
    }

    @Override
    public void update() {
        getEnvironmentBackground().update();
        getEnvironmentFloor().update();
        updateListInformation();

        if (getCurrentStageType().equals(LibraryUtils.StageType.PLAYING)) {
            updateListObstacle();
            checkCollisionInListObstacle();
        }
    }

    public void start() {
        setActive(true);
        getTimer().start();
        prepareStagePlay();
    }

    public void prepareStagePlay() {
        setCurrentStageType(LibraryUtils.StageType.PLAY);
        setCurrentScore(0);
        setCurrentVelocity(0);
        setMillisUntilNextObstacle(0);
        setMinDistBetweenObstacles(0);
        getGame().getPlayer().prepareStagePlay();
        getListObstacle().clear();
    }

    public void prepareStagePlaying() {
        setCurrentStageType(LibraryUtils.StageType.PLAYING);
        setCurrentVelocity(INITIAL_VELOCITY);
        setMinDistBetweenObstacles(INITIAL_MIN_DIST_OBSTACLES);
    }

    public void pause() {
        setCurrentStageType(LibraryUtils.StageType.PAUSED);
    }

    public void unpause() {
        setCurrentStageType(LibraryUtils.StageType.PLAYING);
    }

    public void prepareStageLost() {
        setCurrentStageType(LibraryUtils.StageType.LOST);
    }

    private void updateListInformation() {
        getListInformation().clear();

        switch (getCurrentStageType()) {
            case PLAY -> getListInformation().add(new InformationPlay(getGame()));
            case LOST -> getListInformation().add(new InformationLost(getGame()));
            default -> {
                getListInformation().add(new InformationLives(getGame()));
                getListInformation().add(new InformationScore(getGame()));
            }
        }
    }

    private void updateListObstacle() {
        List<DefaultObstacle> obstaclesToRemove = new ArrayList<>();

        if (getMillisUntilNextObstacle() > 0) {
            setMillisUntilNextObstacle(getMillisUntilNextObstacle() - 1);
        } else {
            addObstacle();
            setRandomMillisUntilNextObstacle();
        }

        for (DefaultObstacle defaultObstacle : getListObstacle()) {
            if ((defaultObstacle.getX() + defaultObstacle.getWidth() > 0)) {
                defaultObstacle.setX(defaultObstacle.getX() - getCurrentVelocity());
            } else {
                setCurrentScore(getCurrentScore() + 1);
                obstaclesToRemove.add(defaultObstacle);
            }
        }

        getListObstacle().removeAll(obstaclesToRemove);
    }

    private void addObstacle() {
        int randomObstacleType = (int)Math.floor(LibraryUtils.ObstacleType.getTotalObstacleType() * Math.random());

        switch (LibraryUtils.ObstacleType.values()[randomObstacleType]) {
            case CUP -> getListObstacle().add(new ObstacleCup(getGame()));
            case TEA_CUP -> getListObstacle().add(new ObstacleTeaCup(getGame()));
            case KNIFE -> getListObstacle().add(new ObstacleKnife(getGame()));
        }
    }

    private void paintListInformation(Graphics2D graphics2D) {
        DefaultInformation information;

        for (DefaultInformation defaultInformation : getListInformation()) {
            information = defaultInformation;
            graphics2D.drawImage(information.getImage(), information.getX(), information.getY(), information.getWidth(), information.getHeight(), getGame());

            if (information.isUsedTextBox()) {
                information.paintTextBox(graphics2D);
            }
        }
    }
    private void paintListObstacle(Graphics2D graphics2D) {
        DefaultObstacle obstacle;

        for (DefaultObstacle defaultObstacle : getListObstacle()) {
            obstacle = defaultObstacle;
            graphics2D.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight(), getGame());
        }
    }

    private void checkCollisionInListObstacle() {
        List<DefaultObstacle> obstaclesToRemove = new ArrayList<>();

        for (DefaultObstacle defaultObstacle : getListObstacle()) {
            if (LibraryUtils.checkCollisionBetweenObjects2D(getGame().getPlayer(), defaultObstacle)) {
                obstaclesToRemove.add(defaultObstacle);
                getGame().getPlayer().setCurrentTotalLives(getGame().getPlayer().getCurrentTotalLives() - 1);

                if (getGame().getPlayer().getCurrentTotalLives() <= 0) {
                    prepareStageLost();
                }
            }
        }

        getListObstacle().removeAll(obstaclesToRemove);
    }

    private void setRandomMillisUntilNextObstacle() {
        setMillisUntilNextObstacle(getMinDistBetweenObstacles() + (int)Math.floor(21 * Math.random()));
    }

    public int getGroundPosition() {
        return getEnvironmentFloor().getY() + 10;
    }

    private void setGame(PonkanRun game) {
        this.game = game;
    }

    private PonkanRun getGame() {
        return game;
    }

    private void setTimer(Timer timer) {
        Stage.timer = timer;
    }

    private static Timer getTimer() {
        return timer;
    }

    private void setEnvironmentBackground(EnvironmentBackground environmentBackground) {
        this.environmentBackground = environmentBackground;
    }

    public EnvironmentBackground getEnvironmentBackground() {
        return environmentBackground;
    }

    private void setEnvironmentFloor(EnvironmentFloor environmentFloor) {
        this.environmentFloor = environmentFloor;
    }

    private EnvironmentFloor getEnvironmentFloor() {
        return environmentFloor;
    }

    private void setListInformation(List<DefaultInformation> listInformation) {
        this.listInformation = listInformation;
    }

    private List<DefaultInformation> getListInformation() {
        return listInformation;
    }

    private void setListObstacle(List<DefaultObstacle> listObstacle) {
        this.listObstacle = listObstacle;
    }

    private List<DefaultObstacle> getListObstacle() {
        return listObstacle;
    }

    private void setCurrentStageType(LibraryUtils.StageType currentStageType) {
        this.currentStageType = currentStageType;
    }

    public LibraryUtils.StageType getCurrentStageType() {
        return currentStageType;
    }

    private void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    private void setCurrentVelocity(int currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    private int getCurrentVelocity() {
        return currentVelocity;
    }

    private void setMillisUntilNextObstacle(int millisUntilNextObstacle) {
        this.millisUntilNextObstacle = millisUntilNextObstacle;
    }

    private int getMillisUntilNextObstacle() {
        return millisUntilNextObstacle;
    }

    private void setMinDistBetweenObstacles(int minDistBetweenObstacles) {
        this.minDistBetweenObstacles = minDistBetweenObstacles;
    }

    private int getMinDistBetweenObstacles() {
        return minDistBetweenObstacles;
    }

    public boolean getActive() {
        return active;
    }

    private void setActive (boolean active) {
        this.active = active;
    }
}
