/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Employee {
    private String id, first_name, last_name, email, phone_number;
    private Date hire_date;
    private double salary, commisionPct;
    private String jobId, managerId, departmentId;

    public Employee(String id, String first_name, String last_name, String email, String phone_number, Date hire_date, double salary, double commisionPct, String jobId, String managerId, String departmentId) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.salary = salary;
        this.commisionPct = commisionPct;
        this.jobId = jobId;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public Employee() {
    }


    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", salary=" + salary + ", commisionPct=" + commisionPct + ", jobId=" + jobId + ", managerId=" + managerId + ", departmentId=" + departmentId + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommisionPct() {
        return commisionPct;
    }

    public void setCommisionPct(double commisionPct) {
        this.commisionPct = commisionPct;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    

    
}
