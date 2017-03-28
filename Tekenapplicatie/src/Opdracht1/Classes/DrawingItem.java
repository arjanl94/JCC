package Opdracht1.Classes;

import Opdracht1.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.Comparator;


/**
 * Created by Arjan on 15-2-2017.
 */
public abstract class DrawingItem implements Comparator<DrawingItem>, Serializable
{
    private Color color;
    private Point anchor;
    private DrawingItem previousState;
    private Rectangle boundingBox;

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
        boundingBox = calculateBoundingBox();
    }

    @Override
    public int compare(DrawingItem item1, DrawingItem item2)
    {
        return (item1.anchor.x + item1.anchor.y) - (item2.anchor.x + item2.anchor.y);
    }

    public void previousDrawing(DrawingItem item){
        previousState = item;
    }

    public boolean overlaps(DrawingItem other) {
        return this.boundingBox.intersects(other.boundingBox);
    }

    public boolean insideBoundingBox(Point point){
        if(this.boundingBox.contains(point)){
            return true;
        }
        else return false;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
    protected abstract Rectangle calculateBoundingBox();
}
