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

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tekenapplicatie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}