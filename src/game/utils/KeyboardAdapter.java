package game.utils;

import game.stage.Stage;
import game.object.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> {
                if (Stage.getCurrentStageType().equals(LibraryUtils.StageType.PLAY)) {
                    Stage.prepareStagePlaying();
                }
            }
            case KeyEvent.VK_UP -> {
                if (Player.getRemainingJumps() > 0) {
                    Player.jump();
                }
            }
            case KeyEvent.VK_DOWN -> Player.roll();
            default -> throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Player.getUp();
        }
    }
}
