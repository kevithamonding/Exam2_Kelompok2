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
    Scanner sc = new Scanner(System.in);
    public int pil;

    public void Menu() {
        System.out.println("================== MENU CRUD ==================");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. View All");
        System.out.println("5. Search by Id");
        System.out.println("6. Back to Main Menu");
        System.out.println("===============================================");
        System.out.print("Masukkan pilihan : ");
        this.pil = sc.nextInt();
        System.out.println("===============================================");

    }

    public static void main(String[] args) {

        DBConnection dbc = new DBConnection();
        Scanner sc = new Scanner(System.in);

        int piltabel;

        //Test Connection
        System.out.println(dbc.getConnection());

        //Dependency Injection
        RegionDAO rdao = new RegionDAO(dbc.getConnection());

        do {
            System.out.println("================= MENU UTAMA =================");
            System.out.println("1. REGION");
            System.out.println("2. COUNTRY");
            System.out.println("3. LOCATION");
            System.out.println("4. DEPARTMENT");
            System.out.println("5. JOB");
            System.out.println("6. EMPLOYEE");
            System.out.println("7. Exit");
            System.out.println("==============================================");
            System.out.print("Masukkan pilihan : ");
            piltabel = sc.nextInt();
            System.out.println("==============================================");

            switch (piltabel) {
                case 1:
                    RegionView region = new RegionView();
                    region.Logika();
                    break;
                case 2:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    throw new AssertionError();
            }
        } while (piltabel != 7);

    }

}
