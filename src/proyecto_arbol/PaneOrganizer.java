package proyecto_arbol;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    Label labelP;
    GMind arbol;
    BorderPane root0;ImageView img;
    
    public StackPane root(){
        arbol=new GMind();
        
        
        root=new StackPane();
        ImageView fondo=new ImageView(new Image("recursos/imagenes/desierto.jpg"));
        ImageView dialog=new ImageView(new Image("recursos/imagenes/dialogo.png"));
        img=new ImageView(new Image("recursos/imagenes/akinatorp.png"));
        img.setLayoutX(0);
        img.setLayoutY(0);
        dialog.setFitHeight(100);
        dialog.setFitWidth(500);
        Label lab=new Label(arbol.getCurrent());
        lab.setFont(CONSTANTES.MYFONT);
        StackPane sp1=new StackPane();
        sp1.getChildren().addAll(dialog,lab);
        
        labelP=new Label();
        fondo.setFitHeight(900);
        fondo.setFitWidth(900);
        root0=new BorderPane();
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
        root0.setCenter(sp1);
        root0.setRight(img);
        root.getChildren().addAll(fondo,root0);
        
        botonSi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
               if(!arbol.hasnext()){
                   img.setImage(new Image("recursos/imagenes/akinatorf.png"));
                   lab.setText("El genio lo ha hecho de nuevo.");
                   root0.setBottom(null);
               }else{
                   arbol.preguntar("Si");
                   lab.setText(arbol.getCurrent());
               }
            }
        });
        botonNo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                arbol.preguntar("No");
                lab.setText(arbol.getCurrent());
            }
        });
        
        
        
        
        return root;
    }
}
