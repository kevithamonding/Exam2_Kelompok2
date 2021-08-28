/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Employee;

/**
 *
 * @author Asus
 */
public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method ini berfungsi untuk mengambil data dari dalam database dan
     * ditampung dalam sebuah objek ArrayList
     *
     * @return objek yang berisi data yang telah diambil
     */
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM tb_employee")
                    .executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Method ini berfungsi untuk memasukan data baru atau mengubah data yang
     * sudah ada kedalam database dan akan dilakukan pengecekan, jika data sudah
     * ada maka akan melakukan Update dan jika belum ada akan melakukan Insert
     *
     * @param employee untuk menentukan objek yang berisi nilai masukan untuk
     * dimasukkan ke dalam database
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
    public boolean save(Employee employee) {
        try {
            boolean isInsert = getById(employee.getId()) == null;
            System.out.println(isInsert ? "Insert Berhasil" : "Update Berhasil");
            String query = isInsert
                    ? "INSERT INTO tb_employee(first_name, last_name, email, phone_number,"
                    + "hire_date, salary, comission_pct, job_id, manager_id, department_id, employee_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)"
                    : "UPDATE tb_employee SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?,"
                    + "salary = ?, comission_pct = ?, job_id = ?, manager_id = ?, department_id = ? WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhone_number());
            preparedStatement.setString(5, employee.getHire_date());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setDouble(7, employee.getCommisionPct());
            preparedStatement.setString(8, employee.getJobId());
            preparedStatement.setString(9, employee.getManagerId());
            preparedStatement.setString(10, employee.getDepartmentId());
            preparedStatement.setString(11, employee.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method ini berfungsi untuk menghapus data tertentu dari dalam database
     * berdasarkan parameter id yang dimasukkan
     *
     * @param id untuk menentukan id mana yang datanya akan dihapus
     * @return nilai berupa boolean, bernilai true jika berhasil dan false jika
     * sebaliknya
     */
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_employee WHERE employee_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method ini berfungsi untuk mengambil data tertentu dari dalam database
     * berdasarkan parameter id yang dimasukkan, dan ditampung dalam sebuah
     * objek
     *
     * @param id untuk menentukan id mana yang datanya akan diambil
     * @return objek yang berisi data (berdasarkan id) yang telah diambil
     */
    public Employee getById(String id) {
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tb_employee WHERE employee_id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
