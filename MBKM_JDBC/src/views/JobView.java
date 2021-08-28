/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.JobDAO;
import java.util.Scanner;
import models.Job;
import tools.DBConnection;
import tools.MBKM_JDBC;

/**
 *
 * @author rebel
 */
public class JobView {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    JobDAO jdao = new JobDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    String job_id, job_title;
    double min_salary, max_salary;

    public void InputData() {
        System.out.print("Job ID = ");
        this.job_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (jdao.getById(job_id) != null) {
                System.out.println("UPDATE DATA");
                System.out.println(jdao.getById(job_id).getJob_id() + " - "
                        + jdao.getById(job_id).getJob_title());
            }
            System.out.print("Job Name = ");
            this.job_title = sc.next();
            System.out.print("Minimal Salary = ");
            this.min_salary = sc.nextDouble();
            System.out.print("Maximal Salary = ");
            this.max_salary = sc.nextDouble();

        }
    }

    public void Logika() {

        do {
            m.Menu();
            switch (m.pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    jdao.save(new Job(job_id, job_title, min_salary, max_salary));
                    break;

                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    jdao.save(new Job(job_id, job_title, min_salary, max_salary));
                    break;

                case 3:
                    System.out.println("== Delete ==");
                    InputData();
                    System.out.println(jdao.getById(job_id).getJob_id() + " - "
                            + jdao.getById(job_id).getJob_title());
                    System.out.print("Apakah ingin dihapus? (ya/tidak)");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya") || pil2.equalsIgnoreCase("Ya")) {
                        System.out.println(
                                jdao.delete(job_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;

                case 4:
                    for (Job j : jdao.getAll()) {
                        System.out.println(j.getJob_id() + " - " + j.getJob_title() + " - " +  "Min Salary: " +j.getMin_salary() + " - " + "Max Salary: " +j.getMax_salary());
                    }
                    break;

                case 5:
                    do {
                        InputData();
                        if (jdao.getById(job_id) == null) {
                            System.out.println("Data tidak ada!");
                        } else {
                            System.out.println(jdao.getById(job_id).getJob_id() + " - "
                                    + jdao.getById(job_id).getJob_title());
                        }
                    } while (jdao.getById(job_id) == null);
                    break;

                default:
                    System.out.println("Pilihan Anda Salah");
            }
        } while (m.pil != 6);
    }

}
