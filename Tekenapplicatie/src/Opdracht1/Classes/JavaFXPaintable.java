package Opdracht1.Classes;

import Opdracht1.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by user on 28-3-2017.
 */
public class JavaFXPaintable implements IPaintable{
    private GraphicsContext gc;

    public JavaFXPaintable(GraphicsContext gc){
        this.gc = gc;
    }

    @Override
    public void setColor(Opdracht1.Color color) {
        gc.setStroke(javafx.scene.paint.Paint.valueOf(color.toString()));
    }

    @Override
    public void setLineWidth(double width) {
        gc.setLineWidth(width);
    }

    @Override
    public void paintOval(Oval oval){
        setColor(oval.getColor());
        setLineWidth(oval.getWeight());
        gc.strokeOval(oval.getAnchor().x, oval.getAnchor().y, oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paintText(PaintedText text) {
        gc.setFill(javafx.scene.paint.Paint.valueOf(text.getColor().toString()));
        gc.setFont(new javafx.scene.text.Font(text.getFontName(), 10));
        gc.fillText((text).getContent(), text.getAnchor().getX(), text.getAnchor().getY());
    }

    @Override
    public void paintPolygon(Polygon polygon) {
        setColor(polygon.getColor());
        setLineWidth(polygon.getWeight());
        ArrayList<Double> xwaarden = new ArrayList<>();
        ArrayList<Double> ywaarden = new ArrayList<>();
        for (Point x: (polygon).getVertices())
        {
            xwaarden.add(x.getX());
        }
        for (Point x: (polygon).getVertices())
        {
            ywaarden.add(x.getY());
        }
        double[] xarray = convertDoubles(xwaarden);
        double[] yarray = convertDoubles(ywaarden);

        gc.strokePolygon(xarray, yarray, xwaarden.size());
    }

    public static double[] convertDoubles(ArrayList<Double> doubles)
    {
        double[] ret = new double[doubles.size()];
        Iterator<Double> iterator = doubles.iterator();
        int i = 0;
        while(iterator.hasNext())
        {
            ret[i] = iterator.next();
            i++;
        }
        return ret;
    }
}
