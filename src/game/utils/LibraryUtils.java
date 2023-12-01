package game.utils;

import game.object.Object2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LibraryUtils {
    private static final String PATH_IMG = "/game/image/";
    private static final String PATH_IMG_ENVIRONMENT = String.format("%senvironment/", PATH_IMG);
    private static final String PATH_IMG_ITEM = String.format("%sitem/", PATH_IMG);
    public static final String PATH_IMG_ENVIRONMENT_BG = String.format("%senvironment-bg/", PATH_IMG_ENVIRONMENT);
    public static final String PATH_IMG_ENVIRONMENT_FLOOR = String.format("%senvironment-floor/", PATH_IMG_ENVIRONMENT);
    public static final String PATH_IMG_ENVIRONMENT_KNIFE_STAND = String.format("%sknife-stand/", PATH_IMG_ENVIRONMENT);
    public static final String PATH_IMG_INFORMATION = String.format("%sinformation/", PATH_IMG);
    public static final String PATH_IMG_PLAYER = String.format("%splayer/", PATH_IMG);
    public static final String PATH_IMG_PLAYER_STOPPED = String.format("%splayer-stopped/", PATH_IMG_PLAYER);
    public static final String PATH_IMG_PLAYER_WALKING = String.format("%splayer-walking/", PATH_IMG_PLAYER);
    public static final String PATH_IMG_PLAYER_JUMPING = String.format("%splayer-jumping/", PATH_IMG_PLAYER);
    public static final String PATH_IMG_PLAYER_ROLLING = String.format("%splayer-rolling/", PATH_IMG_PLAYER);
    public static final String PATH_IMG_OBSTACLE = String.format("%sobstacle/", PATH_IMG);
    public static final String PATH_IMG_ITEM_LEAF = String.format("%sleaf/", PATH_IMG_ITEM);

    private static final String PROPERTIES_FILE = "record.properties";

    public enum StageType {
        PLAY,
        PLAYING,
        LOST,
        PAUSED
    }

    public enum ObstacleType {
        CUP,
        TEA_CUP,
        KNIFE;

        public static int getTotalObstacleType() {
            return values().length;
        }
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bImage.getGraphics().drawImage(img, 0, 0, null);
        return bImage;
    }

    public static boolean checkCollisionBetweenObjects2D(Object2D obj1, Object2D obj2) {
        BufferedImage bufferedImg1 = toBufferedImage(obj1.getImage());
        BufferedImage bufferedImg2 = toBufferedImage(obj2.getImage());

        int xIntersection = Math.max(obj1.getX(), obj2.getX());
        int yIntersection = Math.max(obj1.getY(), obj2.getY());
        int intersectionWidth = Math.min(obj1.getX() + bufferedImg1.getWidth(), obj2.getX() + bufferedImg2.getWidth()) - xIntersection;
        int intersectionHeight = Math.min(obj1.getY() + bufferedImg1.getHeight(), obj2.getY() + bufferedImg2.getHeight()) - yIntersection;

        for (int x = xIntersection; x < xIntersection + intersectionWidth; x++) {
            for (int y = yIntersection; y < yIntersection + intersectionHeight; y++) {
                int pixelImage1 = bufferedImg1.getRGB(x - obj1.getX(), y - obj1.getY());
                int pixelImage2 = bufferedImg2.getRGB(x - obj2.getX(), y - obj2.getY());
                if ((pixelImage1 & 0xFF000000) != 0 && (pixelImage2 & 0xFF000000) != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int loadRecord() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error loading the record.");
        }
        return Integer.parseInt(properties.getProperty("record", "0"));
    }

    public static void saveRecord(int record) {
        Properties properties = new Properties();
        properties.setProperty("record", String.valueOf(record));
        try (FileOutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(output, "Game Record");
        } catch (IOException e) {
            System.out.println("Error saving the record.");
        }
    }
}
