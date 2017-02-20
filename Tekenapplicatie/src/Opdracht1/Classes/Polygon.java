package Opdracht1.Classes;

import java.awt.*;

/**
 * Created by user on 16-2-2017.
 */
public class Polygon extends DrawingItem{
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
}
