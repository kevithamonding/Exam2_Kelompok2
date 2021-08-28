/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import daos.EmployeeDAO;
import java.sql.Date;
import java.util.Scanner;
import models.Employee;
import tools.DBConnection;
import tools.MBKM_JDBC;

/**
 *
 * @author Asus
 */
public class EmployeeView {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    DBConnection dbc = new DBConnection();
    EmployeeDAO edao = new EmployeeDAO(dbc.getConnection());
    MBKM_JDBC m = new MBKM_JDBC();
    String tbl, sDate;
    Date emp_hire_date;
    double emp_salary, emp_commisionPct;
    String emp_id, emp_first_name, emp_last_name, emp_email, emp_phone_number, emp_jobId, emp_managerId, emp_departmentId;

    public void headerEmployee() {
        this.tbl = "| %-10s | %-14s | %-14s | %-16s | %-12s | %-10s | %-6s | %-13s | %-10s | %-20s | %-20s |%n";

        System.out.format("+------------+----------------+----------------+------------------+--------------+------------+--------+---------------+------------+----------------------+----------------------+%n");
        System.out.format("| EmployeeId | First Name     | Last Name      | Email            | Phone Number | Hire Date  | Job ID | Department ID | Manager ID | Salary (Rp.)         | Commision (Rp.)      |%n");
        System.out.format("+------------+----------------+----------------+------------------+--------------+------------+--------+---------------+------------+----------------------+----------------------+%n");
    }

    public void InputData() {
        System.out.print("Employee ID = ");
        this.emp_id = sc.next();
        if (m.pil == 1 || m.pil == 2) {
            if (edao.getById(emp_id) != null) {
                System.out.println("UPDATE DATA");
                headerEmployee();
                System.out.format(tbl, edao.getById(emp_id).getId(), edao.getById(emp_id).getFirst_name(),
                        edao.getById(emp_id).getLast_name(), edao.getById(emp_id).getEmail(),
                        edao.getById(emp_id).getPhone_number(), edao.getById(emp_id).getHire_date(),
                        edao.getById(emp_id).getJobId(), edao.getById(emp_id).getDepartmentId(),
                        edao.getById(emp_id).getManagerId(), edao.getById(emp_id).getSalary(),
                        edao.getById(emp_id).getCommisionPct());
                System.out.format("+------------+----------------+----------------+------------------+--------------+------------+--------+---------------+------------+----------------------+----------------------+%n");
            }
            System.out.print("First Name = ");
            this.emp_first_name = sc.next();
            System.out.print("Last Name = ");
            this.emp_last_name = sc.next();
            System.out.print("Email = ");
            this.emp_email = sc.next();
            System.out.print("Phone Number = ");
            this.emp_phone_number = sc.next();
            System.out.print("Hire Date (yyyy-mm-dd)= ");
            this.sDate = sc.next();
            this.emp_hire_date = Date.valueOf(this.sDate);
            System.out.print("Job ID = ");
            this.emp_jobId = sc.next();
            System.out.print("Manager ID = ");
            this.emp_managerId = sc.next();
            System.out.print("Department ID = ");
            this.emp_departmentId = sc.next();
            System.out.print("Salary = ");
            this.emp_salary = sc.nextDouble();
            System.out.print("Commision = ");
            this.emp_commisionPct = sc.nextDouble();
        }
    }

    public void Logika() {
        do {
            m.Menu();
            switch (m.pil) {
                case 1:
                    System.out.println("== Insert ==");
                    InputData();
                    edao.save(new Employee(emp_id, emp_first_name, emp_last_name,
                            emp_email, emp_phone_number, emp_hire_date,
                            emp_salary, emp_commisionPct, emp_jobId,
                            emp_managerId, emp_departmentId));
                    break;
                case 2:
                    System.out.println("== Update ==");
                    InputData();
                    edao.save(new Employee(emp_id, emp_first_name, emp_last_name,
                            emp_email, emp_phone_number, emp_hire_date,
                            emp_salary, emp_commisionPct, emp_jobId,
                            emp_managerId, emp_departmentId));
                    break;
                case 3:
                    System.out.println("== Delete ==");
                    InputData();
                    System.out.println("Employee ID: " + edao.getById(emp_id).getId() + "\nName: "
                            + edao.getById(emp_id).getFirst_name() + " " + edao.getById(emp_id).getLast_name()
                            + "\nEmail: " + edao.getById(emp_id).getEmail() + "\nPhone Number: "
                            + edao.getById(emp_id).getPhone_number()
                            + "\nHire Date: " + edao.getById(emp_id).getHire_date()
                            + "\nJob ID: " + edao.getById(emp_id).getJobId() + "\nDepartment ID: "
                            + edao.getById(emp_id).getDepartmentId()
                            + "\nManager ID: " + edao.getById(emp_id).getManagerId() + "\nSalary: Rp. "
                            + edao.getById(emp_id).getSalary()
                            + "\nCommision: Rp. " + edao.getById(emp_id).getCommisionPct());
                    System.out.print("Apakah ingin dihapus? (ya/tidak) ");
                    String pil2 = sc.next();
                    if (pil2.equalsIgnoreCase("ya")) {
                        System.out.println(
                                edao.delete(emp_id)
                                ? "Delete Berhasil" : "Delete Gagal"
                        );
                    }
                    break;
                case 4:
                    headerEmployee();
                    for (Employee e : edao.getAll()) {
                        System.out.format(tbl, e.getId(), e.getFirst_name(), e.getLast_name(), e.getEmail(),
                                e.getPhone_number(), e.getHire_date(), e.getJobId(), e.getDepartmentId(),
                                e.getManagerId(), e.getSalary(), e.getCommisionPct());
                    }
                    System.out.format("+------------+----------------+----------------+------------------+--------------+------------+--------+---------------+------------+----------------------+----------------------+%n");

                    break;
                case 5:
                    do {
                        InputData();
                        if (edao.getById(emp_id) == null) {
                            System.out.println("Data tidak ada!");
                        } else {
                            headerEmployee();
                            System.out.format(tbl, edao.getById(emp_id).getId(), edao.getById(emp_id).getFirst_name(),
                                    edao.getById(emp_id).getLast_name(), edao.getById(emp_id).getEmail(),
                                    edao.getById(emp_id).getPhone_number(), edao.getById(emp_id).getHire_date(),
                                    edao.getById(emp_id).getJobId(), edao.getById(emp_id).getDepartmentId(),
                                    edao.getById(emp_id).getManagerId(), edao.getById(emp_id).getSalary(),
                                    edao.getById(emp_id).getCommisionPct());
                            System.out.format("+------------+----------------+----------------+------------------+--------------+------------+--------+---------------+------------+----------------------+----------------------+%n");
                        }
                    } while (edao.getById(emp_id) == null);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Pilihan Anda Salah");
            }

        } while (m.pil
                != 6);
    }
}
