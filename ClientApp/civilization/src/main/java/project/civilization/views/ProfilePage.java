package project.civilization.views;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;


import javafx.scene.paint.ImagePattern;
import project.civilization.CivilizationApplication;
import project.civilization.controllers.GameController;
import project.civilization.controllers.UserController;
import project.civilization.enums.Menus;


public class ProfilePage  extends GameMenus {

    private int avatarPicCount = 4;
    private int currentPic = 1;

    @FXML
    private TextField nickname;
    @FXML
    private TextField password;
    @FXML
    private Label nicknameError;
    @FXML
    private Label passwordError;
    @FXML
    private ImageView avatarPic;

    // JFrame frame = new JFrame("Profile Picture");
    // JPanel panel = new JPanel();
    // panel.setLayout(new FlowLayout());

    // JLabel avatar=new JLabel();

    // JButton backButton = new JButton();
    // JButton selectImage=new JButton();
    // JButton nextImage=new JButton();
    // JLabel changeNickname=new JLabel();
    // JTextField newNickname=new JTextField();
    // JButton changeNicknameButton=new JButton();
    // JLabel changePassword=new JLabel();
    // JTextField newPassword=new JTextField();
    // JButton changePasswordButton=new JButton();
    // JButton deleteAccount=new JButton();


    // panel.add(backButton);
    // panel.add(selectImage);
    // panel.add(nextImage);
    // panel.add(changeNickname);
    // panel.add(newNickname);
    // panel.add(changeNicknameButton);
    // panel.add(changePassword);
    // panel.add(newPassword);
    // panel.add(changePasswordButton);
    // panel.add(deleteAccount);
    // frame.add(panel);
    // frame.setSize(400,500);
    // frame.setLocationRelativeTo(null);

    // frame.setVisible(true);

    public void initialize()
    {   
       String s = UserController.getPicUrl();
        avatarPic.setImage(new Image (s));
       // currentPic=UserController.getLoggedInUser().getPicNum();
    }
    public void selectImage(MouseEvent mouseEvent) throws IOException
    {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
        JFileChooser imageChooser=new JFileChooser();
        imageChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        imageChooser.setFileFilter(filter);
        int response=imageChooser.showOpenDialog(null);
        if(response==JFileChooser.APPROVE_OPTION)
        {
           
            File file = imageChooser.getSelectedFile();
            // ImageIcon imageTemp=new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            // // Image image=imageTemp.getImage();
            // BufferedImage picture = ImageIO.read(file);
            // Image image = SwingFXUtils.toFXImage(picture, null);
            Image selectedImage=new Image(file.getAbsolutePath());
            
            avatarPic.setImage(selectedImage);
            currentPic=1;
            UserController.setPicUrl(file.getAbsolutePath());
            //UserController.getLoggedInUser().setAvatarPic(selectedImage, 0);

        }
    }
    public void changeAvatarPic(MouseEvent mouseEvent) throws MalformedURLException {

        if (currentPic == avatarPicCount) {
            currentPic = 1;

        } else {
            currentPic++;
        }
        Image nextImage = new Image(CivilizationApplication.class.getResource("pictures/avatar/" + currentPic + ".png").toExternalForm());
        avatarPic.setImage(nextImage);
        UserController.setPicUrl(nextImage.getUrl());
        //UserController.getLoggedInUser().setAvatarPic(nextImage, currentPic);
    }
    public void changePassword(MouseEvent mouseEvent)
    {
        if(password.getText().equals(""))
        {
            passwordError.setText("Fill all fields");
            passwordError.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        UserController.changePassword(password.getText());
        Alert alert =new Alert(AlertType.INFORMATION,"Password changed successfully");
        alert.showAndWait();
    }
    public void changeNickname(MouseEvent mouseEvent)
    {
        if(nickname.getText().equals(""))
        {
            nicknameError.setText("Fill all fields");
            nicknameError.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        if(UserController.changeNickname(nickname.getText()).equals("nickname changed successfully")){
            Alert alert =new Alert(AlertType.INFORMATION,"nickname changed successfully");
            alert.showAndWait();
            return;
        }

        nicknameError.setText("This nickname is taken");
        nicknameError.setStyle("-fx-text-fill: #ff0066;");

    }
    
    public void deleteAccount(MouseEvent mouseEvent)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete your account?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            UserController.deleteAccount();
            Alert alertFinish=new Alert(AlertType.INFORMATION, "Account deleted successfully\nProceeding to login menu");
            alertFinish.showAndWait();
            CivilizationApplication.changeMenu(Menus.LOGIN);
        }
    }


    public void friendsList(MouseEvent mouseEvent) {
        CivilizationApplication.changeMenu(Menus.SEEALLFRIENDS);
    }

    public void friendsRequests(MouseEvent mouseEvent) {
        CivilizationApplication.changeMenu(Menus.FRIENDSHIPREQUESTS);
    }
}
