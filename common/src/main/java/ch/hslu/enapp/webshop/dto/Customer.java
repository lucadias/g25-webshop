package ch.hslu.enapp.webshop.dto;

import java.util.Collection;
import java.util.Objects;

public class Customer {
    private String name;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private Collection<Customertorole> customertorolesByName;
    private Collection<Purchase> purchasesByName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email);
    }

    public int hashCode() {
        return Objects.hash(name, password, firstname, lastname, address, email);
    }

    public Collection<Customertorole> getCustomertorolesByName() {
        return customertorolesByName;
    }

    public void setCustomertorolesByName(Collection<Customertorole> customertorolesByName) {
        this.customertorolesByName = customertorolesByName;
    }

    public Collection<Purchase> getPurchasesByName() {
        return purchasesByName;
    }

    public void setPurchasesByName(Collection<Purchase> purchasesByName) {
        this.purchasesByName = purchasesByName;
    }
}
