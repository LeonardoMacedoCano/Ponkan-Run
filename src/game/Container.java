package game;

import javax.swing.*;

public class Container extends JFrame {
    private final String TITLE = "Ponkan Run";
    private final int HEIGHT = 765;
    private final int WIDTH = 1040;

    private Container() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
