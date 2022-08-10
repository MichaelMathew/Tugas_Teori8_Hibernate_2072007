package com.example.tugas_teori8_hibernate_2072007.Controller;

import com.example.tugas_teori8_hibernate_2072007.dao.UserDao;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.tugas_teori8_hibernate_2072007.model.User;

public class UTSController2 {
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void submit(ActionEvent actionEvent) {
        UserDao dao = new UserDao();
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Please Fill all the field", ButtonType.OK);
            alert.show();
        } else {
            User u = new User();
            u.setIdUser(0);
            u.setUserName(txtUserName.getText());
            u.setUserPassword(txtPassword.getText());
            dao.addData(u);
            txtUserName.getScene().getWindow().hide();
        }
    }
}
