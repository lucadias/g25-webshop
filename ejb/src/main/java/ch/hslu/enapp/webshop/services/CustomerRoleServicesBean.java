package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.converter.CustomerConverter;
import ch.hslu.enapp.webshop.converter.CustomerToRoleConverter;
import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Customertorole;
import ch.hslu.enapp.webshop.entity.CustomerEntity;
import ch.hslu.enapp.webshop.entity.CustomertoroleEntity;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Stateless(name = "CustomerRoleServicesBeanEJB")
public class CustomerRoleServicesBean implements CustomerRoleServicesBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Resource
    EJBContext context;


    @Inject
    private CustomerToRoleConverter ctrc;

    @Override
    public ArrayList<Customertorole> getCustomerToRole(){

        List<CustomertoroleEntity> customertoroleList = new ArrayList<CustomertoroleEntity>();


        customertoroleList = em.createNamedQuery("getCustomerToRoleByCustomerName", CustomertoroleEntity.class)
                .setParameter("Customername", context.getCallerPrincipal().getName()).getResultList();


        ArrayList<Customertorole> customertoroleListConverted = new ArrayList<>();

        customertoroleList.forEach(i -> customertoroleListConverted.add(ctrc.convertToDTO(i)));

        return customertoroleListConverted;
    }



    

}
