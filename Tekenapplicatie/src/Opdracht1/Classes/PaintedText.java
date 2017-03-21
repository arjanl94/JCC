package Opdracht1.Classes;

import java.awt.*;

/**
 * Created by Arjan on 16-2-2017.
 */
public class PaintedText extends DrawingItem
{
    private String content;
    private String fontName;
    private int Width;
    private int Height;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    @Override
    Rectangle boundingBox() {
        return new Rectangle(getAnchor().x, getAnchor().y, Width, Height);
    }

    @Override
    public double getWidth() {
        Font font = new Font(fontName,Font.PLAIN, 10);
        FontMetrics fontMetrics = new FontMetrics(font){};
        double width = fontMetrics.stringWidth(content);
        Width = (int)width;
        return width;
    }

    @Override
    public double getHeight() {
        Font font = new Font(fontName,Font.PLAIN, 10);
        FontMetrics fontMetrics = new FontMetrics(font){};
        double height = fontMetrics.getHeight();
        Height = (int)height;
        return height;
    }

    public PaintedText(Opdracht1.Color color, Point anchor, String content, String fontname)
    {
        super(color, anchor);
        this.content = content;
        this.fontName = fontname;
    }

    @Override
    public String toString(){
        return "Painted Text - Color: " + getColor() + " Anchor x/y: " + getAnchor().getX() + "/" + getAnchor().getY() + " Font: " + getFontName();
    }
}
