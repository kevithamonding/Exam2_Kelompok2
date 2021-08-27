/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.RegionDAO;
import daos.JobDAO;
import daos.DepartmentDAO;
import java.util.Scanner;
import models.Region;
import models.Job;
import models.Department;
import views.RegionView;
import views.JobView;
import views.DepartmentView;

/**
 *
 * @author Asus
 */
public class MBKM_JDBC {

    /**
     * @param args the command line arguments
     */  Scanner sc = new Scanner(System.in);
    public int reg_id, pil ;
    String reg_name;


    public void Menu() {
        System.out.println("MENU");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. View All");
        System.out.println("5. Search by Id");
        System.out.println("6. Exit");
        System.out.print("Masukkan pilihan : ");
        this.pil = sc.nextInt();
    }
    
//    void MainMenu(){
//        System.out.println("MENU");
//        System.out.println("1. Insert");
//        System.out.println("2. Update");
//        System.out.println("3. Delete");
//        System.out.println("4. View All");
//        System.out.println("5. Search by Id");
//        System.out.println("6. Exit");
//        System.out.print("Masukkan pilihan : ");
//    }
    
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Scanner sc = new Scanner(System.in);

        //Test Connection
        System.out.println(dbc.getConnection());

        //Dependency Injection
//        RegionDAO rdao = new RegionDAO(dbc.getConnection());
//        JobDAO jdao = new JobDAO(dbc.getConnection());

//        RegionView region = new RegionView();
//        region.Logika();

//          JobView job = new JobView();
//          job.Logika();

//          DepartmentView department = new DepartmentView();
//          department.Logika();
          
            
    }

}
