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
import models.Department;
import java.sql.ResultSet;

/**
 *
 * @author rebel
 */
public class DepartmentDAO {
    private Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Method ini bertujuan untuk menampilkan seluruh data yang terdapat di dalam tb_department
     * @return method ini akan mengembalikan nilai berupa department yang merupakan array list
     */
    public List<Department> getAll(){
        List<Department> department = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_department")
                    .executeQuery();
            while (resultSet.next()){
               department.add(new Department(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
    
    
      /**
     * Method ini bertujuan untuk memasukkan data kedalam database tb_department
     * @param department untuk memanggil objek department sebagai parameter untuk menginput data
     * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert berhasil dan false apabila insert gagal
     */
     public boolean insert(Department department){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_department (department_id, department_name, location_id, manager_id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, department.getDepartment_id());
            preparedStatement.setString(2, department.getDepartment_name());
            preparedStatement.setString(3, department.getLocation_id());
            preparedStatement.setString(4, department.getManager_id());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     
    /**
      * Method ini bertujuan untuk melakukan update/edit data yang telah diinputkan sebelumnya 
      * dan kemudian kembali disimpan di dalam database tb_department
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin mengupdate data
      * @param department untuk memanggil objek job sebagai parameter untuk mengupdate data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila update berhasil dan false apabila update gagal
      */
     public boolean update(String id, Department department){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_department SET department_id = ?, department_name = ?, location_id = ?, manager_id = ? WHERE department_id = ?");
            preparedStatement.setString(1, department.getDepartment_id());
            preparedStatement.setString(2, department.getDepartment_name());
            preparedStatement.setString(3, department.getLocation_id());
            preparedStatement.setString(4, department.getManager_id());
            preparedStatement.setString(5, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
     
       /**
      * Method ini bertujuan untuk menghapus data yang telah tersimpan di database tb_department berdasarkan id nya
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin delete data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila delete berhasil dan false apabila delete gagal
      */
     
     public boolean delete(String id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_department WHERE department_id = ?");
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
       * @param department untuk memanggil objek department sebagai parameter untuk insert/update data
       * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert/update berhasil dan false apabila insert/update gagal
       */
      public boolean save(Department department) {
        try {
            boolean isInsert = getById(department.getDepartment_id()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_department (department_name, location_id, manager_id, department_id) VALUES (?,?,?,?)"
                    : "UPDATE tb_department SET department_name = ?, location_id = ?, manager_id = ? WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, department.getDepartment_name());
            preparedStatement.setString(2, department.getLocation_id());
            preparedStatement.setString(3, department.getManager_id());
             preparedStatement.setString(4, department.getDepartment_id());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
      
        /**
          * Method ini bertujuan untuk menampilkan data pada tb_department berdasarkan id yang diinputkan
          * @param id untuk menentukan id yang akan menjadi parameter ketika ingin select data
          * @return method ini akan mengembalikan nilai berupa seluruh data department berdasarkan id yang diinputkan
          */
      
      public Department getById(String id) {
        Department department = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tb_department WHERE department_id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = new Department(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}
