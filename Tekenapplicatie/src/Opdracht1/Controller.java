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
            addDrawings("Polygon");
        }
        else if (lbitems.getValue() == "Text"){
            addDrawings("Text");
        }
        else if (lbitems.getValue() == "Oval"){
            addDrawings("Oval");
        }
    }

    public void addDrawings(String item) {
        if (item.equals("Polygon")) {
            Point[] points = new Point[]{new Point(110, 110), new Point(110, 135), new Point(190, 160), new Point(200, 110)};
            Polygon polygon = new Opdracht1.Classes.Polygon(Opdracht1.Color.RED, new Point(200, 200), 2.0, points);
            drawing.addDrawing(polygon);
            addPreviousState(polygon);
        }
        else if (item.equals("Text")) {
            PaintedText text = new PaintedText(Opdracht1.Color.BLACK, new Point(50, 100), "Dit is tekst", "Serif");
            drawing.addDrawing(text);
            addPreviousState(text);
        }
        else if (item.equals("Oval")) {
            if (drawing.getNumber() == 0) {
                Oval oval = new Oval(Opdracht1.Color.GREEN, new Point(10, 10), 5, 50, 50);
                drawing.addDrawing(oval);
                addPreviousState(oval);
                drawing.setNumber(1);
            }
            else {
                Oval oval = new Oval(Opdracht1.Color.BLUE, new Point(220, 20), 10, 90, 180);
                drawing.addDrawing(oval);
                addPreviousState(oval);
                drawing.setNumber(0);
            }
        }
        DrawItems(drawing);
    }

    public void addPreviousState(DrawingItem item){
        if (drawing.getDrawingItems().size() > 1){
            item.setPreviousDrawing(drawing.getDrawingItems().get(drawing.getDrawingItems().size() - 2));
        }
    }

    public void DrawItems(Drawing drawing){
        clearCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        JavaFXPaintable paintable = new JavaFXPaintable(gc);
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
                    paintable.paintOval((Oval)item);
                    System.out.println(item.toString());
                }
                else if (item instanceof PaintedText){
                    paintable.paintText((PaintedText) item);
                    System.out.println(item.toString());
                }
                else if (item instanceof Polygon){
                    paintable.paintPolygon((Polygon)item);
                    System.out.println(item.toString());
                }
                drawnItems.add(item);
            }
            else{
                System.out.println("Item overlaps previous drawn item");
            }
        }
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
        drawing.setNumber(0);
    }

    public void clearCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
    }

    public void saveDb(MouseEvent mouseEvent) {
        database.clearDatabase();
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

    public void btnCheckPreviousItems(MouseEvent mouseEvent) {
        drawing.previousDrawing(1);
    }
}
