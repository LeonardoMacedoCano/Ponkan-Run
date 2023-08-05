package game.object;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public abstract class DefaultInformation extends Object2D {
    private boolean usedTextBox;
    private int xText;
    private int yText;
    private AttributedString text;

    protected abstract void createTextBox(Graphics2D graphics2D);

    protected static int getTextBoxWidth(Graphics2D graphics2D, AttributedString attributedString) {
        AttributedCharacterIterator characterIterator = attributedString.getIterator();
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(characterIterator, fontRenderContext);
        TextLayout textLayout = lbm.nextLayout(Integer.MAX_VALUE);
        return (int) textLayout.getBounds().getWidth();
    }

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

    protected AttributedString getText () {
        return text;
    }

    protected void setText (AttributedString text) {
        this.text = text;
    }
}
