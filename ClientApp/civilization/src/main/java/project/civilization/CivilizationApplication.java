package project.civilization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.civilization.enums.Menus;
import project.civilization.views.Music;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class CivilizationApplication extends Application {
    public static Stage stages;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;
    public static AnchorPane chatPane;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML(Menus.LOGIN);
        //Parent root = loadMapForTest();
        stages = stage;
        assert root != null;
        Scene scene = new Scene(root);
        stage.setTitle("Civilization");
        stage.setScene(scene);
        stage.show();
        //Music.addMusic("songs/start.mp3");
    }

    //initialize server and stream
    public static void initializeNetwork() {
        try {
            Socket socket = new Socket("localhost", 772);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeNetwork();
        launch();
    }

    public static void changeMenu(Menus menuName) {
        Parent root = loadFXML(menuName);
        assert root != null;
        Scene scene = new Scene(root);
        if (menuName.equals(Menus.PUBLICCHAT)) {
            chatPane = (AnchorPane) root;
        }
        stages.setScene(scene);
    }

    public static void loadMapForTest() {
        try {
            URL address = new URL(CivilizationApplication.class.getResource("fxml/" + "map-page" + ".fxml").toExternalForm());
            FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Parent loadFXML(Menus menuName) {
        if (menuName == Menus.GAME) {
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