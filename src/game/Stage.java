package game;

import game.object.Player;
import game.object.StageBackground;
import game.utils.LibraryUtils;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stage extends JPanel implements ActionListener {
    private final StageBackground background;
    private final Player player;
    private static String currentStageType;
    public static final int GRAVITATIONAL_FORCE = 2;

    public Stage() {
        Timer timer = new Timer(10, this);

        background = new StageBackground();
        player = new Player();

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
    }

    private void setCurrentStageType(String currentStageType) {
        Stage.currentStageType = currentStageType;
    }

    public static String getCurrentStageType() {
        return currentStageType;
    }
}
