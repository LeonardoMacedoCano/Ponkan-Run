package game.object.information;

import game.PonkanRun;
import game.object.Object2D;

import java.awt.*;
import java.text.AttributedString;

public abstract class DefaultInformation extends Object2D {
    private boolean usedTextBox;
    private int xText;
    private int yText;
    private int textBoxWidth;
    private int textBoxHeight;
    private AttributedString text;

    public DefaultInformation(PonkanRun game) {
        super(game);
    }

    protected abstract void createTextBox(Graphics2D graphics2D);

    public void paintTextBox(Graphics2D graphics2D) {
        createTextBox(graphics2D);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawString(getText().getIterator(), getXText(), getYText());
    }

    public boolean isUsedTextBox() {
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

    protected int getTextBoxWidth () {
        return textBoxWidth;
    }

    protected void setTextBoxWidth (int textBoxWidth) {
        this.textBoxWidth = textBoxWidth;
    }

    protected int getTextBoxHeight () {
        return textBoxHeight;
    }

    protected void setTextBoxHeight (int textBoxHeight) {
        this.textBoxHeight = textBoxHeight;
    }

    protected AttributedString getText () {
        return text;
    }

    protected void setText (AttributedString text) {
        this.text = text;
    }
}
