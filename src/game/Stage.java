package game;

import game.object.Player;
import game.object.StageBackground;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stage extends JPanel implements ActionListener {
    private Timer timer;
    private StageBackground background;
    private Player player;

    public Stage() {
        timer = new Timer(10, this);
        background = new StageBackground();
        player = new Player();

        setFocusable(true);
        setDoubleBuffered(true);

        timer.start();
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

    private void running() {
        update();
        repaint();
    }

    private void update() {
        background.updateObject();
        player.updateObject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        running();
    }
}
