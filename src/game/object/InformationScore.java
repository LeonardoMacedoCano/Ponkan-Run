package game.object;

import game.PonkanRun;
import game.stage.Stage;
import game.utils.LibraryUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class InformationScore extends DefaultInformation {
    @Override
    protected String getImageFrame() {
        return String.format("%s/panelScore.png", LibraryUtils.PATH_IMG_INFORMATION);
    }

    @Override
    public void updateObject() {

    }

    @Override
    public void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX((PonkanRun.DEFAULT_WIDTH - getWidth()) / 2);
        setY(0);
    }

    @Override
    protected void beforeCreateObject() {
        setUsedTextBox(true);
    }

    @Override
    protected void afterCreateObject() {

    }

    @Override
    protected void createTextBox(Graphics2D graphics2D) {
        AttributedString text;
        Font font;

        font = new Font("TimesRoman", Font.PLAIN, 50);
        text = new AttributedString(String.format("%03d", Stage.getCurrentScore()));
        text.addAttribute(TextAttribute.FONT, font);
        text.addAttribute(TextAttribute.FOREGROUND, Color.white);

        AttributedCharacterIterator characterIterator = text.getIterator();
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(characterIterator, fontRenderContext);
        TextLayout textLayout = lbm.nextLayout(Integer.MAX_VALUE);

        setText(text);
        setTextBoxHeight((int) textLayout.getBounds().getHeight());
        setTextBoxWidth((int) textLayout.getBounds().getWidth());
        setXText((PonkanRun.DEFAULT_WIDTH - getTextBoxWidth()) / 2);
        setYText(((getHeight() + getTextBoxHeight()) / 2));
    }
}
