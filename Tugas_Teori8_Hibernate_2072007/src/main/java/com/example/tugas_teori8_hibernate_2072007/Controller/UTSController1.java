package com.example.tugas_teori8_hibernate_2072007.Controller;

import com.example.tugas_teori8_hibernate_2072007.MovieApplication;
import com.example.tugas_teori8_hibernate_2072007.dao.MovieDao;
import com.example.tugas_teori8_hibernate_2072007.dao.UserDao;
import com.example.tugas_teori8_hibernate_2072007.dao.WListDao;
import com.example.tugas_teori8_hibernate_2072007.model.Movie;
import com.example.tugas_teori8_hibernate_2072007.model.User;
import com.example.tugas_teori8_hibernate_2072007.model.Watchlist;
import com.example.tugas_teori8_hibernate_2072007.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UTSController1 {
    public ComboBox<String> cmbGenre;
    public ListView<User> lvUser;
    public TableView<Movie> table1;
    public TableView<Watchlist> table2;
    public TableColumn<String , Movie> ColTitle1;
    public TableColumn<String , Movie> ColGenre;
    public TableColumn<Integer, Movie> ColDuration;
    public TableColumn<String, Movie> ColTitle2;
    public TableColumn<Integer, Watchlist> ColLastWatch;
    public TableColumn<Integer, Watchlist> ColFav;
    private Stage stage;
    ObservableList<Movie> mList_tampilan;
    ObservableList<User> uList_tampilan;
    ObservableList<Watchlist> wList_tampilan;



    public void initialize() throws IOException {
        stage = new Stage();
        ShowData();
    }

    public void ShowData() {
        MovieDao dao = new MovieDao();
        mList_tampilan = FXCollections.observableArrayList(dao.getData());
        table1.setItems(mList_tampilan);
        ColTitle1.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ColGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        ColDuration.setCellValueFactory(new PropertyValueFactory<>("Durasi"));
        UserDao dao1 = new UserDao();
        uList_tampilan = FXCollections.observableArrayList(dao1.getData());
        lvUser.setItems(uList_tampilan);
        ObservableList<String> genre = FXCollections.observableArrayList("All","Action","Musical","Comedy","Animated","Fantasy","Drama","Mystery","Thriller","Horror");
        cmbGenre.setItems(genre);
        cmbGenre.getSelectionModel().select(0);
    }
    public void changeCombo(ActionEvent actionEvent) {
        MovieDao dao = new MovieDao();
        mList_tampilan.clear();
        if(cmbGenre.getSelectionModel().getSelectedItem() == "All"){
            mList_tampilan.addAll(dao.getData());
        } else {
            mList_tampilan.addAll(dao.FilterData(cmbGenre.getSelectionModel().getSelectedItem()));
        }
    }

    public void AddUserAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(MovieApplication.class.getResource("UTSSecondPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 333, 168);

        stage.setTitle("modal");
        stage.setScene(scene);
        stage.showAndWait();

        ShowData();
    }

    public void DelUserAction(ActionEvent actionEvent) {
        ObservableList<User> SelectedUser;
        SelectedUser = lvUser.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        UserDao dao = new UserDao();
        if (alert.getResult() == ButtonType.OK) {
            for (User u : SelectedUser) {
                dao.deleteData(u);
            }
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Success Delete ygy", ButtonType.OK);
            alert2.showAndWait();
        }
        ShowData();
    }

    public void printReport(ActionEvent actionEvent) {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Movie.jasper",param,conn);
            JasperViewer viewer = new JasperViewer(jp,false);
            viewer.setTitle("laporan movies");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void SelectedList(MouseEvent mouseEvent) {
        WListDao dao = new WListDao();
        wList_tampilan = FXCollections.observableArrayList(dao.SelectedList(lvUser.getSelectionModel().getSelectedItem()));
        table2.setItems(wList_tampilan);
        ColTitle2.setCellValueFactory(new PropertyValueFactory<>("movieByMovieIdMovie"));
        ColLastWatch.setCellValueFactory(new PropertyValueFactory<>("DurationAndLastWatch"));
        ColFav.setCellValueFactory(new PropertyValueFactory<>("TrueOrFalse"));
    }
}
