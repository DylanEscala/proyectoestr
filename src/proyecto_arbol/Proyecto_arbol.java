/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_arbol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Familia
 */public class Proyecto_arbol extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PaneOrganizer pn=new PaneOrganizer();
        pn.menu();
        Scene scena=new Scene(pn.getroot(),800,500);
        primaryStage.setScene(scena);
        primaryStage.show();
    }
    
}
