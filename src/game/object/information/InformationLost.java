package game.object.information;

import game.PonkanRun;
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

public class InformationLost extends DefaultInformation {
    public InformationLost(PonkanRun game) {
        super(game);
    }

    @Override
    protected void createTextBox(Graphics2D graphics2D) {
        AttributedString text;
        Font font;

        font = new Font("TimesRoman", Font.PLAIN, 30);

        int currentScore = getGame().getCurrentStage().getCurrentScore();
        int currentRecord = getGame().getCurrentStage().getCurrentRecord();

        String formattedText;

        if (currentScore > currentRecord) {
            formattedText = String.format("New Record: %03d", currentScore);
        } else {
            formattedText = String.format("Score: %03d / Record: %03d", currentScore, currentRecord);
        }

        text = new AttributedString(formattedText);

        text.addAttribute(TextAttribute.FONT, font);
        text.addAttribute(TextAttribute.FOREGROUND, Color.white);

        AttributedCharacterIterator characterIterator = text.getIterator();
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(characterIterator, fontRenderContext);
        TextLayout textLayout = lbm.nextLayout(Integer.MAX_VALUE);

        setText(text);
        setTextBoxHeight((int) textLayout.getBounds().getHeight());
        setTextBoxWidth((int) textLayout.getBounds().getWidth());
        setXText((getGame().getScreenWidth() - getTextBoxWidth()) / 2);
        setYText((getGame().getScreenHeight() / 2) + getTextBoxHeight());
    }

    @Override
    public void update() {

    }

    @Override
    protected void updateFrame() {

    }

    @Override
    protected void setStartPosition() {
        setX((getGame().getScreenWidth() - getWidth()) / 2);
        setY((getGame().getScreenHeight() - getHeight()) / 2);
    }

    @Override
    protected String getImageFrame() {
        return String.format("%s/panelLost.png", LibraryUtils.PATH_IMG_INFORMATION);
    }

    @Override
    protected void beforeCreateObject() {
        setUsedTextBox(true);
    }

    @Override
    protected void afterCreateObject() {

    }
}
