package Opdracht1;

import Opdracht1.Classes.*;
import Opdracht1.Classes.Polygon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Drawing drawing = new Drawing("Test");
    private final Canvas canvas = new Canvas(400,400);

    private void testDrawingItems(){
        drawing.addDrawing(new Oval(Color.GREEN, new Point(10,10), 5, 50, 50));
        drawing.addDrawing(new Oval(Color.BLUE, new Point(220,20), 10,90,180));
        drawing.addDrawing(new PaintedText(Color.BLACK, new Point(50,100), "Dit is tekst", "Serif"));
        Point[] points = new Point[] {new Point(110, 110), new Point(110, 135), new Point( 190,160), new Point(200, 110)};
        drawing.addDrawing(new Polygon(Color.RED, new Point(200,200), 2.0, points));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tekenapplicatie");
        testDrawingItems();
        Group group = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();
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

        group.getChildren().add(canvas);
        primaryStage.setScene(new Scene(group));
        primaryStage.show();
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