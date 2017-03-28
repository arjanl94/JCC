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
    private Canvas canvas;
    public ComboBox lbitems;
    public Button btnadd;
    public Button btnsave;
    public Button btnload;

    private Drawing drawing = new Drawing("Test");
    private SerializeDrawing serializedrawing = new SerializeDrawing();
    private Database database = new Database();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbitems.getItems().addAll("Polygon", "Oval", "Text");
    }

    public void addItem(MouseEvent mouseEvent) {
        if (lbitems.getValue() == "Polygon"){
            Point[] points = new Point[] {new Point(110, 110), new Point(110, 135), new Point( 190,160), new Point(200, 110)};
            drawing.addDrawing(new Opdracht1.Classes.Polygon(Opdracht1.Color.RED, new Point(200,200), 2.0, points));
        }
        else if (lbitems.getValue() == "Text"){
            drawing.addDrawing(new PaintedText(Opdracht1.Color.BLACK, new Point(50,100), "Dit is tekst", "Serif"));
        }
        else if (lbitems.getValue() == "Oval"){
            if (drawing.getNumber() == 0){
                drawing.addDrawing(new Oval(Opdracht1.Color.GREEN, new Point(10,10), 5, 50, 50));
                drawing.setNumber(1);
            }
            else{
                drawing.addDrawing(new Oval(Opdracht1.Color.BLUE, new Point(220,20), 10,90,180));
            }
        }
        DrawItems(drawing);
    }

    public void addDrawings(String item){
        if (item.equals("Polygon")) {
            Point[] points = new Point[]{new Point(110, 110), new Point(110, 135), new Point(190, 160), new Point(200, 110)};
            Polygon polygon = new Opdracht1.Classes.Polygon(Opdracht1.Color.RED, new Point(200, 200), 2.0, points);
            boolean overlap = false;
            for (DrawingItem drawingItem : drawing.getDrawingItems()) {
                if (polygon.overlaps(drawingItem)) {
                    System.out.println("Item overlaps");
                    overlap = true;
                    break;
                }
                if (!overlap){
                    drawing.addDrawing(polygon);
                }
            }
        }
        else if (item.equals("Text")){
            drawing.addDrawing(new PaintedText(Opdracht1.Color.BLACK, new Point(50,100), "Dit is tekst", "Serif"));
        }
        else if (item.equals("Oval")){
            if (drawing.getNumber() == 0){
                Oval oval = new Oval(Opdracht1.Color.GREEN, new Point(10,10), 5, 50, 50);
                boolean overlap = false;
                for (DrawingItem drawingItem : drawing.getDrawingItems()) {
                    if (oval.overlaps(drawingItem)){
                        System.out.println("Item overlaps");

                        overlap = true;
                        break;
                    }
                    if (!overlap){
                        drawing.addDrawing(oval);
                        drawing.setNumber(1);
                    }
                }
            }
            else{
                drawing.addDrawing(new Oval(Opdracht1.Color.BLUE, new Point(220,20), 10,90,180));
            }
        }
        DrawItems(drawing);
    }

    public void DrawItems(Drawing drawing){
        clearCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<DrawingItem> drawnItems = new ArrayList<>();
        for (DrawingItem item: drawing.getDrawingItems())
        {
            int count = 0;
            for (DrawingItem drawnItem : drawnItems) {
                if (item.overlaps(drawnItem)){
                    count = 1;
                }
            }
            if (count == 0){
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
                drawnItems.add(item);
            }
            else{
                System.out.println("Item overlaps previous drawn item");
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

    public void saveDrawing(MouseEvent mouseEvent) {
        serializedrawing.save(drawing);
    }

    public void loadDrawing(MouseEvent mouseEvent) {
        drawing = serializedrawing.load();
        DrawItems(drawing);
    }

    public void clearDrawing(MouseEvent mouseEvent) {
        clearCanvas();
        drawing.removeDrawings();
    }

    public void clearCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
    }

    public void saveDb(MouseEvent mouseEvent) {
        for (DrawingItem item:drawing.getDrawingItems()) {
            if (item instanceof Oval){
                database.saveItems("Oval");
            }
            else if (item instanceof PaintedText){
                database.saveItems("Text");
            }
            else if (item instanceof Polygon){
                database.saveItems("Polygon");
            }
        }
    }

    public void loadDb(MouseEvent mouseEvent) {
        ArrayList<String> drawitems = database.loadItems();
        for (String item: drawitems) {
            addDrawings(item);
        }
    }
}
