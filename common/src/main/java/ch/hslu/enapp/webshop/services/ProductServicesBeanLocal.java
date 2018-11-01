package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Product;

import java.util.List;

public interface ProductServicesBeanLocal {
    Product getFirstProduct() throws Exception;


    List<Product> getProducts() throws Exception;
}
