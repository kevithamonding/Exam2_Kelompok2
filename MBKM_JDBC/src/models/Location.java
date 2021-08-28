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
public class Location {
    private String location_id, street_address, postal_code, city, state_province, country_id;

    public Location() {
    }

    public Location(String location_id, String stress_address, String postal_code, String city, String state_province, String country_id) {
        this.location_id = location_id;
        this.street_address = stress_address;
        this.postal_code = postal_code;
        this.city = city;
        this.state_province = state_province;
        this.country_id = country_id;
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
     * @return the stress_address
     */
    public String getStreet_address() {
        return street_address;
    }

    /**
     * @param stress_address the stress_address to set
     */
    public void setStress_address(String stress_address) {
        this.street_address = stress_address;
    }

    /**
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state_province
     */
    public String getState_province() {
        return state_province;
    }

    /**
     * @param state_province the state_province to set
     */
    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    /**
     * @return the country_id
     */
    public String getCountry_id() {
        return country_id;
    }

    /**
     * @param country_id the country_id to set
     */
    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "Location{" + "location_id=" + location_id + ", street_address=" + street_address + ", postal_code=" + postal_code + ", city=" + city + ", state_province=" + state_province + ", country_id=" + country_id + '}';
    }
    
    
}
