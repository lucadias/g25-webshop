package ch.hslu.enapp.webshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity

@NamedQueries({
        @NamedQuery(name = "getCustomerToRoleByCustomerName", query = "SELECT a FROM CustomertoroleEntity a WHERE a.name = :Customername")
})
@Table(name = "customertorole", schema = "webshop", catalog = "")
@IdClass(CustomertoroleEntityPK.class)
public class CustomertoroleEntity {
    private String name;
    private String role;
    private CustomerEntity customerByName;
    private RoleEntity roleByRole;

    @Id
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "role", nullable = false, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomertoroleEntity that = (CustomertoroleEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "name", referencedColumnName = "name")
    public CustomerEntity getCustomerByName() {
        return customerByName;
    }

    public void setCustomerByName(CustomerEntity customerByName) {
        this.customerByName = customerByName;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "role", referencedColumnName = "name")
    public RoleEntity getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(RoleEntity roleByRole) {
        this.roleByRole = roleByRole;
    }
}
