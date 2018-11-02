package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Product;

import java.util.List;

public interface CheckoutServicesBeanLocal {
    void checkout(List<Product> basket, String customername);
}
