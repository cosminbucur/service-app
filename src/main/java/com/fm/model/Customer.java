package com.fm.model;


import java.util.Objects;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String company;

    // FIXME should be private String
    // a phone number is always a String. long is for high numbers that don't start with 0 or +40
    private String phoneNumber;
    private String emailAddress;

    public Customer() {
        withId(id);
        withFirstName(firstName);
        withLastName(lastName);
        withCompany(company);
        withPhoneNumber(phoneNumber);
        withEmailAddress(emailAddress);
    }

    public static Customer customer() {
        return new Customer();
    }

    public Customer withId(Long id) {
        this.id = id;
        return this;
    }

    public Customer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer withCompany(String company) {
        this.company = company;
        return this;
    }

    public Customer withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Customer withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phoneNumber == customer.phoneNumber &&
                Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(company, customer.company) &&
                Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, company, phoneNumber, emailAddress);
    }
}
