package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.services.CustomerRoleServicesBeanLocal;
import ch.hslu.enapp.webshop.services.CustomerServicesBeanLocal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class CustomerJSF implements Serializable {
    private static final long serialVersionUID = 1492259801008765071L;


    @Inject
    private CustomerServicesBeanLocal customerbean;



    private Customer customer;

    private List<Customer> allCustomers;




    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @PostConstruct
    private void init(){
        customer = customerbean.getCustomer();
        allCustomers = customerbean.getAllCustomers();
    }


    public Customer getCustomer() throws  Exception {
        return customer;

    }

    public List<Customer> getAllCustomers(){
        return allCustomers;
    }

    public void setAllCustomers(List<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }



    public void updateAllCustomers(){
        customerbean.updateAllCustomers(this.allCustomers);

    }

    public void updateCustomer(){
        customerbean.updateCustomer(this.customer);

    }


}
