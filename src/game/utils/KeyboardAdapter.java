package game.utils;

import game.PonkanRun;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {
    private final PonkanRun game;

    public KeyboardAdapter(PonkanRun game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> {
                switch (this.game.getCurrentStage().getCurrentStageType()) {
                    case PLAY -> this.game.getCurrentStage().prepareStagePlaying();
                    case LOST -> this.game.getCurrentStage().prepareStagePlay();
                }
            }
            case KeyEvent.VK_UP -> {
                if (this.game.getPlayer().getRemainingJumps() > 0) this.game.getPlayer().jump();
            }
            case KeyEvent.VK_DOWN -> this.game.getPlayer().roll();
            case KeyEvent.VK_P, KeyEvent.VK_PAUSE-> {
                switch (this.game.getCurrentStage().getCurrentStageType()) {
                    case PLAYING -> this.game.getCurrentStage().pause();
                    case PAUSED -> this.game.getCurrentStage().unpause();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) this.game.getPlayer().getUp();
    }
}
