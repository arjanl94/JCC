package Opdracht1.Classes;

import java.io.*;

/**
 * Created by Arjan on 14-3-2017.
 */
public class SerializeDrawing {

    public SerializeDrawing(){

    }

    public Drawing load(){
        try {
            FileInputStream f = new FileInputStream("D:/Users/user/drawing.ser");
            ObjectInputStream input = new ObjectInputStream(f);
            Drawing drawing = (Drawing) input.readObject();
            input.close();
            f.close();
            return drawing;
        }
        catch(IOException i){
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException c){
            System.out.println("Class niet gevonden");
            c.printStackTrace();
            return null;
        }
    }
    public boolean save(Drawing drawing){
        if (drawing != null){
            try {
                FileOutputStream f = new FileOutputStream("D:/Users/user/drawing.ser");
                ObjectOutputStream out = new ObjectOutputStream(f);
                out.writeObject(drawing);
                out.close();
                f.close();
                System.out.printf("Drawing is opgeslagen");
            }
            catch(IOException i){
                i.printStackTrace();
            }
        }
        return false;
    }
}
