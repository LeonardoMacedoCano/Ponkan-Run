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
                if (this.game.currentStage.getCurrentStageType().equals(LibraryUtils.StageType.PLAY)) {
                    this.game.currentStage.prepareStagePlaying();
                }
            }
            case KeyEvent.VK_UP -> {
                if (this.game.player.getRemainingJumps() > 0) this.game.player.jump();
            }
            case KeyEvent.VK_DOWN -> this.game.player.roll();
            default -> throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) this.game.player.getUp();
    }
}
