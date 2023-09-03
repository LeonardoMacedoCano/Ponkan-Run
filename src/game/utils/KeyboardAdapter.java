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
            case KeyEvent.VK_ENTER -> {
                switch (getGame().getCurrentStage().getCurrentStageType()) {
                    case PLAY -> getGame().getCurrentStage().prepareStagePlaying();
                    case LOST -> getGame().getCurrentStage().prepareStagePlay();
                }
            }
            case KeyEvent.VK_UP -> {
                if (getGame().getPlayer().getRemainingJumps() > 0) getGame().getPlayer().jump();
            }
            case KeyEvent.VK_DOWN -> getGame().getPlayer().roll();
            case KeyEvent.VK_P, KeyEvent.VK_PAUSE-> {
                switch (getGame().getCurrentStage().getCurrentStageType()) {
                    case PLAYING -> getGame().getCurrentStage().pause();
                    case PAUSED -> getGame().getCurrentStage().unpause();
                }
            }
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
