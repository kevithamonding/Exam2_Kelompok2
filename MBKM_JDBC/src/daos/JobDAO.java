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
import models.Job;
import java.sql.ResultSet;
/**
 *
 * @author rebel
 */
public class JobDAO {
    private Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }
    /**
     * Method ini bertujuan untuk menampilkan seluruh data yang terdapat di dalam tb_job
     * @return method ini akan mengembalikan nilai berupa job yang merupakan array list
     */
    public List<Job> getAll(){
        List<Job> job = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_job")
                    .executeQuery();
            while (resultSet.next()){
               job.add(new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }
    
    /**
     * Method ini bertujuan untuk memasukkan data kedalam database tb_job
     * @param job untuk memanggil objek job sebagai parameter untuk menginput data
     * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert berhasil dan false apabila insert gagal
     */
     public boolean insert(Job job){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_job (job_id, job_title, min_salary, max_salary) VALUES (?,?,?,?)");
            preparedStatement.setString(1, job.getJob_id());
            preparedStatement.setString(2, job.getJob_title());
            preparedStatement.setDouble(3, job.getMin_salary());
            preparedStatement.setDouble(4, job.getMax_salary());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     /**
      * Method ini bertujuan untuk melakukan update/edit data yang telah diinputkan sebelumnya 
      * dan kemudian kembali disimpan di dalam database tb_job
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin mengupdate data
      * @param job untuk memanggil objek job sebagai parameter untuk mengupdate data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila update berhasil dan false apabila update gagal
      */
     public boolean update(String id, Job job){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_job SET job_id = ?, job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?");
            preparedStatement.setString(1, job.getJob_id());
            preparedStatement.setString(2, job.getJob_title());
            preparedStatement.setDouble(3, job.getMin_salary());
            preparedStatement.setDouble(4, job.getMax_salary());
            preparedStatement.setString(5, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
     
     /**
      * Method ini bertujuan untuk menghapus data yang telah tersimpan di database tb_job berdasarkan id nya
      * @param id untuk menentukan id yang akan menjadi parameter ketika ingin delete data
      * @return method ini akan mengembalikan tipe data boolean berupa true apabila delete berhasil dan false apabila delete gagal
      */
     
      public boolean delete(String id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_job WHERE job_id = ?");
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
       * @param job untuk memanggil objek job sebagai parameter untuk insert/update data
       * @return method ini akan mengembalikan tipe data boolean berupa true apabila insert/update berhasil dan false apabila insert/update gagal
       */
      
         public boolean save(Job job) {
        try {
            boolean isInsert = getById(job.getJob_id()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_job (job_title, min_salary, max_salary, job_id) VALUES (?,?,?,?)"
                    : "UPDATE tb_job SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, job.getJob_title());
            preparedStatement.setDouble(2, job.getMin_salary());
            preparedStatement.setDouble(3, job.getMax_salary());
            preparedStatement.setString(4, job.getJob_id());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
        
         /**
          * Method ini bertujuan untuk menampilkan data pada tb_job berdasarkan id yang diinputkan
          * @param id untuk menentukan id yang akan menjadi parameter ketika ingin select data
          * @return method ini akan mengembalikan nilai berupa seluruh data job berdasarkan id yang diinputkan
          */
        public Job getById(String id) {
        Job job = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tb_job WHERE job_id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                job = new Job(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }
    
    
    
}
