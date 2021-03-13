package com.reinhart.controller;

import com.reinhart.Main;
import com.reinhart.entity.Category;
import com.reinhart.entity.Item;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox<Category> coboxCategory;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Item> tableToko;
    @FXML
    private TableColumn<Item,String> colId;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item,String> colPrice;
    @FXML
    private TableColumn<Item,String> colCategory;
    @FXML
    private TableColumn<Item,String> colExpiredDate;
    @FXML
    private BorderPane rootBpane;
    private ObservableList<Category> categories;
    private ObservableList<Item> items;
    private Integer m = 0;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnsave;

    @FXML
    private void categoryAct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/second_layout.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle("Second Stage");
        secondStage.setScene(new Scene(root));
        secondStage.initOwner(rootBpane.getScene().getWindow());
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.show();
    }

    @FXML
    private void resetAct(ActionEvent actionEvent) {
        resetForm();
    }

    private void resetForm() {
        txtName.clear();
        txtPrice.clear();
        coboxCategory.setValue(null);
    }

    @FXML
    private void updateAct(ActionEvent actionEvent) {

        btnupdate.setDisable(false);
        Item item = tableToko.getSelectionModel().getSelectedItem();
        item.setName(txtName.getText());
        item.setPrice(Integer.parseInt(txtPrice.getText().trim()));
        item.setCategory(coboxCategory.getValue());
        item.setExpiredDate(date.getValue());
        tableToko.refresh();
    }

    @FXML
    private void saveAct(ActionEvent actionEvent) {
        Item item = new Item();
        m = m+1;
        item.setId(m);
        item.setName(txtName.getText());
        item.setPrice(Integer.parseInt(txtPrice.getText().trim()));
        item.setCategory(coboxCategory.getValue());
        item.setExpiredDate(date.getValue());
        resetForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnupdate.setDisable(true);
        categories = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        coboxCategory.setItems(categories);
        tableToko.setItems(items);
        colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colPrice.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPrice())));
        colCategory.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory().getName()));
        colExpiredDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getExpiredDate().toString()));
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Item item =tableToko.getSelectionModel().getSelectedItem();
        txtName.setText(item.getName().trim());
        txtPrice.setText(item.getPrice().toString());
        btnupdate.setDisable(false);

    }
}
