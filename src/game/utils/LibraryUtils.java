package game.utils;

public class LibraryUtils {
    private static final String PATH_IMG = "src/game/image/";
    public static final String PATH_IMG_STAGE_BG = String.format("%s/stage-background/", PATH_IMG);
    public static final String PATH_IMG_INFORMATION = String.format("%s/information", PATH_IMG);
    public static final String PATH_IMG_PLAYER = String.format("%s/player/", PATH_IMG);
    public static final String PATH_IMG_PLAYER_STOPPED = String.format("%s/player-stopped/", PATH_IMG_PLAYER);
    public static final String PATH_IMG_PLAYER_WALKING = String.format("%s/player-walking/", PATH_IMG_PLAYER);

    public static class StageType {
        public static final String PLAY = "Play";
        public static final String PLAYING = "Playing";
        public static final String LOST = "Lost";
        public static final String PAUSED = "Paused";
    }
}
