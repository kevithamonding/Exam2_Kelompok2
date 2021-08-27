/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.DepartmentDAO;
import java.util.Scanner;
import models.Location;
import tools.DBConnection;
import tools.MBKM_JDBC;
/**
 *
 * @author rebel
 */
public class LocationView {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    DepartmentDAO ddao = new DepartmentDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    String location_id, stress_address, postal_code, city, state_province, country_id;

    public LocationView() {
    }

    public LocationView(String location_id, String stress_address, String postal_code, String city, String state_province, String country_id) {
        this.location_id = location_id;
        this.stress_address = stress_address;
        this.postal_code = postal_code;
        this.city = city;
        this.state_province = state_province;
        this.country_id = country_id;
    }
    
    public void InputData() {
        System.out.print("Location ID = ");
        this.location_id= sc.next();
        if (m.pil == 1 || m.pil == 2) {
            System.out.print("Department Name = ");
            this.department_name = sc.next();
            System.out.println("Location ID = ");
            this.location_id = sc.next();
            System.out.println("Manager ID = ");
            this.manager_id = sc.next();
            
        }
    }
    
}
