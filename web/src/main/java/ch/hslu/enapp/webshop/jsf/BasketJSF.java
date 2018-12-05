package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.dto.Product;
import ch.hslu.enapp.webshop.dto.Purchaseitem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class BasketJSF implements Serializable {
    private static final long serialVersionUID = 1492259801008765071L;



    static private ArrayList<Purchaseitem> basket = new ArrayList<Purchaseitem>();


    public void addToBasket(Product product){
//        LogManager.getLogger(BasketJSF.class).log(Level.DEBUG,"Added Item to BaLsket " +product.getName());

        Purchaseitem purchaseitem = new Purchaseitem();
        purchaseitem.setProduct(product.getId());
        purchaseitem.setProductByProduct(product);
        boolean toAdd = false;
        if(basket.isEmpty()){
            purchaseitem.setQuantity(1);
            basket.add(purchaseitem);
        }else {
            for(int i = 0; i < basket.size(); i++){
                if(basket.get(i).getProduct() == product.getId()){
                    basket.get(i).setQuantity(basket.get(i).getQuantity() + 1);
                    return;
                } else {
                    purchaseitem.setQuantity(1);
                    toAdd = true;
                }
            }
        }
        if(toAdd){
            basket.add(purchaseitem);
        }
    }




    public void removefromBasket(Purchaseitem product) {

        basket.remove(product);
    }

    public ArrayList<Purchaseitem> getBasketitems(){
        LogManager.getLogger(BasketJSF.class).log(Level.DEBUG,"Returned Basket " +this.basket.size());

        return this.basket;
    }


    public void resetBasket() throws InterruptedException {
        //wait(100);
        basket.clear();
    }
}
