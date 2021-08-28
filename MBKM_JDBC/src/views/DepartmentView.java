/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.DepartmentDAO;
import java.util.Scanner;
import models.Department;
import tools.DBConnection;
import tools.MBKM_JDBC;

/**
 *
 * @author rebel
 */
public class DepartmentView {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    DepartmentDAO ddao = new DepartmentDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    String department_id, department_name, location_id, manager_id;

    public void InputData() {
        System.out.print("Department ID = ");
        this.department_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (ddao.getById(department_id) != null) {
                System.out.println("UPDATE DATA");
                System.out.println(ddao.getById(department_id).getDepartment_id() + " - "
                        + ddao.getById(department_id).getDepartment_name());
            }
            System.out.print("Department Name = ");
            this.department_name = sc.next();
            System.out.print("Location ID = ");
            this.location_id = sc.next();
            System.out.print("Manager ID = ");
            this.manager_id = sc.next();
        }
    }

    public void Logika() {

        do {
            m.Menu();
            switch (m.pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    ddao.save(new Department(department_id, department_name, location_id, manager_id));
                    break;
                    
                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    ddao.save(new Department(department_id, department_name, location_id, manager_id));
                    break;
                    
                case 3:
                    System.out.println("== Delete ==");
                    InputData();
                    System.out.println(ddao.getById(department_id).getDepartment_id() + " - "
                            + ddao.getById(department_id).getDepartment_name());
                    System.out.print("Apakah ingin dihapus? (ya/tidak)");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya") || pil2.equalsIgnoreCase("Ya")) {
                        System.out.println(
                                ddao.delete(department_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;

                case 4:
                    for (Department d : ddao.getAll()) {
                        System.out.println(d.getDepartment_id() + " - " + d.getDepartment_name() + " - " + "Location ID: " + d.getLocation_id() + 
                        " - " + "Manager ID: "+ d.getManager_id());
                    }
                    break;

                case 5:
                    do {
                        InputData();
                        if (ddao.getById(department_id) == null) {
                            System.out.println("Data tidak ada!");
                            break;
                        } else {
                            System.out.println(ddao.getById(department_id).getDepartment_id() + " - "
                                    + ddao.getById(department_id).getDepartment_name());
                        }
                    } while (ddao.getById(department_id) == null);
                    break;
                
                case 6:
                    break;
                    
                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }
}
