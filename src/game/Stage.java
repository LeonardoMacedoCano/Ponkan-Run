package game;

import game.object.*;
import game.utils.KeyboardAdapter;
import game.utils.LibraryUtils;

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
    private static String currentStageType;
    private static int currentScore;
    public static final int GRAVITATIONAL_FORCE = 2;

    public Stage() {
        Timer timer = new Timer(10, this);

        background = new StageBackground();
        player = new Player();
        listInformation = new ArrayList<DefaultInformation>();

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
        player.prepareStagePlay();
    }

    public static void prepareStagePlaying() {
        setCurrentStageType(LibraryUtils.StageType.PLAYING);
    }

    private void running() {
        update();
        repaint();
    }

    private void update() {
        background.updateObject();
        player.updateObject();
        updateListInformation();
    }

    private void updateListInformation() {
        listInformation.clear();

        switch (getCurrentStageType()) {
            case LibraryUtils.StageType.PLAY:
                listInformation.add(new InformationPlay());
                break;
            default:
                listInformation.add(new InformationLives());
                listInformation.add(new InformationScore());
                break;
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
}
