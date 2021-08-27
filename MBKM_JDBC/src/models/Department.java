/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rebel
 */
public class Department {
    private String department_id, department_name, location_id, manager_id;

    public Department() {
    }

    public Department(String department_id, String department_name, String location_id, String manager_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.location_id = location_id;
        this.manager_id = manager_id;
    }

    /**
     * @return the department_id
     */
    public String getDepartment_id() {
        return department_id;
    }

    /**
     * @param department_id the department_id to set
     */
    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    /**
     * @return the department_name
     */
    public String getDepartment_name() {
        return department_name;
    }

    /**
     * @param department_name the department_name to set
     */
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    /**
     * @return the location_id
     */
    public String getLocation_id() {
        return location_id;
    }

    /**
     * @param location_id the location_id to set
     */
    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    /**
     * @return the manager_id
     */
    public String getManager_id() {
        return manager_id;
    }

    /**
     * @param manager_id the manager_id to set
     */
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
    
    
}
