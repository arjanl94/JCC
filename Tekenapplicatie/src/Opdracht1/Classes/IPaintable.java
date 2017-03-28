package Opdracht1.Classes;

import Opdracht1.*;

import java.awt.*;

/**
 * Created by user on 28-3-2017.
 */
public interface IPaintable {
    public void setColor(Opdracht1.Color color);
    public void setLineWidth(double width);
    public void paintOval(Oval oval);
    public void paintText(PaintedText text);
    public void paintPolygon(Polygon polygon);
}
