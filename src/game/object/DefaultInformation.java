package game.object;

import java.text.AttributedString;

public class DefaultInformation extends Object2D  {
    private boolean usedTextBox;
    private int xText;
    private int yText;
    private AttributedString text;

    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {

    }

    @Override
    protected String getImageFrame() {
        return null;
    }

    @Override
    protected void beforeCreateObject() {

    }

    @Override
    protected void afterCreateObject() {

    }

    protected boolean isUsedTextBox () {
        return usedTextBox;
    }

    protected void setUsedTextBox (boolean usedTextBox) {
        this.usedTextBox = usedTextBox;
    }

    protected int getXText () {
        return xText;
    }

    protected void setXText (int xText) {
        this.xText = xText;
    }

    protected int getYText () {
        return yText;
    }

    protected void setYText (int yText) {
        this.yText = yText;
    }

    protected AttributedString getText () {
        return text;
    }

    protected void setText (AttributedString text) {
        this.text = text;
    }
}
