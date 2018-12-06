package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.converter.PurchasesConverter;
import ch.hslu.enapp.webshop.dto.Purchase;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "PurchasesServicesBeanEJB")
public class PurchasesServicesBean implements PurchasesServicesBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private PurchasesConverter pc;

    @Resource
    EJBContext context;




    @Override
    public List<Purchase> getAllPurchases() {

        List<PurchaseEntity> allPurchasesEntity = em.createNamedQuery("getAllPurchasesByCustomer", PurchaseEntity.class)
                .setParameter("customer", context.getCallerPrincipal().getName())
                .getResultList();

        ArrayList<Purchase> PurchasesListConverted = new ArrayList<>();
        allPurchasesEntity.forEach(i -> PurchasesListConverted.add(pc.convertToDTO(i)));
        return PurchasesListConverted;
    }



}
