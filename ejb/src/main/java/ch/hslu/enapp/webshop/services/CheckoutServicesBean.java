package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.dto.Purchase;
import ch.hslu.enapp.webshop.entity.ProductEntity;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.PurchaseitemEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Stateless(name = "CheckoutServicesBeanEJB")
public class CheckoutServicesBean implements CheckoutServicesBeanLocal {
    public CheckoutServicesBean() {
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public void checkout(List<Product> basket, String customername){



        final PurchaseEntity newPurchase = new PurchaseEntity();

        newPurchase.setCustomer(customername);
        newPurchase.setDatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        newPurchase.setState("entered");
        em.persist(newPurchase);
        em.flush();

        int purchaseid = newPurchase.getId();
        if (purchaseid == 0){
            final TypedQuery<PurchaseEntity> query = em.createNamedQuery("getPurchaseByDate", PurchaseEntity.class);
            purchaseid = query.getResultList().get(0).getId();
        }

        for (final Product product : basket) {
            PurchaseitemEntity newPurchaseitem = new PurchaseitemEntity();
            newPurchaseitem.setPurchase(purchaseid);
            newPurchaseitem.setProduct(product.getId());
            newPurchaseitem.setQuantity(1);
            em.persist(newPurchaseitem);
            em.flush();
        }


    }
}
