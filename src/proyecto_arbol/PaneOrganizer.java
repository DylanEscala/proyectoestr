package proyecto_arbol;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    BorderPane root0;
    ImageView img;

    public PaneOrganizer() {
        arbol = new GMind();
        img = new ImageView();
        root0 = new BorderPane();
        root = new StackPane();
        ImageView fondo = new ImageView(new Image("recursos/imagenes/desierto.jpg"));
        fondo.setFitHeight(900);
        fondo.setFitWidth(900);
        root.getChildren().addAll(fondo, root0);
    }

    public void root() {

        ImageView dialog = new ImageView(new Image("recursos/imagenes/dialogo.png"));
        img.setImage(new Image("recursos/imagenes/akinatorp.png"));
        dialog.setFitHeight(100);
        dialog.setFitWidth(500);
        Label lab = new Label(arbol.getCurrent());
        lab.setFont(CONSTANTES.MYFONT);
        StackPane sp1 = new StackPane();
        sp1.getChildren().addAll(dialog, lab);

        labelP = new Label();
        StackPane botonSi = Utilities.boton(nomBot);
        StackPane botonNo = Utilities.boton(nomBot);


        Label SiLabel = new Label("Si");
        SiLabel.setFont(CONSTANTES.MYFONT);
        SiLabel.setTextFill(Color.WHITE);
        botonSi.getChildren().add(SiLabel);
        Label NoLabel = new Label("No");
        NoLabel.setFont(CONSTANTES.MYFONT);
        NoLabel.setTextFill(Color.WHITE);
        botonNo.getChildren().add(NoLabel);
        HBox center = new HBox();
        center.getChildren().addAll(botonSi, botonNo);
        center.setSpacing(30);
        center.setAlignment(Pos.TOP_CENTER);
        root0.setBottom(center);
        root0.setCenter(sp1);

        botonSi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (!arbol.hasnext()) {
                    img.setImage(new Image("recursos/imagenes/akinatorf.png"));
                    lab.setText("El genio lo ha hecho de nuevo.");
                    StackPane spn = Utilities.boton(nomBot);
                    Label menu = new Label("Menu");
                    menu.setFont(CONSTANTES.MYFONT);
                    menu.setTextFill(Color.WHITE);
                    spn.getChildren().add(menu);
                    spn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent arg0) {
                            Thread n = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            menu();
                                        }
                                    });
                                }
                            });
                            n.start();
                        }
                    });

                    root0.setBottom(null);
                    root0.setBottom(spn);
                    arbol.restart();
                } else {
                    arbol.preguntar("Si");
                    lab.setText(arbol.getCurrent());
                }
            }
        });
        botonNo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (!arbol.hasnext()) {
                    img.setImage(new Image("recursos/imagenes/akinatorsalty.png"));
                    SeccionFormulario preg = new SeccionFormulario("Que pregunta ayuda a difenciar entre el animal que pensaste y " + arbol.getCurrent().substring(4));
                    SeccionFormulario resp = new SeccionFormulario("Nombre de animal que pensabas");
                    StackPane botong = Utilities.boton(nomBot);
                    Label guardar = new Label("Guardar");
                    botong.getChildren().add(guardar);
                    guardar.setFont(CONSTANTES.MYFONT);
                    guardar.setTextFill(Color.WHITE);
                    VBox vb = new VBox();
                    vb.getChildren().addAll(preg.getRoot(), resp.getRoot());
                    vb.setAlignment(Pos.CENTER);
                    root0.setCenter(vb);
                    root0.setBottom(botong);
                    botong.setOnMouseClicked((e) -> {
                        if (preg.getCampo().getText().equals("") || resp.getCampo().getText().equals("")) {
                            preg.activarError("Llenar los campos");
                            resp.activarError("Llenar los campos");
                        } else {
                            arbol.anadir(preg.getCampo().getText(), resp.getCampo().getText());
                            arbol.restart();
                            Thread n = new Thread(new Runnable() {
                              
                                @Override
                                public void run() {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            menu();
                                        }
                                    });
                                }
                            });
                            n.start();
                            arbol.guardarArbolito();
                        }
                    }
                    );

                } else {
                    arbol.preguntar("No");
                    lab.setText(arbol.getCurrent());
                }
            }
        });
    }

    public void menu() {

        StackPane botong = Utilities.boton(nomBot);
        Label guardar = new Label("Comenzar");
        botong.getChildren().add(guardar);
        guardar.setFont(CONSTANTES.MYFONT);
        guardar.setTextFill(Color.WHITE);
        root0.setCenter(botong);
        img.setImage(new Image("recursos/imagenes/icono.png"));
        img.setFitHeight(400);
        img.setFitWidth(400);
        root0.setRight(img);
        botong.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                Thread n = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                root();
                            }
                        });
                    }
                });
                n.start();
            }
        });
        root0.setBottom(null);

    }

    public StackPane getroot() {
        return root;
    }
}
