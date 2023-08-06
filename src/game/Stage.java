package game;

import game.object.*;
import game.utils.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Stage extends JPanel implements ActionListener {
    private final StageBackground background;
    private final Player player;
    private final List<DefaultInformation> listInformation;
    private final List<DefaultObstacle> listObstacle;
    private static String currentStageType;
    private static int currentScore;
    private static int currentVelocity;
    private int millisUntilNextObstacle;
    private static int minDistBetweenObstacles;
    public static final int GRAVITATIONAL_FORCE = 2;
    public static final int INITIAL_VELOCITY = 8;
    public static final int INITIAL_MIN_DIST_OBSTACLES = 70;

    public Stage() {
        Timer timer = new Timer(10, this);

        background = new StageBackground();
        player = new Player();
        listInformation = new ArrayList<DefaultInformation>();
        listObstacle = new ArrayList<DefaultObstacle>();

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new KeyboardAdapter());

        timer.start();
        prepareStagePlay();
        running();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        background.paintObject(graphics2D);
        paintListInformation(graphics2D);
        paintListObstacle(graphics2D);
        player.paintObject(graphics2D);

        graphics2D.dispose();
        super.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        running();
    }

    private void prepareStagePlay() {
        setCurrentStageType(LibraryUtils.StageType.PLAY);
        setCurrentScore(0);
        setCurrentVelocity(0);
        setMillisUntilNextObstacle(0);
        setMinDistBetweenObstacles(0);
        player.prepareStagePlay();
        listObstacle.clear();
    }

    public static void prepareStagePlaying() {
        setCurrentStageType(LibraryUtils.StageType.PLAYING);
        setCurrentVelocity(INITIAL_VELOCITY);
        setMinDistBetweenObstacles(INITIAL_MIN_DIST_OBSTACLES);
    }

    private void running() {
        update();
        repaint();
    }

    private void update() {
        background.updateObject();
        player.updateObject();
        updateListInformation();

        if (getCurrentStageType().equals(LibraryUtils.StageType.PLAYING)) {
            updateListObstacle();
            checkCollisionInListObstacle();
        }
    }

    private void updateListInformation() {
        listInformation.clear();

        switch (getCurrentStageType()) {
            case LibraryUtils.StageType.PLAY -> listInformation.add(new InformationPlay());
            default -> {
                listInformation.add(new InformationLives());
                listInformation.add(new InformationScore());
            }
        }
    }

    private void updateListObstacle() {
        DefaultObstacle obstacle;

        if (getMillisUntilNextObstacle() > 0) {
            setMillisUntilNextObstacle(getMillisUntilNextObstacle() - 1);
        } else {
            addObstacle();
            setRandomMillisUntilNextObstacle();
        }

        for (int i = 0; i < listObstacle.size(); i++) {
            obstacle = listObstacle.get(i);

            if ((obstacle.getX() +  obstacle.getWidth() > 0)) {
                obstacle.setX(obstacle.getX() - getCurrentVelocity());
            } else {
                setCurrentScore(getCurrentScore() + 1);
                listObstacle.remove(i);
            }
        }
    }

    private void addObstacle() {
        int randomObstacleType = (int)Math.floor(ObstacleType.getTotalObstacleType() * Math.random());

        switch (randomObstacleType) {
            case ObstacleType.CUP -> listObstacle.add(new ObstacleCup());
            case ObstacleType.TEA_CUP -> listObstacle.add(new ObstacleTeaCup());
            case ObstacleType.KNIFE -> listObstacle.add(new ObstacleKnife());
        }
    }

    private void paintListInformation(Graphics2D graphics2D) {
        DefaultInformation information;

        for (DefaultInformation defaultInformation : listInformation) {
            information = defaultInformation;
            graphics2D.drawImage(information.getImage(), information.getX(), information.getY(), information.getWidth(), information.getHeight(), this);

            if (information.isUsedTextBox()) {
                information.paintTextBox(graphics2D);
            }
        }
    }
    private void paintListObstacle(Graphics2D graphics2D) {
        DefaultObstacle obstacle;

        for (DefaultObstacle defaultObstacle : listObstacle) {
            obstacle = defaultObstacle;
            graphics2D.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight(), this);
        }
    }

    private void checkCollisionInListObstacle() {
        DefaultObstacle obstacle;

        for (int i = 0; i < listObstacle.size(); i++) {
            obstacle = listObstacle.get(i);

            if (LibraryUtils.checkCollisionBetweenObjects2D(player, obstacle)) {
                listObstacle.remove(i);
                player.setCurrentTotalLives(player.getCurrentTotalLives() - 1);
            }
        }
    }

    private void setRandomMillisUntilNextObstacle() {
        setMillisUntilNextObstacle(getMinDistBetweenObstacles() + (int)Math.floor(21 * Math.random()));
    }

    private static void setCurrentStageType(String currentStageType) {
        Stage.currentStageType = currentStageType;
    }

    public static String getCurrentStageType() {
        return currentStageType;
    }

    private static void setCurrentScore(int currentScore) {
        Stage.currentScore = currentScore;
    }

    public static int getCurrentScore() {
        return currentScore;
    }

    private void setMillisUntilNextObstacle(int millisUntilNextObstacle) {
        this.millisUntilNextObstacle = millisUntilNextObstacle;
    }

    private int getMillisUntilNextObstacle() {
        return millisUntilNextObstacle;
    }

    private static void setMinDistBetweenObstacles(int minDistBetweenObstacles) {
        Stage.minDistBetweenObstacles = minDistBetweenObstacles;
    }

    private int getMinDistBetweenObstacles() {
        return minDistBetweenObstacles;
    }

    private static void setCurrentVelocity(int currentVelocity) {
        Stage.currentVelocity = currentVelocity;
    }

    private int getCurrentVelocity() {
        return currentVelocity;
    }
}
