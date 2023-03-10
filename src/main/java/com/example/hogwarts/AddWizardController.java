package com.example.hogwarts;
import java.net.URL;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class AddWizardController implements Initializable {

    private Stage stage;
    private boolean editEGlobal;
    private Wizard wizard;
    private WizardDAO wizardDAO = new WizardDAOImpl();


    @FXML
    private TextField name;
    @FXML
    private Spinner<Integer> heightSpinner ;
    @FXML
    private CheckBox pureBlood;

    @FXML private TableColumn<Wizard, Void> editColumn;



    public void setWizard(Wizard wizard, boolean editE) {
        editEGlobal=editE;
        this.wizard = wizard;
        int pb;
        if(pureBlood.isSelected()){
            pb=1;
        }else{
            pb = 0;
        }
        name.textProperty().bindBidirectional(wizard.nameProperty());
        heightSpinner.getValueFactory().valueProperty().bindBidirectional(wizard.heightProperty().asObject()); ;
        wizard.setPureBlood(pb);
        int randomNum = ThreadLocalRandom.current().nextInt(4-1+1)+1;
        wizard.setHouseId(randomNum);
    }


    @FXML public void onCancel(){
        HelloApplication.loadFXML("hello-view.fxml");
    }

    @FXML public void onSave(){

        if(editEGlobal){
             wizard = wizardDAO.save(wizard);
             String house="empty";
             switch (wizard.getHouseId()){
                 case 1:
                     house="Gryffindor";
                     break;
                 case 2:
                     house="Hufflepuff";
                     break;
                 case 3:
                     house="Ravenclaw";
                     break;
                 case 4:
                     house="Slythering";
                     break;
             }
             Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, wizard.getName() + " a " + house + " házba került.", ButtonType.OK);
             confirm.showAndWait().ifPresent(buttonType -> {
                 if(buttonType.equals(ButtonType.OK)){
                 }
             });
         }else{
             wizard = wizardDAO.update(wizard);
         }

        HelloApplication.loadFXML("hello-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heightSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                100, 300, 150));

    }
}
