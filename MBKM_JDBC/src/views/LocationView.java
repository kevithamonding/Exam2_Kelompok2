/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.LocationDAO;
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
    LocationDAO ldao = new LocationDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    String location_id, street_address, postal_code, city, state_province, country_id, tbl;

    public void headerLocation() {
        this.tbl = "| %-10s | %-25s | %-11s | %-13s | %-26s | %-10s |%n";

        System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
        System.out.format("| LocationId | Street Address            | Postal Code | City          | State Province             | CountryId  |%n");
        System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
    }

    public void InputData() {
        System.out.print("Location ID = ");
        this.location_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (ldao.getById(location_id) != null) {
                System.out.println("UPDATE DATA");
                headerLocation();
                System.out.format(tbl, ldao.getById(location_id).getLocation_id(),
                        ldao.getById(location_id).getStreet_address(), ldao.getById(location_id).getPostal_code(),
                        ldao.getById(location_id).getCity(), ldao.getById(location_id).getState_province(),
                        ldao.getById(location_id).getCountry_id());
                System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
            }
            System.out.print("Street Address = ");
            this.street_address = sc.next();
            System.out.print("Postal Code =  ");
            this.postal_code = sc.next();
            System.out.print("City = ");
            this.city = sc.next();
            System.out.print("State Province = ");
            this.state_province = sc.next();
            System.out.print("Country ID = ");
            this.country_id = sc.next();
        }
    }

    public void Logika() {

        do {
            m.Menu();
            switch (m.pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    ldao.save(new Location(location_id, street_address, postal_code, city, state_province, country_id));
                    break;

                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    ldao.save(new Location(location_id, street_address, postal_code, city, state_province, country_id));
                    break;

                case 3:
                    System.out.println("== Delete ==");
                    InputData();
                    headerLocation();
                    System.out.format(tbl, ldao.getById(location_id).getLocation_id(),
                            ldao.getById(location_id).getStreet_address(), ldao.getById(location_id).getPostal_code(),
                            ldao.getById(location_id).getCity(), ldao.getById(location_id).getState_province(),
                            ldao.getById(location_id).getCountry_id());
                    System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
                    System.out.print("Apakah ingin dihapus? (ya/tidak)");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya")) {
                        System.out.println(
                                ldao.delete(location_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;

                case 4:
                    headerLocation();
                    for (Location l : ldao.getAll()) {
                        System.out.format(tbl, l.getLocation_id(), l.getStreet_address(), l.getPostal_code(),
                                l.getCity(), l.getState_province(), l.getLocation_id());
                    }
                    System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
                    break;

                case 5:
                    do {
                        InputData();
                        if (ldao.getById(location_id) == null) {
                            System.out.println("Data tidak ada!");
                            break;
                        } else {
                            headerLocation();
                            System.out.format(tbl, ldao.getById(location_id).getLocation_id(),
                                    ldao.getById(location_id).getStreet_address(), ldao.getById(location_id).getPostal_code(),
                                    ldao.getById(location_id).getCity(), ldao.getById(location_id).getState_province(),
                                    ldao.getById(location_id).getCountry_id());
                            System.out.format("+------------+---------------------------+-------------+---------------+----------------------------+------------+%n");
                        }
                    } while (ldao.getById(location_id) == null);
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }

}
