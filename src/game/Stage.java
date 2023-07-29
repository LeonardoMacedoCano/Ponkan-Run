package game;

import game.object.stageBackground;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stage extends JPanel implements ActionListener {
    private Timer timer;
    private stageBackground background;

    public Stage() {
        timer = new Timer(10, this);
        background = new stageBackground();

        setFocusable(true);
        setDoubleBuffered(true);

        timer.start();
        running();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        background.paintObject(graphics2D);
        graphics2D.dispose();
        super.paint(g);
    }

    private void running() {
        update();
        repaint();
    }

    private void update() {
        background.updateObject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        running();
    }
}
