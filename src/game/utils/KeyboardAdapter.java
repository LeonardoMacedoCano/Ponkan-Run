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
                switch (this.game.currentStage.getCurrentStageType()) {
                    case PLAY -> this.game.currentStage.prepareStagePlaying();
                    case LOST -> this.game.currentStage.prepareStagePlay();
                }
            }
            case KeyEvent.VK_UP -> {
                if (this.game.player.getRemainingJumps() > 0) this.game.player.jump();
            }
            case KeyEvent.VK_DOWN -> this.game.player.roll();
            case KeyEvent.VK_P, KeyEvent.VK_PAUSE-> {
                switch (this.game.currentStage.getCurrentStageType()) {
                    case PLAYING -> this.game.currentStage.pause();
                    case PAUSED -> this.game.currentStage.unpause();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) this.game.player.getUp();
    }
}
