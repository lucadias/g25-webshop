package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Product;

import java.util.List;

public interface BasketServicesBeanLocal {
    void addtoBasket(Product product);

    List<Product> getBasket();

    void saveBasket();
}
