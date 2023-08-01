package game.object;

import game.Container;
import game.Stage;
import game.utils.LibraryUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
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
        setX((Container.DEFAULT_WIDTH - getWidth()) / 2);
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

        setText(text);
        setXText((Container.DEFAULT_WIDTH - getTextBoxWidth(graphics2D, getText())) / 2);
        setYText(75);
    }
}
