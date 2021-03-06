/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.CountryDAO;
import java.util.Scanner;
import models.Country;
import tools.DBConnection;
import tools.MBKM_JDBC;

/**
 *
 * @author Asus
 */
public class CountryView {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    CountryDAO cdao = new CountryDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    int region_id;
    String country_id, country_name, tbl;

    public void headerRegion() {
        this.tbl = "| %-9s | %-17s | %-9s |%n";

        System.out.format("+-----------+-------------------+-----------+%n");
        System.out.format("| CountryId | Country Name      | RegionId  |%n");
        System.out.format("+-----------+-------------------+-----------+%n");
    }

    public void InputData() {
        System.out.print("Country ID = ");
        this.country_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (cdao.getById(country_id) != null) {
                System.out.println("UPDATE DATA");
                headerRegion();
                System.out.format(tbl, cdao.getById(country_id).getId(), cdao.getById(country_id).getName(),
                        cdao.getById(country_id).getRegionId());
                System.out.format("+-----------+-------------------+-----------+%n");
            }
            System.out.print("Country Name = ");
            this.country_name = sc.next();
            System.out.print("Region ID = ");
            this.region_id = sc.nextInt();
        }
    }

    public void Logika() {
        do {
            m.Menu();
            switch (m.pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    cdao.save(new Country(country_id, country_name, region_id));
                    break;
                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    cdao.save(new Country(country_id, country_name, region_id));
                    break;
                case 3:
                    System.out.println("== Delete ==");
                    InputData();
                    headerRegion();
                    System.out.format(tbl, cdao.getById(country_id).getId(), cdao.getById(country_id).getName(),
                            cdao.getById(country_id).getRegionId());
                    System.out.format("+-----------+-------------------+-----------+%n");
                    System.out.print("Apakah ingin dihapus? (ya/tidak) ");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya")) {
                        System.out.println(
                                cdao.delete(country_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;
                case 4:
                    headerRegion();
                    for (Country c : cdao.getAll()) {
                        System.out.format(tbl, c.getId(), c.getName(), c.getRegionId());
                    }
                    System.out.format("+-----------+-------------------+-----------+%n");
                    break;
                case 5:
                    do {
                        InputData();
                        if (cdao.getById(country_id) == null) {
                            System.out.println("Data tidak ada!");
                            break;
                        } else {
                            headerRegion();
                            System.out.format(tbl, cdao.getById(country_id).getId(), cdao.getById(country_id).getName(),
                                    cdao.getById(country_id).getRegionId());
                            System.out.format("+-----------+-------------------+-----------+%n");
                        }
                    } while (cdao.getById(country_id) == null);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }
}
