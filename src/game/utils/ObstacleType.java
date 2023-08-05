package game.utils;

import java.util.ArrayList;
import java.util.List;

public class ObstacleType {
    public static final int CUP = 0;
    public static final int TEA_CUP = 1;
    public static final int KNIFE = 2;

    private static final List<Integer> obstacleType = new ArrayList<>();
    static {
        obstacleType.add(CUP);
        obstacleType.add(TEA_CUP);
        obstacleType.add(KNIFE);
    }

    protected static List<Integer> getObstacleType() {
        return obstacleType;
    }

    public static int getTotalObstacleType() {
        return obstacleType.size();
    }
}
