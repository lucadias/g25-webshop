package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Customer;

import java.util.List;

public interface CustomerServicesBeanLocal {
    public Customer getCustomer();

    void updateCustomer(Customer customer);

    List<Customer> getAllCustomers();

    void updateAllCustomers(List<Customer> allCustomers);
}
