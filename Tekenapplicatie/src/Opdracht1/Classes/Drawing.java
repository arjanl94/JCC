package Opdracht1.Classes;

import java.util.*;

/**
 * Created by Arjan on 15-2-2017.
 */
public class Drawing
{
    private String name;
    private ArrayList<DrawingItem> drawingItems = new ArrayList<>();

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

    public ArrayList<DrawingItem> getDrawingItems(){
        return drawingItems;
    }
}
