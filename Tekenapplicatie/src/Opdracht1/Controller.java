package Opdracht1;

import Opdracht1.Classes.*;
import Opdracht1.Classes.Polygon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import java.awt.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Canvas canvas;
    public ComboBox lbitems;
    public Button btnadd;
    private int number = 0;

    private Drawing drawing = new Drawing("Test");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbitems.getItems().addAll("Polygon", "Oval", "Text");
    }

    public void addItem(MouseEvent mouseEvent) {
        if (lbitems.getValue() == "Polygon"){
            Point[] points = new Point[] {new Point(110, 110), new Point(110, 135), new Point( 190,160), new Point(200, 110)};
            drawing.addDrawing(new Opdracht1.Classes.Polygon(Opdracht1.Color.RED, new Point(200,200), 2.0, points));
        }
        else if (lbitems.getValue() == "Oval"){
            drawing.addDrawing(new Oval(Opdracht1.Color.GREEN, new Point(10,10), 5, 50, 50));
        }
        else if (lbitems.getValue() == "Text"){
            if (number == 0){
                drawing.addDrawing(new PaintedText(Opdracht1.Color.BLACK, new Point(50,100), "Dit is tekst", "Serif"));
            }
            else{
                drawing.addDrawing(new Oval(Opdracht1.Color.BLUE, new Point(220,20), 10,90,180));
            }
        }
        DrawItems(drawing);
    }

    public void DrawItems(Drawing drawing){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        for (DrawingItem item: drawing.getDrawingItems())
        {
            if (item instanceof Oval){
                gc.setStroke(javafx.scene.paint.Paint.valueOf(item.getColor().toString()));
                gc.setLineWidth(((Oval) item).getWeight());
                gc.strokeOval(item.getAnchor().getX(), item.getAnchor().getY(), item.getWidth(), item.getHeight());
                System.out.println(item.toString());
            }
            else if (item instanceof PaintedText){
                PaintedText text = (PaintedText) item;
                gc.setFill(javafx.scene.paint.Paint.valueOf(item.getColor().toString()));
                gc.setFont(new javafx.scene.text.Font(text.getFontName(), 10));
                gc.fillText(((PaintedText) item).getContent(), item.getAnchor().getX(), item.getAnchor().getY());
                System.out.println(item.toString());
            }
            else if (item instanceof Polygon){
                gc.setStroke(javafx.scene.paint.Paint.valueOf(item.getColor().toString()));
                gc.setLineWidth(((Polygon) item).getWeight());
                ArrayList<Double> xwaarden = new ArrayList<>();
                ArrayList<Double> ywaarden = new ArrayList<>();
                for (Point x: ((Polygon) item).getVertices())
                {
                    xwaarden.add(x.getX());
                }
                for (Point x: ((Polygon) item).getVertices())
                {
                    ywaarden.add(x.getY());
                }
                double[] xarray = convertDoubles(xwaarden);
                double[] yarray = convertDoubles(ywaarden);

                gc.strokePolygon(xarray, yarray, xwaarden.size());
                System.out.println(item.toString());
            }
        }
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
