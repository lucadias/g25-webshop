package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.services.CustomerServicesBeanLocal;
import ch.hslu.enapp.webshop.services.RegisterCustomerServicesLocal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class RegisterJSF implements Serializable {
    private static final long serialVersionUID = 1492259801008765071L;



    @Inject
    private RegisterCustomerServicesLocal registerbean;

    private Customer customer;





    @PostConstruct
    private void init(){
        customer = new Customer();
        customer.setName("");
        customer.setPassword("");
        customer.setFirstname("");
        customer.setLastname("");
        customer.setEmail("");
        customer.setAddress("");

    }


    public String registerNewCustomer(){
        registerbean.registerNewCustomer(customer);
        return "/login?faces-redirect=true";

    }


    public Object getCustomer() {
        return customer;
    }
}
