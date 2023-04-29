package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.data.UserType;
import sample.data.UserTypeDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainForm extends GridPane {

    private final String LOGIN = "Beaver";
    private final String PASSWORD = "200820e3227815ed1756a6b531e7e0d2";

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public MainForm() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        fillForm();
    }

    private void fillForm() {
        Text sceneTitle = new Text("Welcome form");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        this.add(userName, 0, 1);

        TextField userTextField = new TextField();
        this.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        this.add(pw, 0, 2);

        PasswordField passwordField = new PasswordField();
        this.add(passwordField, 1, 2);

//        ComboBox

        Button signInBtn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signInBtn);
        this.add(hbBtn, 1, 4);

        ComboBox<UserType> userTypeComboBox = new ComboBox<UserType>();
        ObservableList<UserType> list = UserTypeDAO.getUserTypeList();

        userTypeComboBox.setItems(list);
        userTypeComboBox.getSelectionModel().select(0);

        this.add(userTypeComboBox, 0,5 );

        final Text resultLabel = new Text();
        this.add(resultLabel, 1, 6);

        signInBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (userTextField.getText().equals(LOGIN) && hashMD5(passwordField.getText()).equals(PASSWORD)) {
                    resultLabel.setFill(Color.BLUE);

                    UserType currentUserType = userTypeComboBox.getSelectionModel().getSelectedItem();
                    resultLabel.setText("Sign in success as " + currentUserType.getName());

                } else {
                    resultLabel.setFill(Color.RED);
                    resultLabel.setText("Incorrect login or password");
                }
            }
        });
    }

    private String hashMD5(String text) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int number = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[number >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[number & 0x0F];
        }
        return new String(hexChars);
    }

}
