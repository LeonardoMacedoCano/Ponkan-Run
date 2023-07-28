package game;

import javax.swing.JFrame;

public class Container extends JFrame {
    private final String TITLE = "Ponkan Run";
    private final int DEFAULT_HEIGHT = 765;
    private final int DEFAULT_WIDTH = 1040;

    private Container() {
        add(new Stage());
        setTitle(TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
