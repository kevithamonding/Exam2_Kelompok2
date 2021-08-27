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
    
    public RegionDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_region")
                    .executeQuery();
            while (resultSet.next()){
                regions.add(new Region(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }
    
    public boolean insert(Region region){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_region (region_id, region_name) VALUES (?,?)");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Integer id, Region region){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_region SET region_id = ?, region_name = ? WHERE region_id = ?");
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    
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
    
//    public boolean insert_update(Region region){
//        try {
//            String insert = "INSERT INTO tb_region (region_id, region_name) VALUES (?,?)";
//            String update = "UPDATE tb_region SET region_id = ?, region_name = ? WHERE region_id = ?";
//            
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_region WHERE region_id = ?");
//            ps.setInt(1, region.getId());
//            ResultSet byId = ps.executeQuery();
//            
//            PreparedStatement preparedStatement;
//            
//            if (byId.next()) {
//                preparedStatement = connection.prepareStatement(update);
//                preparedStatement.setInt(3, region.getId());
//            } else {
//                preparedStatement = connection.prepareStatement(insert);
//            }
//            preparedStatement.setInt(1, region.getId());
//            preparedStatement.setString(2, region.getName());
//            
//            preparedStatement.execute();
//            
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    
    public boolean delete(int id){
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
