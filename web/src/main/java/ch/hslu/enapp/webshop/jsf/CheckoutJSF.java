package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.ProductsBeanLocal;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.services.CheckoutServicesBeanLocal;
import ch.hslu.enapp.webshop.services.ProductServicesBeanLocal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named
@RequestScoped
public class CheckoutJSF {

    @Inject
    private CheckoutServicesBeanLocal checkoutbean;

    private BasketJSF basketjsf = new BasketJSF();



    public void checkout(List<Product> basket, String customername){

        checkoutbean.checkout(basket, customername);
        basketjsf.resetBasket();
    }



}
