package Opdracht1.Classes;

import Opdracht1.Color;

import java.awt.*;
import java.util.Comparator;


/**
 * Created by Arjan on 15-2-2017.
 */
public abstract class DrawingItem implements Comparator<DrawingItem>
{
    private Color color;
    private Point anchor;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public  Point getAnchor(){
        return anchor;
    }

    public abstract double getWidth();

    public abstract double getHeight();

    public DrawingItem(Color color, Point anchor)
    {
        this.color = color;
        this.anchor = anchor;
    }

    @Override
    public int compare(DrawingItem item1, DrawingItem item2)
    {
        return (item1.anchor.x + item1.anchor.y) - (item2.anchor.x + item2.anchor.y);
    }
}
