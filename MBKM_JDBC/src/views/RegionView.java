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
import tools.MBKM_JDBC;

/**
 *
 * @author Asus
 */
public class RegionView {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    RegionDAO rdao = new RegionDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    int reg_id;
    String reg_name, tbl;

    public void headerRegion() {
        this.tbl = "| %-8s | %-16s |%n";

        System.out.format("+----------+------------------+%n");
        System.out.format("| RegionId | Region Name      |%n");
        System.out.format("+----------+------------------+%n");
    }

    public void InputData() {
        System.out.print("Region ID = ");
        this.reg_id = sc.nextInt();
        if (m.pil == 1 || m.pil == 2) {
            if (rdao.getById(reg_id) != null) {
                System.out.println("UPDATE DATA");
                headerRegion();
                System.out.format(tbl, rdao.getById(reg_id).getId(), rdao.getById(reg_id).getName());
                System.out.format("+----------+------------------+%n");
            }
            System.out.print("Region Name = ");
            this.reg_name = sc.next();
        }
    }

    public void Logika() {
        do {
            m.Menu();
            switch (m.pil) {
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
                    headerRegion();
                    System.out.format(tbl, rdao.getById(reg_id).getId(), rdao.getById(reg_id).getName());
                    System.out.format("+----------+------------------+%n");
                    System.out.print("Apakah ingin dihapus? (ya/tidak) ");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya")) {
                        System.out.println(
                                rdao.delete(reg_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;
                case 4:
                    headerRegion();
                    for (Region r : rdao.getAll()) {
                        System.out.format(tbl, r.getId(), r.getName());
                    }
                    System.out.format("+----------+------------------+%n");
                    break;
                case 5:
                    do {
                        InputData();
                        if (rdao.getById(reg_id) == null) {
                            System.out.println("Data tidak ada!");
                            break;
                        } else {
                            headerRegion();
                            System.out.format(tbl, rdao.getById(reg_id).getId(), rdao.getById(reg_id).getName());
                            System.out.format("+----------+------------------+%n");
                        }
                    } while (rdao.getById(reg_id) == null);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }

}
