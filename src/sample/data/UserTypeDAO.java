package sample.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserTypeDAO {
    public static ObservableList<UserType> getUserTypeList() {
        UserType student = new UserType("std", "Student");
        UserType teacher = new UserType("tch", "Teacher");

        ObservableList<UserType> list //
                = FXCollections.observableArrayList(student, teacher);

        return list;
    }
}
