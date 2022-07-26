package project.civilization.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import project.civilization.CivilizationApplication;
import project.civilization.controllers.GameController;
import project.civilization.models.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Economic {

    @FXML
    private AnchorPane anchorPane;


    public void initialize()
    {

        ScrollPane scrollPane=new ScrollPane();
        Pane demoPane=new Pane();

        int startSize=50;
        int height=50;
        int screenWidth=600;
        int screenHeight=400;

        ArrayList<String> playerCitiesNames = GameController.getPlayerCitiesNames();
        String[] mainInfo=GameController.getPlayerMainInfo().split(" ");
        
        Label gold=new Label("gold: "+mainInfo[0]);
        Label happiness=new Label("happiness: "+mainInfo[1]+"  ");
        Label production=new Label("production: "+mainInfo[2]+"  ");
        Label population=new Label("population: "+mainInfo[3]+"  ");
        Label trophies=new Label("Trophies: "+mainInfo[4]+"  ");
        Label year=new Label("Year: "+mainInfo[5]);

        HBox hbox=new HBox(year,gold,happiness,production,population,trophies);
        demoPane.getChildren().add(hbox);
        
        for(String temp : playerCitiesNames)
        {

            Button city=new Button();
            city.setText(GameController.cityScreen(temp));



            city.setStyle("-fx-text-fill: goldenrod; -fx-background-color:#2f2f2f");
            city.setPrefWidth(screenWidth);

            city.setLayoutY(startSize);



            city.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    loadPanel(temp);
                }

            });
            city.setOnMouseEntered(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    city.setStyle("-fx-text-fill: goldenrod; -fx-background-color:#000000");

                }

            });
            city.setOnMouseExited(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    city.setStyle("-fx-text-fill: goldenrod; -fx-background-color:#2f2f2f");

                }

            });


            demoPane.getChildren().add(city);
            demoPane.setPrefHeight(screenHeight);
            demoPane.setStyle("-fx-background-color: #1a1a1a" );
            startSize+=height;
        }

        scrollPane.setMaxHeight(screenHeight);
        scrollPane.setPrefWidth(screenWidth);
        scrollPane.setLayoutY(27);
        scrollPane.setContent(demoPane);
        scrollPane.setPannable(true);

        anchorPane.getChildren().add(scrollPane);
    }


    private Node openPanel;

    public void loadPanel(String cityName)
    {
        FXMLLoader loader = new FXMLLoader(CivilizationApplication.class.getResource("fxml/panels/city-screen.fxml"));
        try {
            // notificationHistory=;
            if(openPanel!=null)
            {
                return;
            }
            CityScreen cityScreenController=new CityScreen(cityName);
            loader.setController(cityScreenController);
            openPanel= (Node)(loader.load());
            openPanel.setLayoutY(80);
            openPanel.setLayoutX(80);
            anchorPane.getChildren().add(openPanel);


            Button closeButton=new Button();
            closeButton.setLayoutX(80);
            closeButton.setLayoutY(80);
            closeButton.setPrefSize(60, 15);
            closeButton.setText("Close");
            closeButton.setStyle("-fx-background-color:black; -fx-text-fill: goldenrod");

            anchorPane.getChildren().add(closeButton);
            closeButton.setOnMouseEntered(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    closeButton.setStyle("-fx-background-color:#3a3a3a; -fx-text-fill: goldenrod");

                }

            });
            closeButton.setOnMouseExited(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    closeButton.setStyle("-fx-background-color:black; -fx-text-fill: goldenrod");

                }

            });
            closeButton.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    anchorPane.getChildren().remove(openPanel);
                    anchorPane.getChildren().remove(closeButton);
                    openPanel=null;
                }

            });
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}

