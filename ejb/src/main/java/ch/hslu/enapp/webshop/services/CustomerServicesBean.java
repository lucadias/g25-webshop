package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.entity.CustomerEntity;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "CustomerServicesBeanEJB")
public class CustomerServicesBean implements CustomerServicesBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private CustomerConverter cc;

    @Resource
    EJBContext context;


    @Override
    public Customer getCustomer(){


        CustomerEntity cust = em.createNamedQuery("getCustomerByName", CustomerEntity.class)
                        .setParameter("name", context.getCallerPrincipal().getName())
                        .getSingleResult();

        return cc.convertToDTO(cust);
    }

    @Override
    public void updateCustomer(Customer customer) {

        em.merge(cc.convertToEntity(customer));
        em.flush();

    }

    @Override
    public List<Customer> getAllCustomers() {

        List<CustomerEntity> allCustomerEntitiy = em.createNamedQuery("getAllCustomers", CustomerEntity.class)
                .getResultList();

        ArrayList<Customer> CustomerListConverted = new ArrayList<>();
        allCustomerEntitiy.forEach(i -> CustomerListConverted.add(cc.convertToDTO(i)));
        return CustomerListConverted;
    }

    @Override
    public void updateAllCustomers(List<Customer> allCustomers) {
        allCustomers.forEach(i -> updateCustomer(i));
    }


}
