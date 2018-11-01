package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.ProductsBeanLocal;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.services.ProductServicesBeanLocal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named
@RequestScoped
public class ProductJSF {

    @Inject
    private ProductServicesBeanLocal productServices;



    public List<Product> getProducts() throws  Exception{
        List<Product> products = productServices.getProducts();

        return products;
    }



}
