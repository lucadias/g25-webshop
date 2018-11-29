package ch.hslu.enapp.webshop.converter;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Customertorole;
import ch.hslu.enapp.webshop.entity.CustomerEntity;
import ch.hslu.enapp.webshop.entity.CustomertoroleEntity;

import java.io.Serializable;

public class CustomerToRoleConverter implements Serializable {

    private static final long serialVersionUID = 1492259801008765071L;

    public CustomertoroleEntity convertToEntity(Customertorole dto) {
        CustomertoroleEntity customertorole = new CustomertoroleEntity();
        customertorole.setName(dto.getName());
        customertorole.setRole(dto.getRole());

        return customertorole;
    }


    public Customertorole convertToDTO(CustomertoroleEntity entity) {
        Customertorole customertorole = new Customertorole();
        customertorole.setName(entity.getName());
        customertorole.setRole(entity.getRole());

        return customertorole;
    }
}