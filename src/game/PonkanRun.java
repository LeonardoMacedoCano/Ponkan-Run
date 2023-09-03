package game;

import game.object.character.Player;
import game.stage.Stage;
import game.utils.Animation;
import game.utils.KeyboardAdapter;
import game.utils.LibraryUtils;
import game.utils.CustomTimer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class PonkanRun extends JFrame implements Animation {
    private BufferStrategy renderStrategy;
    private KeyboardAdapter keyboardAdapter;
    private CustomTimer customTimer;
    private Stage currentStage;
    private Player player;
    private boolean active;
    private int screenWidth;
    private int screenHeight;
    private Insets screenEdge;

    public PonkanRun() {
        String TITLE = "Ponkan Run";
        String IMAGE_ICON = String.format("%s/icon.png", LibraryUtils.PATH_IMG_PLAYER);
        ImageIcon icon = new ImageIcon(IMAGE_ICON);
        int DEFAULT_HEIGHT = 728;
        int DEFAULT_WIDTH = 1024;

        setIgnoreRepaint(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle(TITLE);
        setIconImage(icon.getImage());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setScreenEdge(getInsets());
        setScreenWidth(getBounds().width + getScreenEdge().left + getScreenEdge().right);
        setScreenHeight(getBounds().height + getScreenEdge().top + getScreenEdge().bottom);
        setSize(getScreenWidth(), getScreenHeight());
        setRenderStrategy();

        setKeyboardAdapter(new KeyboardAdapter(this));
        setCurrentStage(new Stage(this));
        setPlayer(new Player(this));
        setCustomTimer(new CustomTimer(this, 15));
        setActive(true);
    }

    public static void main(String[] args) {
        PonkanRun ponkanRun = new PonkanRun();

        while (ponkanRun.getActive()) {
            ponkanRun.startGame();
            ponkanRun.loop();
        }
    }

    @Override
    public void update() {
        getCurrentStage().update();

        if (!(getCurrentStage().getCurrentStageType().equals(LibraryUtils.StageType.PAUSED))) {
            getPlayer().update();
        }
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        getCurrentStage().paint(graphics2D);
        getPlayer().paint(graphics2D);

        graphics2D.dispose();
        super.paint(graphics2D);
    }

    private void startGame() {
        System.gc();
        addKeyboardHandler();
        getCurrentStage().start();
    }

    private void loop() {
        resetTime();

        while (getCurrentStage().getActive()) {
            update();
            render();
            getCustomTimer().normalize();
            getCustomTimer().skipFrames();
            getCustomTimer().generateStatistics();
        }

        removeKeyListener(getKeyboardAdapter());
    }

    private void render() {
        try {
            Graphics2D graphics2D = (Graphics2D) getGraphicContext();
            paint(graphics2D);
            graphics2D.dispose();

            if(!getRenderStrategy().contentsLost()) getRenderStrategy().show();
            else System.out.println("Contents Lost");

            Toolkit.getDefaultToolkit().sync();
        } catch(Exception e){
            System.out.println("An error occurred while rendering");
            System.exit(0);
        }
    }

    private void resetTime() {
        getCustomTimer().restart();
    }

    private void addKeyboardHandler() {
        removeKeyListener(getKeyboardAdapter());
        addKeyListener(getKeyboardAdapter());
    }

    private Graphics getGraphicContext() {
        return getRenderStrategy().getDrawGraphics();
    }

    private void setRenderStrategy() {
        try {
            EventQueue.invokeAndWait(() -> createBufferStrategy(2));
        } catch (Exception e) {
            System.out.println("Error creating buffer strategy");
            System.exit(0);
        }
        this.renderStrategy = getBufferStrategy();
    }

    private BufferStrategy getRenderStrategy() {
        return renderStrategy;
    }

    private void setKeyboardAdapter (KeyboardAdapter keyboardAdapter) {
        this.keyboardAdapter = keyboardAdapter;
    }

    private KeyboardAdapter getKeyboardAdapter() {
        return keyboardAdapter;
    }

    private void setCustomTimer (CustomTimer customTimer) {
        this.customTimer = customTimer;
    }

    private CustomTimer getCustomTimer() {
        return customTimer;
    }

    private void setCurrentStage (Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    private void setPlayer (Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private void setActive (boolean active) {
        this.active = active;
    }

    private boolean getActive() {
        return active;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    private void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Insets getScreenEdge() {
        return screenEdge;
    }

    private void setScreenEdge(Insets screenEdge) {
        this.screenEdge = screenEdge;
    }
}
