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
        
        public Job getById(String id) {
        Job job = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM tb_job WHERE job_id=?");
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
