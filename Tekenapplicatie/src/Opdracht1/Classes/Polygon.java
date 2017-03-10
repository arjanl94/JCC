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
    public double getWidth()
    {
        double min = 1000;
        double max = 0;
        for (Point x : vertices)
        {
            if (x.getX() < min)
            {
                min = x.getX();
            }
            if (x.getX() > max)
            {
                max = x.getX();
            }
        }
        double weight = max - min;
        return weight;
    }

    @Override
    public double getHeight()
    {
        double min = 1000;
        double max = 0;
        for (Point y : vertices)
        {
            if  (y.getY() < min)
            {
                min = y.getY();
            }
            if (y.getY() > max)
            {
                max = y.getY();
            }
        }
        double height = max - min;
        return height;
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
