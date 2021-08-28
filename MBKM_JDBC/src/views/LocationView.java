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
    String location_id, street_address, postal_code, city, state_province, country_id;

    public void InputData() {
        System.out.print("Location ID = ");
        this.location_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (ldao.getById(location_id) != null) {
                System.out.println("UPDATE DATA");
                System.out.println(ldao.getById(location_id).getLocation_id() + " - "
                        + ldao.getById(location_id).getStreet_address() + " - "
                        + ldao.getById(location_id).getPostal_code() + " - "
                        + ldao.getById(location_id).getCity()
                );
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
                    System.out.println(ldao.getById(location_id).getLocation_id() + " - "
                            + ldao.getById(location_id).getStreet_address() + " - "
                            + ldao.getById(location_id).getPostal_code() + " - "
                            + ldao.getById(location_id).getCity());
                    System.out.print("Apakah ingin dihapus? (ya/tidak)");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya") || pil2.equalsIgnoreCase("Ya")) {
                        System.out.println(
                                ldao.delete(location_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;

                case 4:
                    for (Location l : ldao.getAll()) {
                        System.out.println(l.getLocation_id() + " - " + l.getStreet_address() + " - " + l.getPostal_code() + " - " 
                        + l.getCity() + " - " + l.getState_province() + " - " + "Location ID: " + l.getLocation_id());
                    }
                    break;

                case 5:
                    do {
                        InputData();
                        if (ldao.getById(location_id) == null) {
                            System.out.println("Data tidak ada!");
                            break;
                        } else {
                            System.out.println(ldao.getById(location_id).getLocation_id() + " - "
                                    + ldao.getById(location_id).getStreet_address() + " - "
                                    + ldao.getById(location_id).getPostal_code() + " - "
                                    + ldao.getById(location_id).getCity()
                            );
                        }
                    } while (ldao.getById(location_id) == null);
                    break;

                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }

}
