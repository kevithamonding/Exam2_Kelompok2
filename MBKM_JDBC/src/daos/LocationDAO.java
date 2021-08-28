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
import models.Location;
import java.sql.ResultSet;

/**
 *
 * @author rebel
 */
public class LocationDAO {
    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }
    
     /**
     * Method ini bertujuan untuk menampilkan seluruh data yang terdapat di dalam tb_location
     * @return method ini akan mengembalikan nilai berupa location yang merupakan array list
     */
    
      public List<Location> getAll(){
        List<Location> location = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_location")
                    .executeQuery();
            while (resultSet.next()){
               location.add(new Location(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
      
        /**
     * Method ini bertujuan untuk memasukkan data kedalam database tb_location
     * @param location untuk memanggil objek location sebagai parameter untuk menginput data
     * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert berhasil dan false apabila insert gagal
     */
      public boolean insert(Location location){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_location (location_id, street_address, postal_code, city, state_province, country_id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, location.getLocation_id());
            preparedStatement.setString(2, location.getStreet_address());
            preparedStatement.setString(3, location.getPostal_code());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getState_province());
            preparedStatement.setString(6, location.getCountry_id());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
      
      /**
      * Method ini bertujuan untuk melakukan update/edit data yang telah diinputkan sebelumnya 
      * dan kemudian kembali disimpan di dalam database tb_location
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin mengupdate data
      * @param location untuk memanggil objek location sebagai parameter untuk mengupdate data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila update berhasil dan false apabila update gagal
      */
      
        public boolean update(String id, Location location){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_location SET location_id = ?, street_address = ?, postal_code = ?, city = ?, state_province = ?, country_id = ? WHERE location_id = ?");
            preparedStatement.setString(1, location.getLocation_id());
            preparedStatement.setString(2, location.getStreet_address());
            preparedStatement.setString(3, location.getPostal_code());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getState_province());
            preparedStatement.setString(6, location.getCountry_id());
            preparedStatement.setString(7, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
          
         /**
      * Method ini bertujuan untuk menghapus data yang telah tersimpan di database tb_location berdasarkan id nya
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin delete data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila delete berhasil dan false apabila delete gagal
      */
        public boolean delete(String id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_location WHERE location_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
      }
        
       /**
       * Method ini bertujuan untuk memasukkan data kedalam database atau mengubah data yang telah ada,
       * method ini akan melakukan pengecekan berdasarkan parameter yang diinputkan, 
       * apabila data belum tersedia maka akan dilakukan insert, apabila telah tersedia akan dilakukan update
       * @param location untuk memanggil objek location sebagai parameter untuk insert/update data
       * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert/update berhasil dan false apabila insert/update gagal
       */
        public boolean save(Location location) {
        try {
            boolean isInsert = getById(location.getLocation_id()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_location (street_address, postal_code, city, state_province, country_id, location_id) VALUES (?,?,?,?,?,?)"
                    : "UPDATE tb_location SET street_address = ?, postal_code = ?, city = ?, state_province = ?, country_id = ? WHERE location_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location.getStreet_address());
            preparedStatement.setString(2, location.getPostal_code());
            preparedStatement.setString(3, location.getCity());
            preparedStatement.setString(4, location.getState_province());
            preparedStatement.setString(5, location.getCountry_id());
            preparedStatement.setString(6, location.getLocation_id());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
        
          /**
          * Method ini bertujuan untuk menampilkan data pada tb_location berdasarkan id yang diinputkan
          * @param id untuk menentukan id yang akan menjadi parameter ketika ingin select data
          * @return method ini akan mengembalikan nilai berupa seluruh data location berdasarkan id yang diinputkan
          */
          public Location getById(String id) {
        Location location = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tb_location WHERE location_id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                location = new Location(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
}
