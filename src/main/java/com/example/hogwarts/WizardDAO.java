package com.example.hogwarts;

import java.util.List;

public interface WizardDAO {
    List<Wizard> findAll();

    List<Wizard> findAllByHouseId(int HouseId);

    List<Wizard> findAllByHouseId(House house);

    Wizard save(Wizard wizard);
    Wizard update(Wizard wizard);

    void delete(Wizard wizard);
}
