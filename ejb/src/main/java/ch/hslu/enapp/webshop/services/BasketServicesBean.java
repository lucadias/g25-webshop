package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Product;

import javax.annotation.ManagedBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateful(name = "BasketServicesBeanEJB")

public class BasketServicesBean implements BasketServicesBeanLocal {

    @PersistenceContext
    private EntityManager em;

    ArrayList<Product> basket;

    public BasketServicesBean() {
        basket = new ArrayList<Product>();
    }

    @Override
    public void addtoBasket(Product product){
        basket.add(product);
    }

    @Override
    public List<Product> getBasket(){
        System.out.println("test 123");
        return basket;
    }

    @Override
    public void saveBasket(){

    }
}
