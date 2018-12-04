package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Purchase;
import ch.hslu.enapp.webshop.services.CustomerServicesBeanLocal;
import ch.hslu.enapp.webshop.services.DynNavItemsServicesBeanLocal;
import ch.hslu.enapp.webshop.services.PurchasesServicesBeanLocal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class PurchasesJSF implements Serializable {
    private static final long serialVersionUID = 1492259801008765071L;


    @Inject
    private PurchasesServicesBeanLocal purchasesbean;



    private List<Purchase> allPurchases;





    @PostConstruct
    private void init() throws MalformedURLException {

        allPurchases = purchasesbean.getAllPurchases();

    }



    public List<Purchase> getAllPurchages(){
        return allPurchases;
    }




}
