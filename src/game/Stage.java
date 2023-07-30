package game;

import game.object.DefaultInformation;
import game.object.InformationPlay;
import game.object.Player;
import game.object.StageBackground;
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
    public static final int GRAVITATIONAL_FORCE = 2;

    public Stage() {
        Timer timer = new Timer(10, this);

        background = new StageBackground();
        player = new Player();
        listInformation = new ArrayList<DefaultInformation>();

        setFocusable(true);
        setDoubleBuffered(true);

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
        player.prepareStagePlay();
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
                //TO DO
                break;
        }
    }

    private void paintListInformation(Graphics2D graphics2D) {
        DefaultInformation information;

        for (int i = 0; i < listInformation.size(); i++) {
            information = listInformation.get(i);
            graphics2D.drawImage(information.getImage(), information.getX(), information.getY(), information.getWidth(), information.getHeight(), this);
        }
    }

    private void setCurrentStageType(String currentStageType) {
        Stage.currentStageType = currentStageType;
    }

    public static String getCurrentStageType() {
        return currentStageType;
    }
}
