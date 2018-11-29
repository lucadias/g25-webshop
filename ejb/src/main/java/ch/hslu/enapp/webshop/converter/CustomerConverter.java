package ch.hslu.enapp.webshop.converter;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.entity.CustomerEntity;

import java.io.Serializable;

public class CustomerConverter implements Serializable {

    private static final long serialVersionUID = 1492259801008765071L;

    public CustomerEntity convertToEntity(Customer dto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setFirstname(dto.getFirstname());
        customer.setLastname(dto.getLastname());
        customer.setPassword(dto.getPassword());
        return customer;
    }


    public Customer convertToDTO(CustomerEntity entity) {
        Customer customer = new Customer();
        customer.setEmail(entity.getEmail());
        customer.setAddress(entity.getAddress());
        customer.setName(entity.getName());
        customer.setFirstname(entity.getFirstname());
        customer.setLastname(entity.getLastname());

        customer.setPassword(entity.getPassword());
        return customer;
    }
}