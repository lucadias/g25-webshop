package ch.hslu.enapp.webshop.dto;

import java.util.Collection;
import java.util.Objects;

public class Role {
    private String name;
    private Collection<Customertorole> customertorolesByName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return Objects.equals(name, that.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public Collection<Customertorole> getCustomertorolesByName() {
        return customertorolesByName;
    }

    public void setCustomertorolesByName(Collection<Customertorole> customertorolesByName) {
        this.customertorolesByName = customertorolesByName;
    }
}
