package Opdracht1.Classes;

import Opdracht1.*;

import java.awt.*;

/**
 * Created by Arjan on 16-2-2017.
 */
public class Polygon extends DrawingItem
{
    private Point[] vertices;
    private Double weight;

    public Point[] getVertices() {
        return vertices;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public Point getAnchor() {
        return null;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    public Polygon(Opdracht1.Color color, Point anchor, Double weight, Point[] vertices)
    {
        super(color, anchor);
        this.weight = weight;
        this.vertices = vertices;
    }

    @Override
    public String toString(){
        return "Polygon - Color: " + getColor() + " Weight: " + getWeight();
    }
}
