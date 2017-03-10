package Opdracht1.Classes;

import Opdracht1.*;
import Opdracht1.Color;

import java.awt.*;

/**
 * Created by Arjan on 16-2-2017.
 */
public class Oval extends DrawingItem
{
    private double weight;
    private double width;
    private double height;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    public Oval(Color color, Point anchor, double weight, double width, double height)
    {
        super(color, anchor);
        this.weight = weight;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString(){
        return "Oval - Color: " + getColor() + " Anchor x/y: " + getAnchor().getX() + "/" + getAnchor().getY() + " Weight: " + weight + " Width/Height: " + width + "/" + height;
    }
}
