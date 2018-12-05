package ch.hslu.enapp.webshop.services;

import ch.hslu.enapp.webshop.dto.Purchaseitem;
import ch.hslu.enapp.webshop.entity.CustomerEntity;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.PurchaseitemEntity;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import ch.hslu.enapp.webshop.messages.PurchaseMessage;
import ch.hslu.enapp.webshop.rest.Ncresponse;
import com.google.common.hash.Hashing;
import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;


@Stateless(name = "CheckoutServicesBeanEJB")
public class CheckoutServicesBean implements CheckoutServicesBeanLocal {
    public CheckoutServicesBean() {
    }

    private static final Logger logger = LogManager.getLogger(CheckoutServicesBean.class);
    @PersistenceContext
    private EntityManager em;

    Connection connection = null;
    Session session = null;

    @Resource(name = "jms/enappQueueAMQ")
    private Queue enappQueueAMQ;

    @Resource(name = "webshopRemoteJmsQueueConnectionFactory")
    private ConnectionFactory webshopRemoteJmsQueueConnectionFactory;



    public static final String PSPID = "HSLUiCompany";
    public static final String USERID = "enappstudents";
    public static final String PSWD = "ds2H9ZV.p!8r";
    public static final String  SHA1PW =	"hslu!comp@ny.websh0p";
    public static final String CONTENT_TYPE	= "application/x-www-form-urlencoded";
    public static final String URL = "https://e-payment.postfinance.ch/ncol/test/orderdirect.asp";
    public static final String  CARDNO = "4111111111111111";
    public static final String  ED = "0921";
    public static final String  CVC = "123";
    public static final String  OPERATION = "SAL";
    public static final String  CURRENCY = "CHF";

    @Override
    public void checkout(List<Purchaseitem> basket, String customername){


        double amount = 0;




        for (final Purchaseitem purchaseitem: basket) {
            amount += Double.valueOf(String.valueOf(purchaseitem.getProductByProduct().getUnitprice()));
        }


        final PurchaseEntity newPurchase = new PurchaseEntity();

        newPurchase.setId(UUID.randomUUID().toString());
        newPurchase.setPayId(payment(newPurchase.getId(), amount));

        newPurchase.setCustomer(customername);
        newPurchase.setDatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        newPurchase.setState("entered");
        em.persist(newPurchase);
        em.flush();

        String purchaseid = newPurchase.getId();
        if (purchaseid.isEmpty()){
            final TypedQuery<PurchaseEntity> query = em.createNamedQuery("getPurchaseByDate", PurchaseEntity.class);
            purchaseid = query.getResultList().get(0).getId();
        }


        PurchaseMessage.Lines lineMessage = new PurchaseMessage.Lines();

        for (final Purchaseitem purchaseitem : basket) {
            PurchaseitemEntity newPurchaseitem = new PurchaseitemEntity();
            newPurchaseitem.setPurchase(purchaseid);
            newPurchaseitem.setQuantity(purchaseitem.getQuantity());
            newPurchaseitem.setProduct(purchaseitem.getProduct());

            PurchaseMessage.Lines.Line plm = new PurchaseMessage.Lines.Line();
            plm.setQuantity(Integer.toString(purchaseitem.getQuantity()));
            plm.setMsDynNAVItemNo(purchaseitem.getProductByProduct().getDynnavnumber());
            plm.setDescription(purchaseitem.getProductByProduct().getDescription());
            plm.setTotalLinePrice( purchaseitem.getProductByProduct().getUnitprice().multiply(BigDecimal.valueOf(purchaseitem.getQuantity())).toString());
            lineMessage.setLine(plm);
            LogManager.getLogger(CheckoutServicesBean.class).error(plm);

            em.persist(newPurchaseitem);
            em.flush();

        }

        sendMessage(amount, newPurchase, customername, lineMessage);


    }


    private void sendMessage(Double amount, PurchaseEntity newPurchase, String customername, PurchaseMessage.Lines lineMessage) {
        PurchaseMessage message = new PurchaseMessage();
        message.setStudent("tapaivad");
        message.setDate(new Timestamp(System.currentTimeMillis()).toString());
        message.setTotalPrice(amount.toString());
        message.setPayId(newPurchase.getPayId());
        message.setPurchaseId(newPurchase.getId());


        PurchaseMessage.Customer customerMessage = new PurchaseMessage.Customer();



        final TypedQuery<CustomerEntity> customerquery = em.createNamedQuery("getCustomerByName", CustomerEntity.class);
        customerquery.setParameter("name",customername);
        CustomerEntity customerE = customerquery.getSingleResult();
        customerMessage.setName(customerE.getFirstname() + customerE.getLastname());
        customerMessage.setShopLoginname(customerE.getName());
        customerMessage.setAddress(customerE.getAddress());
        customerMessage.setDynNavCustNo(customerE.getDynNavCustNo());

        message.setLines(lineMessage);

        message.setCustomer(customerMessage);

        LogManager.getLogger(CheckoutServicesBean.class).error(customerMessage);


        LogManager.getLogger(CheckoutServicesBean.class).error(customerE.toString());

        try {
            sendJMSOurchaseMessage(message);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private String payment(String purchaseid, double amount){

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
        formData.add("PSPID", PSPID);
        formData.add("USERID", USERID);
        formData.add("PSWD", PSWD);
       // formData.add("CONTENT_TYPE", CONTENT_TYPE);
        formData.add("CARDNO", CARDNO);
        formData.add("ED", ED);
        formData.add("CVC", CVC);

        formData.add("CURRENCY", CURRENCY);
        formData.add("OPERATION", OPERATION);
        formData.add("AMOUNT", String.valueOf((int) amount*100));
        formData.add("ORDERID", purchaseid);

        String hash =
                        "AMOUNT="+ (int) amount * 100
                        +SHA1PW+
                        "CARDNO="+CARDNO
                        +SHA1PW
                      //  +"CONTENT_TYPE="+CONTENT_TYPE
                       // +SHA1PW
                        +"CURRENCY="+CURRENCY
                        +SHA1PW
                        +"CVC="+CVC
                        +SHA1PW
                        +"ED="+ED
                        +SHA1PW
                        +"OPERATION="+OPERATION
                        +SHA1PW
                        +"ORDERID="+ purchaseid
                        +SHA1PW
                        +"PSPID="+PSPID
                        +SHA1PW
                        +"PSWD="+PSWD
                        +SHA1PW
                        +"USERID="+USERID
                        +SHA1PW;

        formData.add("SHASIGN", Hashing.sha1().hashString(hash, StandardCharsets.UTF_8).toString());
        LogManager.getLogger(CheckoutServicesBean.class).error(hash);


        LogManager.getLogger(CheckoutServicesBean.class).error(Hashing.sha1().hashString(hash, StandardCharsets.UTF_8).toString());





         final Client client = ClientBuilder.newBuilder().newClient();
        final WebTarget target = client.target(URL);
        final Invocation.Builder builder = target.request();
        final Response result = builder.post(Entity.form(formData));
        final Ncresponse xmlResponse = result.readEntity(Ncresponse.class);
        LogManager.getLogger(CheckoutServicesBean.class).info("PAYID: "+xmlResponse.getPAYID());
        LogManager.getLogger(CheckoutServicesBean.class).error(xmlResponse);
        return xmlResponse.getPAYID().toString();
    }

    void sendJMSOurchaseMessage(PurchaseMessage pmessage) throws JAXBException, JMSException {
        try {
            connection = webshopRemoteJmsQueueConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final MessageProducer messageProducer = session.createProducer(enappQueueAMQ);

            final JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseMessage.class);
            final StringWriter writer = new StringWriter();

            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(pmessage, writer);
            final String purchaseMessageText = writer.toString();

//...
            final TextMessage textMessage = session.createTextMessage(purchaseMessageText);
            String correlationId = UUID.randomUUID().toString();
            textMessage.setJMSCorrelationID(correlationId);


            textMessage.setStringProperty("MessageFormat", "Version 1.5");

            messageProducer.send(textMessage);


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
