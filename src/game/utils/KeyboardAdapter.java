package game.utils;

import game.Stage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (Stage.getCurrentStageType() == LibraryUtils.StageType.PLAY) {
                    Stage.prepareStagePlaying();
                }
                break;
        }
    }
}
