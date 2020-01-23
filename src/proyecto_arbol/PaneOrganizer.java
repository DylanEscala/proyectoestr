package proyecto_arbol;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    StackPane root;
    public StackPane root(){
        root=new StackPane();
        ImageView fondo=new ImageView(new Image("recursos/imagenes/desierto.jpg"));
        fondo.setFitHeight(900);
        fondo.setFitWidth(900);
        BorderPane root0=new BorderPane();
        StackPane botonSi=Utilities.boton(nomBot);
        StackPane botonNo=Utilities.boton(nomBot);
//        Button botonSi=new Button("Si");
//        Button botonNo=new Button("No");
        VBox root01=new VBox();
        Label SiLabel = new Label("Si");
        SiLabel.setFont(CONSTANTES.MYFONT);
        SiLabel.setTextFill(Color.WHITE);
        botonSi.getChildren().add(SiLabel);
        Label NoLabel = new Label("No");
        NoLabel.setFont(CONSTANTES.MYFONT);
        NoLabel.setTextFill(Color.WHITE);
        botonNo.getChildren().add(NoLabel);
        HBox center=new HBox();
        center.getChildren().addAll(botonSi,botonNo);
        center.setSpacing(30);
        center.setAlignment(Pos.TOP_CENTER);
        root0.setBottom(center);
        
        root.getChildren().addAll(fondo,root0);
        root.getChildren().addAll(botonSi,botonNo);
        return root;
    }
}
