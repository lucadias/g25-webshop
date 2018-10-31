package ch.hslu.enapp.webshop;

import ch.hslu.enapp.webshop.entity.ProductEntity;
import org.apache.logging.log4j.LogManager;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless(name = "ProductsEJB")
public class ProductsBean implements ProductsBeanLocal {
    public ProductsBean() {
    }
    @Override
    public String getProducts(){
        System.out.println("test");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            final TypedQuery<ProductEntity> query = em.createNamedQuery("getProduct", ProductEntity.class);
            final List<ProductEntity> results = query.getResultList();
            tx.commit();
            return results.get(0).getName();
        } catch (Exception e) {
            LogManager.getLogger(ProductsBean.class).error("exception", e);
        } finally {
            em.close();
            emf.close();
        }
        return "not found";


    }
}
