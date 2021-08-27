/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Country;

/**
 *
 * @author Asus
 */
public class CountryDAO {
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_country")
                    .executeQuery();
            while (resultSet.next()) {
                countries.add(new Country(resultSet.getString(1),
                                          resultSet.getString(2),
                                          resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public boolean save(Country country) {
        try {
            boolean isInsert = getById(country.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_country(country_name, region_id, country_id) VALUES(?,?,?)"
                    : "UPDATE tb_country SET country_name = ?, region_id = ? WHERE country_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getRegionId());
            preparedStatement.setString(3, country.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_country WHERE country_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Country getById(String id) {
        Country country = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tb_country WHERE country_id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country = new Country(resultSet.getString(1),
                                      resultSet.getString(2),
                                      resultSet.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }
}
