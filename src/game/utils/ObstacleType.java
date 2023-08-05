package game.utils;

import java.util.ArrayList;
import java.util.List;

public class ObstacleType {
    public static final String CUP = "Cup";
    public static final String TEA_CUP = "Tea cup";
    public static final String KNIFE = "Knife";

    private static final List<String> obstacleType = new ArrayList<>();
    static {
        obstacleType.add(CUP);
        obstacleType.add(TEA_CUP);
        obstacleType.add(KNIFE);
    }

    protected static List<String> getObstacleType() {
        return obstacleType;
    }

    protected static int getTotalObstacleType() {
        return obstacleType.size();
    }
}
