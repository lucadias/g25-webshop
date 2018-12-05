package ch.hslu.enapp.webshop.dto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

public class Purchase {
    private String id;
    private String customer;
    private Timestamp datetime;
    private String state;
    private String payId;
    private Customer customerByCustomer;
    private Collection<Purchaseitem> purchaseitemsById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase that = (Purchase) o;
        return id == that.id &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(datetime, that.datetime) &&
                Objects.equals(state, that.state);
    }

    public int hashCode() {
        return Objects.hash(id, customer, datetime, state);
    }

    public Customer getCustomerByCustomer() {
        return customerByCustomer;
    }

    public void setCustomerByCustomer(Customer customerByCustomer) {
        this.customerByCustomer = customerByCustomer;
    }

    public Collection<Purchaseitem> getPurchaseitemsById() {
        return purchaseitemsById;
    }

    public void setPurchaseitemsById(Collection<Purchaseitem> purchaseitemsById) {
        this.purchaseitemsById = purchaseitemsById;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
