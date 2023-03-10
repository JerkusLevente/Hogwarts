package com.example.hogwarts;

import javafx.beans.property.*;

public class House {


    public enum HouseType{
        GRYFFINDOR("Gryffindor"),
        HUFFLEPUFF("Hufflepuff"),
        RAVENCLAW("Ravenclaw"),
        SLYTHERIN("Slytherin");

        private final StringProperty value = new SimpleStringProperty(this, "value");

        public String getValue() {
            return value.get();
        }

        public StringProperty valueProperty() {
            return value;
        }

        HouseType(String value) {
            this.value.set(value);
        }

        @Override
        public String toString() {
            return getValue();
        }
    }


    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private StringProperty Name = new SimpleStringProperty(this, "number");
    private ObjectProperty<HouseType> houseType= new SimpleObjectProperty<>(this, "houseType");

    // getters, setters

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

    public HouseType getHouseType() {
        return houseType.get();
    }

    public ObjectProperty<HouseType> houseTypeProperty() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType.set(houseType);
    }
}
