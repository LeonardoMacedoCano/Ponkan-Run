package game;

import game.stage.Stage;
import game.utils.LibraryUtils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PonkanRun extends JFrame {
    public static final int DEFAULT_HEIGHT = 765;
    public static final int DEFAULT_WIDTH = 1040;

    private PonkanRun() {
        String TITLE = "Ponkan Run";
        String IMAGE_ICON = String.format("%s/icon.png", LibraryUtils.PATH_IMG_PLAYER);
        ImageIcon icon = new ImageIcon(IMAGE_ICON);

        add(new Stage());
        setTitle(TITLE);
        setIconImage(icon.getImage());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PonkanRun();
    }
}
