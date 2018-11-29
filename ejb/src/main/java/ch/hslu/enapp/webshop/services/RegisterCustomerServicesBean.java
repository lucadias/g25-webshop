package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.entity.CustomerEntity;
import ch.hslu.enapp.webshop.entity.CustomertoroleEntity;
import com.google.common.hash.Hashing;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;

@Stateless(name = "RegisterServicesBeanEJB")
public class RegisterCustomerServicesBean implements RegisterCustomerServicesLocal {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void registerNewCustomer(Customer customer){

        final CustomerEntity newCustomer = new CustomerEntity();

        newCustomer.setName(customer.getName());
        newCustomer.setFirstname(customer.getFirstname());
        newCustomer.setLastname(customer.getLastname());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(Hashing.sha256()
                .hashString(customer.getPassword(), StandardCharsets.UTF_8)
                .toString().toUpperCase());



        em.persist(newCustomer);
        em.flush();

        CustomertoroleEntity newCustomerToRole = new CustomertoroleEntity();
        newCustomerToRole.setName(newCustomer.getName());
        newCustomerToRole.setRole("customer");
        em.persist(newCustomerToRole);
        em.flush();






    }



}
