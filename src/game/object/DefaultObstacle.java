package game.object;

public abstract class DefaultObstacle extends Object2D {
    private String type;

    protected void setType (String type) {
        this.type = type;
    }

    protected String getType () {
        return type;
    }
}