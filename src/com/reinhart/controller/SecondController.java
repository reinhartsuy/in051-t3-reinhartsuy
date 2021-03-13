package com.reinhart.controller;

import com.reinhart.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private TableColumn<Category,String> colId;
    @FXML
    private TableColumn<Category,String> colName;
    @FXML
    private TableColumn<Category,String> colCreated;
    private Integer m = 0;
    private ObservableList<Category> categories;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        tableCategory.setItems(mainController.get);
    }
    @FXML
    private void saveAct(ActionEvent actionEvent) {
        Category category = new Category();
        category.setName(txtName.getText());
        m = m +1;
        category.setId(m);
        if (txtName.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill the field");
            alert.showAndWait();
        }else {
            mainC.add(category);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colCreated.setCellValueFactory(data -> new SimpleStringProperty());

    }
}
