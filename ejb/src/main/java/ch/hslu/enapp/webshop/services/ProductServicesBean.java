package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.entity.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "ProductServicesEJB")
public class ProductServicesBean implements ProductServicesBeanLocal {
    public ProductServicesBean() {
    }

    @PersistenceContext
    private EntityManager em;


    @Override
    public Product getFirstProduct() throws Exception {
        final TypedQuery<ProductEntity> query = em.createNamedQuery("getProduct", ProductEntity.class);
        final List<ProductEntity> results = query.getResultList();
        final ProductEntity productEntity = results.get(0);

        final Product productReturn = new Product();
        productReturn.setName(productEntity.getName());
        productReturn.setDescription(productEntity.getDescription());
        productReturn.setMediapath(productEntity.getMediapath());
        productReturn.setUnitprice(productEntity.getUnitprice());
        productReturn.setId(productEntity.getId());


        return  productReturn;
    }

    public List<Product> getProducts() throws Exception {
        final List<Product> productsReturn = new ArrayList<>();
        final TypedQuery<ProductEntity> query = em.createNamedQuery("getProduct", ProductEntity.class);

        for (final ProductEntity productEntity : query.getResultList()) {

            final Product product = new Product();
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setMediapath(productEntity.getMediapath());
            product.setUnitprice(productEntity.getUnitprice());
            product.setId(productEntity.getId());

            productsReturn.add(product);
        }


        return productsReturn;
    }

}
