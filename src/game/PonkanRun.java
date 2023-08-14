package game;

import game.object.Player;
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
    private GraphicsDevice graphicsDevice;
    private BufferStrategy bufferStrategy;
    private final KeyboardAdapter keyboardAdapter;
    private final CustomTimer customTimer;
    private final Stage currentStage;
    public Player player;
    private boolean active;
    private int screenWidth;
    private int screenHeight;
    public static final int DEFAULT_HEIGHT = 728;
    public static final int DEFAULT_WIDTH = 1024;

    public PonkanRun() {
        String TITLE = "Ponkan Run";
        String IMAGE_ICON = String.format("%s/icon.png", LibraryUtils.PATH_IMG_PLAYER);
        ImageIcon icon = new ImageIcon(IMAGE_ICON);

        setTitle(TITLE);
        setIconImage(icon.getImage());
        setActive(true);
        startFullScreen();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.keyboardAdapter = new KeyboardAdapter(this);
        this.currentStage = new Stage(this);
        this.player = new Player(this);
        this.customTimer = new CustomTimer(this, 14);
    }

    public static void main(String[] args) {
        PonkanRun ponkanRun = new PonkanRun();

        while (ponkanRun.isActive()) {
            ponkanRun.startGame();
            ponkanRun.loop();
        }
    }

    public void startGame() {
        System.gc();

        this.addKeyboardHandler();
        this.currentStage.start();
    }

    public void loop() {
        this.resetTime();

        while (this.currentStage.isActive()) {
            this.update();
            this.render();
            this.customTimer.normalize();
            this.customTimer.skipFrames();
            this.customTimer.generateStatistics();
        }

        this.removeKeyListener(this.keyboardAdapter);
    }

    public void update() {
        this.currentStage.update();
        this.player.updateObject();
    }

    private void render() {
        try {
            Graphics2D graphics2D = (Graphics2D) this.getGraphicContext();
            paint(graphics2D);
            graphics2D.dispose();

            if(!bufferStrategy.contentsLost()) bufferStrategy.show();
            else System.out.println("Contents Lost");

            Toolkit.getDefaultToolkit().sync();
        } catch(Exception e){e.printStackTrace();}
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.screenWidth, this.screenHeight);

        this.currentStage.paint(graphics2D);
        this.player.paintObject(graphics2D);

        graphics2D.dispose();
        super.paint(graphics2D);
    }

    public void resetTime() {
        this.customTimer.restart();
    }

    public Graphics getGraphicContext() {
        return this.bufferStrategy.getDrawGraphics();
    } 

    private void setBufferStrategy() {
        try {
            EventQueue.invokeAndWait(() -> createBufferStrategy(2));
        } catch (Exception e) {
            System.out.println("Error creating buffer strategy");
            System.exit(0);
        }
        bufferStrategy = getBufferStrategy();
    }

    protected void setActive (boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    private void startFullScreen() {
        int width = DEFAULT_WIDTH,
                height = DEFAULT_HEIGHT,
                bitDepth = 32;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();

        setIgnoreRepaint(true);
        setResizable(false);

        if(!(graphicsDevice.isFullScreenSupported())) {
            System.out.println("Full screen not supported");
            startInTestMode(width, height);
        } else {
            if(displayModeAvailable(width, height, bitDepth)) {
                int refreshRate = getRefreshRate(width, height, bitDepth);

                if(refreshRate != 0) {
                    graphicsDevice.setFullScreenWindow(this);
                    this.setDisplayMode(width, height, bitDepth, refreshRate);
                } else {
                    startInTestMode(width, height);
                }
            }
            else {
                startInTestMode(width, height);
            }
        }

        screenWidth = getBounds().width;
        screenHeight = getBounds().height;

        setBufferStrategy();
    }

    private void startInTestMode(int width, int height) {
        graphicsDevice = null;

        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setDisplayMode(int width, int height, int bitDepth, int refreshRate) {
        if(!graphicsDevice.isDisplayChangeSupported()) {
            System.out.println("Display change not supported");
            startInTestMode(width, height);
            return;
        }

        DisplayMode displayMode = new DisplayMode(width, height, bitDepth, refreshRate);

        try {
            graphicsDevice.setDisplayMode(displayMode);
            System.out.println("Display mode changed to:: (" + width + "," +
                    height + "," + bitDepth + ")");
        } catch(IllegalArgumentException e) {
            System.out.println("Error when changing display mode (" + width + "," +
                    height + "," + bitDepth + ")");
        }

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int getRefreshRate(int width, int height, int bitDepth) {
        int refRateDefault = 75;
        int refRateAcceptable = 85;
        boolean isDefault = false;
        boolean isAcceptable = false;
        DisplayMode[] modes = graphicsDevice.getDisplayModes();

        for(DisplayMode mode: modes) {
            if(mode.getWidth() == width &&
                    mode.getHeight() == height &&
                    mode.getBitDepth() == bitDepth) {
                if(mode.getRefreshRate() == refRateDefault) {
                    isDefault = true;
                } else if(mode.getRefreshRate() == refRateAcceptable) {
                    isAcceptable = true;
                }
            }
        }

        if(isAcceptable) return refRateAcceptable;
        else if(isDefault) return refRateDefault;
        else return 0;
    }

    private boolean displayModeAvailable(int width, int height, int bitDepth) {
        DisplayMode[] modes = graphicsDevice.getDisplayModes();

        for (DisplayMode mode : modes) {
            if (width == mode.getWidth() && height == mode.getHeight() && bitDepth == mode.getBitDepth()) {
                return true;
            }
        }
        return false;
    }

    public void addKeyboardHandler() {
        this.removeKeyListener(this.keyboardAdapter);
        this.addKeyListener(this.keyboardAdapter);
    }
}
