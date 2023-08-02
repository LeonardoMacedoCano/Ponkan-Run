package game.utils;

import game.Stage;
import game.object.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (Stage.getCurrentStageType().equals(LibraryUtils.StageType.PLAY)) {
                    Stage.prepareStagePlaying();
                }
                break;
            case KeyEvent.VK_UP:
                if (Player.getRemainingJumps() > 0)  {
                    Player.jump();
                }
                break;
        }
    }
}
