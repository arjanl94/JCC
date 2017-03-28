package Opdracht1.Classes;

import Opdracht1.*;

import java.awt.*;
import java.io.File;

/**
 * Created by Arjan on 16-2-2017.
 */
public class Image extends DrawingItem
{
    private File file;
/*
    @Override
    Rectangle boundingBox() {
        return null;
    }
*/
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

    @Override
    protected Rectangle calculateBoundingBox() {
        return null;
    }

    public Image(Opdracht1.Color color, Point anchor, File file)
    {
        super(color, anchor);
        this.file = file;
    }
}
