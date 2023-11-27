package game.utils;

import game.PonkanRun;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {
    private PonkanRun game;

    public KeyboardAdapter(PonkanRun game) {
        setGame(game);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                getGame().getCurrentStage().alterCurrentStage();
                break;
            case KeyEvent.VK_UP:
                getGame().getPlayer().jump();
                break;
            case KeyEvent.VK_DOWN:
                getGame().getPlayer().roll();
                break;
            case KeyEvent.VK_P, KeyEvent.VK_PAUSE:
                getGame().getCurrentStage().controlPause();
                break;
            case KeyEvent.VK_F:
                getGame().getPlayer().throwLeaf();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) getGame().getPlayer().getUp();
    }

    private void setGame(PonkanRun game) {
        this.game = game;
    }

    private PonkanRun getGame() {
        return game;
    }
}
