/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import models.Region;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public class RegionDAO {

    private Connection connection;

    public RegionDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method ini berfungsi untuk mengambil data dari dalam database dan
     * ditampung dalam sebuah objek ArrayList
     *
     * @return objek yang berisi data yang telah diambil
     */
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_region")
                    .executeQuery();
            while (resultSet.next()) {
                regions.add(new Region(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

    /**
     * Method ini berfungsi untuk memasukan data baru atau mengubah data yang
     * sudah ada kedalam database dan akan dilakukan pengecekan, jika data sudah
     * ada maka akan melakukan Update dan jika belum ada akan melakukan Insert
     *
     * @param region untuk menentukan objek yang berisi nilai masukan untuk
     * dimasukkan ke dalam database
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
    public boolean save(Region region) {
        try {
            boolean isInsert = getById(region.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_region(region_name, region_id) VALUES(?,?)"
                    : "UPDATE tb_region SET region_name = ? WHERE region_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getId());
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
     * @param id untuk menentukan id mana yang datanya akan dihapus
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_region WHERE region_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method ini berfungsi untuk mengambil data tertentu dari dalam database berdasarkan
     * parameter id yang dimasukkan, dan ditampung dalam sebuah objek
     *
     * @param id untuk menentukan id mana yang datanya akan diambil
     * @return objek yang berisi data (berdasarkan id) yang telah diambil
     */
    public Region getById(int id) {
        Region region = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tb_region WHERE region_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                region = new Region(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }
}
