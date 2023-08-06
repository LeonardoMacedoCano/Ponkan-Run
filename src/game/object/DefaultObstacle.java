package game.object;

public abstract class DefaultObstacle extends Object2D {
    private int type;

    protected void setType (int type) {
        this.type = type;
    }

    protected int getType () {
        return type;
    }
}