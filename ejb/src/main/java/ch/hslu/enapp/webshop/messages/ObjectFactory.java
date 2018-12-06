
package ch.hslu.enapp.webshop.messages;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.hslu.enapp.webshop.messages package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.hslu.enapp.webshop.messages
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PurchaseMessage }
     * 
     */
    public PurchaseMessage createPurchaseMessage() {
        return new PurchaseMessage();
    }

    /**
     * Create an instance of {@link PurchaseMessage.Lines }
     * 
     */
    public PurchaseMessage.Lines createPurchaseMessageLines() {
        return new PurchaseMessage.Lines();
    }

    /**
     * Create an instance of {@link PurchaseMessage.Customer }
     * 
     */
    public PurchaseMessage.Customer createPurchaseMessageCustomer() {
        return new PurchaseMessage.Customer();
    }

    /**
     * Create an instance of {@link PurchaseMessage.Lines.Line }
     * 
     */
    public PurchaseMessage.Lines.Line createPurchaseMessageLinesLine() {
        return new PurchaseMessage.Lines.Line();
    }

}
