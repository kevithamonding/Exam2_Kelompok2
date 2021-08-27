/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.RegionDAO;
import java.util.Scanner;
import models.Region;
import tools.DBConnection;

/**
 *
 * @author Asus
 */
public class RegionView {

    Scanner sc = new Scanner(System.in);
    DBConnection dbc = new DBConnection();
    RegionDAO rdao = new RegionDAO(dbc.getConnection());
    int reg_id, pil;
    String reg_name;

    public RegionView() {
    }

    public RegionView(int reg_id, String reg_name) {
        this.reg_id = reg_id;
        this.reg_name = reg_name;
    }

    public void InputData() {
        System.out.print("Region ID = ");
        this.reg_id = sc.nextInt();
        if (this.pil == 1 || this.pil == 2) {
            System.out.print("Region Name = ");
            this.reg_name = sc.next();
        }
    }

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

    public void Logika() {

        do {
            Menu();
            switch (pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    rdao.save(new Region(reg_id, reg_name));
                    break;
                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    rdao.save(new Region(reg_id, reg_name));
                    break;
                case 3:
                System.out.println("== Delete ==");
                InputData();
                System.out.println(rdao.getById(reg_id).getId() + " - "
                        + rdao.getById(reg_id).getName());
                System.out.print("Apakah ingin dihapus?");
                String pil2 = sc.next();
//                rdao.delete(reg_id);
                if (pil2.equalsIgnoreCase("ya")) {
//                    rdao.delete(reg_id);
                    System.out.println(
                            rdao.delete(reg_id)
                            ? "Delete Berhasil" : "Delete Gagal"
                    );
                }
                    break;
                case 4:
                    for (Region r : rdao.getAll()) {
                        System.out.println(r.getId() + " - " + r.getName());
                    }
                    break;
                case 5:
                    do {
                        InputData();
                        if (rdao.getById(reg_id) == null) {
                            System.out.println("Data tidak ada!");
                        } else {
                            System.out.println(rdao.getById(reg_id).getId() + " - "
                                    + rdao.getById(reg_id).getName());
                        }
                    } while (rdao.getById(reg_id) == null);
                    break;
                default:
                    System.out.println("Pilihan Anda Salah");
                }
        }while (pil != 6);
    }
    
}
//        do {
//            Menu();
//            if (pil == 1) {
//                System.out.println("== Insert ==");
//                InputData();
//                rdao.save(new Region(reg_id, reg_name));
//            } else if (pil == 2) {
//                System.out.println("== Update ==");
//                InputData();
//                rdao.save(new Region(reg_id, reg_name));
//            } else if (pil == 3) {
//                System.out.println("== Delete ==");
//                InputData();
//                System.out.println(rdao.getById(reg_id).getId() + " - "
//                        + rdao.getById(reg_id).getName());
//                System.out.println("Apakah ingin dihapus?");
//                String pil2 = sc.next();
//                rdao.delete(reg_id);
////                if (pil2 == "y" || pil2 == "Y") {
////                    System.out.println(
////                            rdao.delete(reg_id)
////                            ? "Delete Berhasil" : "Delete Gagal"
////                    );
////                }
//            } else if (pil == 4) {
//                for (Region r : rdao.getAll()) {
//                    System.out.println(r.getId() + " - " + r.getName());
//                }
//            } else if (pil == 5) {
//                do {
//                    InputData();
//                    if (rdao.getById(reg_id) == null) {
//                        System.out.println("Data tidak ada!");
//                    } else {
//                        System.out.println(rdao.getById(reg_id).getId() + " - "
//                                + rdao.getById(reg_id).getName());
//                    }
//                } while (rdao.getById(reg_id) == null);
//            }
//        } while (pil != 6);
        


