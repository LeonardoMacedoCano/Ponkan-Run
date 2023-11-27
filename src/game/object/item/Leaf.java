package game.object.item;

import game.PonkanRun;
import game.object.Object2D;
import game.utils.LibraryUtils;

public class Leaf extends Object2D {
    public Leaf(PonkanRun game) {
        super(game);
    }

    @Override
    public void update() {
        updateFrame();
        setImage();
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void updateFrame() {
        int LAST_FRAME = 8;
        if (getFrame() < LAST_FRAME) {
            setFrame(getFrame() +1);
        } else {
            setFrame(1);
        }
    }

    @Override
    protected void setStartPosition() {
        setX(getGame().getPlayer().getX() + getGame().getPlayer().getWidth());
        setY(getGame().getPlayer().getY() + (getGame().getPlayer().getHeight() / 3));
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/%d.png", LibraryUtils.PATH_IMG_ITEM_LEAF, getFrame());
    }

    @Override
    protected void afterCreateObject() {

    }
}
