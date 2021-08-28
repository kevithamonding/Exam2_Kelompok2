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

    /**
     * Method ini berfungsi untuk mengambil data dari dalam database dan
     * ditampung dalam sebuah objek ArrayList
     *
     * @return objek yang berisi data yang telah diambil
     */
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

    /**
     * Method ini berfungsi untuk memasukan data baru atau mengubah data yang
     * sudah ada kedalam database dan akan dilakukan pengecekan, jika data sudah
     * ada maka akan melakukan Update dan jika belum ada akan melakukan Insert
     *
     * @param country untuk menentukan objek yang berisi nilai masukan untuk
     * dimasukkan ke dalam database
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
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

    /**
     * Method ini berfungsi untuk menghapus data tertentu dari dalam database
     * berdasarkan parameter id yang dimasukkan
     *
     * @param id untuk menentukan id mana yang datanya akan dihapus
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
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

    /**
     * Method ini berfungsi untuk mengambil data tertentu dari dalam database
     * berdasarkan parameter id yang dimasukkan, dan ditampung dalam sebuah
     * objek
     *
     * @param id untuk menentukan id mana yang datanya akan diambil
     * @return objek yang berisi data (berdasarkan id) yang telah diambil
     */
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
