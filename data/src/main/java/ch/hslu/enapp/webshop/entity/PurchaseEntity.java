package ch.hslu.enapp.webshop.entity;

import ch.hslu.enapp.webshop.dto.Purchase;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "getPurchaseByDate", query = "SELECT u FROM PurchaseEntity u ORDER BY u.datetime DESC"),
        @NamedQuery(name = "getAllPurchasesByCustomer", query = "SELECT c FROM PurchaseEntity c WHERE c.customer = :customer"),
        @NamedQuery(name = "getAllPurchases", query = "SELECT c FROM PurchaseEntity c"),
        @NamedQuery(name = "getAllPurchasesWhereStateNeedsUpdate", query = "SELECT c FROM PurchaseEntity c WHERE c.state <> '010' and c.state <> '000'")

})

@Table(name = "purchase", schema = "webshop", catalog = "")
public class PurchaseEntity {
    private String id;
    private String customer;
    private Timestamp datetime;
    private String state;
    private String payId;
    private CustomerEntity customerByCustomer;
    private Collection<PurchaseitemEntity> purchaseitemsById;

/*    public PurchaseEntity(){
        this.id = UUID.randomUUID().toString();
    }*/
    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer", nullable = false, length = 255)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "payId", nullable = false, length = 255)
    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    @Basic
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseEntity that = (PurchaseEntity) o;
        return id == that.id &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(datetime, that.datetime) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, datetime, state);
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "customer", referencedColumnName = "name")
    public CustomerEntity getCustomerByCustomer() {
        return customerByCustomer;
    }

    public void setCustomerByCustomer(CustomerEntity customerByCustomer) {
        this.customerByCustomer = customerByCustomer;
    }

    @OneToMany(mappedBy = "purchaseByPurchase")
    public Collection<PurchaseitemEntity> getPurchaseitemsById() {
        return purchaseitemsById;
    }

    public void setPurchaseitemsById(Collection<PurchaseitemEntity> purchaseitemsById) {
        this.purchaseitemsById = purchaseitemsById;
    }


}
