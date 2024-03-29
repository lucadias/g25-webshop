package ch.hslu.enapp.webshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "purchaseitem", schema = "webshop", catalog = "")
public class PurchaseitemEntity {
    private int id;
    private String purchase;
    private int product;
    private int quantity;
    private PurchaseEntity purchaseByPurchase;
    private ProductEntity productByProduct;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "purchase", nullable = false)
    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    @Basic
    @Column(name = "product", nullable = false)
    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseitemEntity that = (PurchaseitemEntity) o;
        return id == that.id &&
                purchase == that.purchase &&
                product == that.product &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchase, product, quantity);
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "purchase", referencedColumnName = "id")
    public PurchaseEntity getPurchaseByPurchase() {
        return purchaseByPurchase;
    }

    public void setPurchaseByPurchase(PurchaseEntity purchaseByPurchase) {
        this.purchaseByPurchase = purchaseByPurchase;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product", referencedColumnName = "id")
    public ProductEntity getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(ProductEntity productByProduct) {
        this.productByProduct = productByProduct;
    }
}
