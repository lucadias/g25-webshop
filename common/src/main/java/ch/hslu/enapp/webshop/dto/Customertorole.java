package ch.hslu.enapp.webshop.dto;

import java.util.Objects;

public class Customertorole {
    private String name;
    private String role;
    private Customer customerByName;
    private Role roleByRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customertorole that = (Customertorole) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(role, that.role);
    }

    public int hashCode() {
        return Objects.hash(name, role);
    }

    public Customer getCustomerByName() {
        return customerByName;
    }

    public void setCustomerByName(Customer customerByName) {
        this.customerByName = customerByName;
    }

    public Role getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(Role roleByRole) {
        this.roleByRole = roleByRole;
    }
}
