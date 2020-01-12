package proyecto_arbol;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dylan
 */
public class PaneOrganizer {
    private final String nomBot = "Button";
    public Pane root(){
        VBox root=new VBox();
        StackPane botonSi=Utilities.boton("Button");
        StackPane botonNo=Utilities.boton("Button");
        
        Label SiLabel = new Label("Si");
        SiLabel.setFont(CONSTANTES.MYFONT);
        SiLabel.setTextFill(Color.WHITE);
        botonSi.getChildren().add(SiLabel);
        Label NoLabel = new Label("No");
        NoLabel.setFont(CONSTANTES.MYFONT);
        NoLabel.setTextFill(Color.WHITE);
        botonNo.getChildren().add(NoLabel);
        
        root.getChildren().addAll(botonSi,botonNo);
        
        
        
        
        return root;
    }
    
}
