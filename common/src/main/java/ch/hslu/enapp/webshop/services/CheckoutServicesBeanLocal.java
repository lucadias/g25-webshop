package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.dto.Purchaseitem;

import java.util.List;

public interface CheckoutServicesBeanLocal {
    void checkout(List<Purchaseitem> basket, String customername);
}
