package Opdracht1.Classes;

import java.util.*;

/**
 * Created by Arjan on 15-2-2017.
 */
public class Drawing implements java.io.Serializable
{
    private String name;
    private ArrayList<DrawingItem> drawingItems = new ArrayList<>();
    private int number;

    public String getName()
    { return name; }
    public void setName(String value)
    { name = value; }

    public Drawing(String name)
    {
        this.name = name;
    }

    public void addDrawing(DrawingItem item){
        drawingItems.add(item);
    }

    public void removeDrawings(){
        drawingItems.clear();
    }

    public ArrayList<DrawingItem> getDrawingItems(){
        return drawingItems;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void previousDrawing(int i){
        if (i <= drawingItems.size()){
            DrawingItem item = drawingItems.get(drawingItems.size() - i);
            System.out.println("Drawing: " + item.toString());
            previousDrawing(i + 1);
        }
        else{
            System.out.println("End of list Drawings");
        }
    }
}
