package project.civilization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.civilization.controllers.UserController;
import project.civilization.enums.Menus;
import project.civilization.views.ChatMenu;
import project.civilization.views.LoginMenu;
import project.civilization.views.Menu;
import project.civilization.views.Music;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class CivilizationApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = loadFXML(Menus.GAMEMenu);
        //TODO:
        Parent root = loadFXML(Menus.CHAT);
        ChatMenu.setPane((BorderPane) root);

        assert root != null;
        Scene scene = new Scene(root );
        CivilizationApplication.scene = scene;
        stage.setTitle("Civilization");
        stage.setScene(scene);
        stage.show();
        Music.addMusic("songs/start.mp3");
    }

    public static void main(String[] args) {
        launch();
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu();
        UserController.importSavedUsers();
        loginMenu.run(scanner);
    }
    public static void changeMenu(Menus menuName){
        Parent root = loadFXML(menuName);
        if (menuName.equals("chat-page"))
            ChatMenu.setPane((BorderPane) root);
        CivilizationApplication.scene.setRoot(root);
    }

    private static Parent loadFXML(Menus menuName){
        if(menuName == Menus.GAME){
            Music.addMusic("songs/MUS_Genie_A.wav");
        }
        try {
            URL address = new URL(CivilizationApplication.class.getResource("fxml/" + menuName.getCharacter() + ".fxml").toExternalForm());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}