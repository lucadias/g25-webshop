package ch.hslu.enapp.webshop;

import javax.ejb.Local;

@Local
public interface ProductsBeanLocal {
    String getProducts();
}
