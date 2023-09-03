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
    private final PonkanRun game;
    private static Timer timer;
    public final EnvironmentBackground background;
    private final List<DefaultInformation> listInformation;
    private final List<DefaultObstacle> listObstacle;
    private LibraryUtils.StageType currentStageType;
    private int currentScore;
    private int currentVelocity;
    private int millisUntilNextObstacle;
    private boolean active;
    private int minDistBetweenObstacles;
    public final int GRAVITATIONAL_FORCE = 2;
    public final int INITIAL_VELOCITY = 8;
    public final int INITIAL_MIN_DIST_OBSTACLES = 70;

    public static class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Stage.timer.stop();
            System.gc();
        }
    }

    public Stage(PonkanRun game) {
        this.game = game;

        timer = new Timer(2000, new Listener());
        background = new EnvironmentBackground(game);
        listInformation = new ArrayList<>();
        listObstacle = new ArrayList<>();

        setActive(false);
    }

    public void paint(Graphics2D graphics2D) {
        background.paint(graphics2D);
        paintListInformation(graphics2D);
        paintListObstacle(graphics2D);
    }

    @Override
    public void update() {
        background.update();
        updateListInformation();

        if (getCurrentStageType().equals(LibraryUtils.StageType.PLAYING)) {
            updateListObstacle();
            checkCollisionInListObstacle();
        }
    }

    public void start() {
        setActive(true);
        timer.start();
        prepareStagePlay();
    }

    public void prepareStagePlay() {
        setCurrentStageType(LibraryUtils.StageType.PLAY);
        setCurrentScore(0);
        setCurrentVelocity(0);
        setMillisUntilNextObstacle(0);
        setMinDistBetweenObstacles(0);
        this.game.getPlayer().prepareStagePlay();
        listObstacle.clear();
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
        listInformation.clear();

        switch (getCurrentStageType()) {
            case PLAY -> listInformation.add(new InformationPlay(game));
            case LOST -> listInformation.add(new InformationLost(game));
            default -> {
                listInformation.add(new InformationLives(game));
                listInformation.add(new InformationScore(game));
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

        for (DefaultObstacle defaultObstacle : listObstacle) {
            if ((defaultObstacle.getX() + defaultObstacle.getWidth() > 0)) {
                defaultObstacle.setX(defaultObstacle.getX() - getCurrentVelocity());
            } else {
                setCurrentScore(getCurrentScore() + 1);
                obstaclesToRemove.add(defaultObstacle);
            }
        }

        listObstacle.removeAll(obstaclesToRemove);
    }

    private void addObstacle() {
        int randomObstacleType = (int)Math.floor(LibraryUtils.ObstacleType.getTotalObstacleType() * Math.random());

        switch (LibraryUtils.ObstacleType.values()[randomObstacleType]) {
            case CUP -> listObstacle.add(new ObstacleCup(game));
            case TEA_CUP -> listObstacle.add(new ObstacleTeaCup(game));
            case KNIFE -> listObstacle.add(new ObstacleKnife(game));
        }
    }

    private void paintListInformation(Graphics2D graphics2D) {
        DefaultInformation information;

        for (DefaultInformation defaultInformation : listInformation) {
            information = defaultInformation;
            graphics2D.drawImage(information.getImage(), information.getX(), information.getY(), information.getWidth(), information.getHeight(), this.game);

            if (information.isUsedTextBox()) {
                information.paintTextBox(graphics2D);
            }
        }
    }
    private void paintListObstacle(Graphics2D graphics2D) {
        DefaultObstacle obstacle;

        for (DefaultObstacle defaultObstacle : listObstacle) {
            obstacle = defaultObstacle;
            graphics2D.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight(), this.game);
        }
    }

    private void checkCollisionInListObstacle() {
        List<DefaultObstacle> obstaclesToRemove = new ArrayList<>();

        for (DefaultObstacle defaultObstacle : listObstacle) {
            if (LibraryUtils.checkCollisionBetweenObjects2D(this.game.getPlayer(), defaultObstacle)) {
                obstaclesToRemove.add(defaultObstacle);
                this.game.getPlayer().setCurrentTotalLives(this.game.getPlayer().getCurrentTotalLives() - 1);

                if (this.game.getPlayer().getCurrentTotalLives() <= 0) {
                    prepareStageLost();
                }
            }
        }

        listObstacle.removeAll(obstaclesToRemove);
    }

    private void setRandomMillisUntilNextObstacle() {
        setMillisUntilNextObstacle(getMinDistBetweenObstacles() + (int)Math.floor(21 * Math.random()));
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

    private void setCurrentVelocity(int currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    private int getCurrentVelocity() {
        return currentVelocity;
    }

    public boolean isActive() {
        return active;
    }

    protected void setActive (boolean active) {
        this.active = active;
    }
}
