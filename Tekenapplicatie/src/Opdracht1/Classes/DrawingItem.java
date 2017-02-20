package Opdracht1.Classes;

import Opdracht1.Color;

import java.awt.*;

/**
 * Created by user on 15-2-2017.
 */
public abstract class DrawingItem {

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract Point getAnchor();

    public abstract double getWidth();

    public abstract double getHeight();
}
