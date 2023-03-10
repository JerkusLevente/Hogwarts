package com.example.hogwarts;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    WizardDAO dao = new WizardDAOImpl();

    @FXML
    public void onAddNewWizard(){
        FXMLLoader fxmlLoader = HelloApplication.loadFXML(("addWizard.fxml"));
        AddWizardController controller = fxmlLoader.getController();
        controller.setWizard(new Wizard(),true);
    }

    @FXML
    private void setEditWizard(Wizard w) {
        FXMLLoader fxmlLoader = HelloApplication.loadFXML(("addWizard.fxml"));
        AddWizardController controller = fxmlLoader.getController();
        controller.setWizard( w,false);
    }

    @FXML
    public void onExit(){
        Platform.exit();
    }

    @FXML
    private TableView<Wizard> wizardTable;

    @FXML
    private TableColumn<Wizard, String> nameColumn;
    @FXML
    private TableColumn<Wizard, String> heightColumn;
    @FXML
    private TableColumn<Wizard, String> pureBloodColumn;
    @FXML
    private TableColumn<Wizard, String> houseIdColumn;
    @FXML
    private TableColumn<Wizard, Void> editColumn;


    private void refreshTable() {
        wizardTable.getItems().setAll(dao.findAll());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("Height"));
        pureBloodColumn.setCellValueFactory(new PropertyValueFactory<>("PureBlood"));
        houseIdColumn.setCellValueFactory(new PropertyValueFactory<>("HouseId"));

        editColumn.setCellFactory(param -> new TableCell<>(){
            private final Button editBtn = new Button("Edit");
            {
                editBtn.setOnAction(event -> {
                    Wizard wizard = getTableRow().getItem();
                    setEditWizard(wizard);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });
    }

    public void listRavenclaw(ActionEvent actionEvent) {
        wizardTable.getItems().setAll(dao.findAllByHouseId(3));
    }

    public void listSyltherin(ActionEvent actionEvent) {
        wizardTable.getItems().setAll(dao.findAllByHouseId(4));
    }

    public void listHufflepuff(ActionEvent actionEvent) {
        wizardTable.getItems().setAll(dao.findAllByHouseId(2));
    }

    public void listGryffindor(ActionEvent actionEvent) {
        wizardTable.getItems().setAll(dao.findAllByHouseId(1));
    }

    public void listAll(ActionEvent actionEvent) {
        refreshTable();
    }
}