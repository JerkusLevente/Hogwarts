package com.example.hogwarts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WizardDAOImpl implements WizardDAO {


    // SQL Statements
    private static final String SELECT_ALL_WIZARDS = "SELECT * FROM WIZARDS";
    private static final String INSERT_WIZARD = "INSERT INTO WIZARDS (Name, Height, PureBlood, HouseId) VALUES (?,?,?,?)";
    private static final String UPDATE_WIZARD = "UPDATE WIZARDS SET Name=?, Height = ? WHERE ID=?";
    private static final String DELETE_WIZARD = "DELETE FROM WIZARDS WHERE ID = ?";
    private static final String SELECT_WIZARDS_BY_HOUSE_ID = "SELECT * FROM WIZARDS WHERE HouseId=?";
    private String connectionURL;

    public WizardDAOImpl() {
        connectionURL = WizardConfiguration.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Wizard> findAll() {

        List<Wizard> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_WIZARDS)
        ){

            while(rs.next()){
                Wizard wizard = new Wizard();

                wizard.setID(rs.getInt("ID"));
                wizard.setName(rs.getString("Name"));
                wizard.setHeight(rs.getInt("Height"));
                wizard.setPureBlood(rs.getInt("PureBlood"));
                wizard.setHouseId(rs.getInt("HouseId"));

                result.add(wizard);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;

    }

    @Override
    public List<Wizard> findAllByHouseId(int HouseId) {
        List<Wizard> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = c.prepareStatement(SELECT_WIZARDS_BY_HOUSE_ID)){

            statement.setInt(1, HouseId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Wizard wizard = new Wizard();
                wizard.setID(rs.getInt("ID"));
                wizard.setName(rs.getString("Name"));
                wizard.setHeight(rs.getInt("Height"));
                wizard.setPureBlood(rs.getInt("PureBlood"));
                wizard.setHouseId(rs.getInt("HouseId"));

                result.add(wizard);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Wizard> findAllByHouseId(House house) {
        return findAllByHouseId(house.getID());
    }


    @Override
    public Wizard save(Wizard wizard) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = wizard.getID() <= 0 ? c.prepareStatement(INSERT_WIZARD, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_WIZARD)
        ){
            if(wizard.getID() > 0){ // UPDATE
                stmt.setInt(5, wizard.getID());
            }

            stmt.setString(1, wizard.getName());
            stmt.setInt(2, wizard.getHeight());
            stmt.setInt(3, wizard.getPureBlood());
            stmt.setInt(4, wizard.getHouseId());


            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(wizard.getID() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    wizard.setID(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return wizard;

    }

    @Override
    public Wizard update(Wizard wizard) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = wizard.getID() <= 0 ? c.prepareStatement(INSERT_WIZARD, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_WIZARD)
        ){
            if(wizard.getID() > 0){ // UPDATE
                stmt.setInt(3, wizard.getID());
            }

            stmt.setString(1, wizard.getName());
            stmt.setInt(2, wizard.getHeight());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(wizard.getID() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    wizard.setID(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return wizard;
    }


    @Override
    public void delete(Wizard wizard) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_WIZARD);
        ) {
            stmt.setInt(1, wizard.getID());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
