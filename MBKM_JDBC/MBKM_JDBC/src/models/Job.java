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
public class Job {
    private String job_id, job_title;
    private double min_salary, max_salary;

    public Job() {
    }

    public Job(String job_id, String job_title, double min_salary, double max_salary) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    /**
     * @return the job_id
     */
    public String getJob_id() {
        return job_id;
    }

    /**
     * @param job_id the job_id to set
     */
    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    /**
     * @return the job_title
     */
    public String getJob_title() {
        return job_title;
    }

    /**
     * @param job_title the job_title to set
     */
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    /**
     * @return the min_salary
     */
    public double getMin_salary() {
        return min_salary;
    }

    /**
     * @param min_salary the min_salary to set
     */
    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    /**
     * @return the max_salary
     */
    public double getMax_salary() {
        return max_salary;
    }

    /**
     * @param max_salary the max_salary to set
     */
    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }
    
    
}
