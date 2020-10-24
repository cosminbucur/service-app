package com.fm.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String company;
    private String phoneNumber;
    private String email;
    private boolean active;

    private List<String> licensePlates = new ArrayList<>();

    public Customer() {
        this.active = true;
    }

    // helper method
    public void addLicensePlate(String licensePlate) {
        this.licensePlates.add(licensePlate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, company, phoneNumber, email);
    }
}
