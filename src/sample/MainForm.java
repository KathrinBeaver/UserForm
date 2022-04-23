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

public class MainForm extends GridPane {

    private final String LOGIN = "Beaver";
    private final String PASSWORD = "qwe123";

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
                if (userTextField.getText().equals(LOGIN) && passwordField.getText().equals(PASSWORD)) {
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

}
