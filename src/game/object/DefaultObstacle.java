package game.object;

import game.PonkanRun;

public abstract class DefaultObstacle extends Object2D {
    private int type;

    public DefaultObstacle(PonkanRun game) {
        super(game);
    }

    protected void setType (int type) {
        this.type = type;
    }

    protected int getType () {
        return type;
    }
}