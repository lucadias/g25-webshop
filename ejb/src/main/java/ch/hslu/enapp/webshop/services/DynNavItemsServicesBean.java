package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Customer;
import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.dto.Purchase;
import ch.hslu.enapp.webshop.entity.ProductEntity;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.PurchaseitemEntity;
import ch.hslu.enapp.webshop.wsdl.*;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Null;
import javax.xml.namespace.QName;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.net.URL;

@Stateful(name = "DynNavItemsServicesBeanEJB")
public class DynNavItemsServicesBean implements DynNavItemsServicesBeanLocal {

    private static final String WSDL_LOCATION = "http://enapp-was-global02.el.eee.intern:7047/DynamicsNAVTest/WS/iCompany%20HSLU%20T%26A/Page/Item";
    private static final String NAMESPACE_URI = "urn:microsoft-dynamics-schemas/page/item";
    private static final String SERVICE_NAME = "Item_Service";
    private static final String DOMAIN = "ICOMPANY";
    private static final String USER = "icDynNAVWsStudentSvc";
    private static final String PASSWORD = "ic0mp@ny";



    @PersistenceContext
    private EntityManager em;

    public DynNavItemsServicesBean() throws MalformedURLException {



    }

    @PostConstruct
    public void init(){
        try {
            syncSongswithDB();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void syncSongswithDB() throws MalformedURLException {
        final URL wsdl = new URL(WSDL_LOCATION);
        final QName itemPageQName = new QName(NAMESPACE_URI, SERVICE_NAME);
        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(DOMAIN + "\\" + USER, PASSWORD.toCharArray());
            }
        });

        final ItemService itemService = new ItemService(wsdl, itemPageQName);
        final ItemPort itemPort = itemService.getItemPort();

        final List<ItemFilter> filterList = new ArrayList<ItemFilter>();
        final ItemFilter filter = new ItemFilter();
        filter.setField(ItemFields.PRODUCT_GROUP_CODE);
        filter.setCriteria("MP3");
        filterList.add(filter);

        final ItemList itemList = itemPort.readMultiple(filterList, null, 0);



        for (final Item item : itemList.getItem()) {

            final ProductEntity product = new ProductEntity();

            product.setDescription(item.getOwner());
            product.setMediapath(item.getMediafileName());
            product.setName(item.getDescription());
            product.setDynnavnumber(item.getNo());
            product.setUnitprice(item.getUnitPrice());


            final TypedQuery<ProductEntity> query = em.createNamedQuery("getProductByDynNavNumber", ProductEntity.class);

                    query.setParameter("dynnavnumber", product.getDynnavnumber());
            List<ProductEntity> results = query.getResultList();
            if(results.isEmpty()){
                em.persist(product);
                em.flush();
            }

        }

    }







}

