package com.example.hogwarts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Wizard {

    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private StringProperty Name = new SimpleStringProperty(this, "Name");
    private IntegerProperty Height = new SimpleIntegerProperty(this, "Height");
    private IntegerProperty PureBlood = new SimpleIntegerProperty(this, "PureBlood");

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public int getHeight() {
        return Height.get();
    }

    public IntegerProperty heightProperty() {
        return Height;
    }

    public void setHeight(int height) {
        this.Height.set(height);
    }

    public int getPureBlood() {
        return PureBlood.get();
    }

    public IntegerProperty pureBloodProperty() {
        return PureBlood;
    }

    public void setPureBlood(int pureBlood) {
        this.PureBlood.set(pureBlood);
    }

    public int getHouseId() {
        return HouseId.get();
    }

    public IntegerProperty houseIdProperty() {
        return HouseId;
    }

    public void setHouseId(int houseId) {
        this.HouseId.set(houseId);
    }

    private IntegerProperty HouseId = new SimpleIntegerProperty(this, "HouseId");



}
