/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.RegionDAO;
import java.util.Scanner;
import models.Region;
import views.RegionView;

/**
 *
 * @author Asus
 */
public class MBKM_JDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Scanner sc = new Scanner(System.in);

        //Test Connection
        System.out.println(dbc.getConnection());

        //Dependency Injection
        RegionDAO rdao = new RegionDAO(dbc.getConnection());

            
    }

}
