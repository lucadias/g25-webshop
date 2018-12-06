package ch.hslu.enapp.webshop.tasks;

import ch.hslu.enapp.webshop.entity.CustomerEntity;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import  javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.util.List;

@Startup
@Singleton(name = "ScheduledTasksEJB")
public class ScheduledTask {

    private static final Logger logger = LogManager.getLogger(ScheduledTask.class);

    @PersistenceContext
    private EntityManager em;

    @Schedule(second = "*/10", minute = "*", hour = "*")
    private void scheduledTask() {

        final String baseURI = "http://enapp-daemons.el.eee.intern:9080/EnappDaemonWeb/rest/salesorder/corr/";
        List<PurchaseEntity> allPurchasesEntity = em.createNamedQuery("getAllPurchasesWhereStateNeedsUpdate", PurchaseEntity.class)
                .getResultList();

        allPurchasesEntity.forEach(purchaseEntity -> {
            final Client client = ClientBuilder.newBuilder().newClient();
            final WebTarget target =client.target(baseURI+purchaseEntity.getId());
            Invocation.Builder builder = target.request();
            Salesorder result = builder.get(Salesorder.class);
            addCostumerDynNumberToCustomer(result.getDynNAVCustomerNo(), purchaseEntity.getCustomer());
            purchaseEntity.setState((result.status));
        });
    }

    private void addCostumerDynNumberToCustomer(String dynNAVCustomerNo, String customer) {
        CustomerEntity cust = em.createNamedQuery("getCustomerByName", CustomerEntity.class)
                .setParameter("name", customer)
                .getSingleResult();
        cust.setDynNavCustNo(dynNAVCustomerNo);
        em.merge(cust);
        em.flush();
    }


}

