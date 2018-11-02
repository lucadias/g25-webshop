package ch.hslu.enapp.webshop.jsf;

import ch.hslu.enapp.webshop.dto.Product;
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



    private ArrayList<Product> basket = new ArrayList<Product>();




    public void addToBasket(Product product){
        LogManager.getLogger(BasketJSF.class).log(Level.DEBUG,"Added Item to BaLsket " +product.getName());
       basket.add(product);
   //    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("basketput",basket);
      //  basketServices.addtoBasket(product);
    }

    public ArrayList<Product> getBasketitems(){
        LogManager.getLogger(BasketJSF.class).log(Level.DEBUG,"Returned Basket " +this.basket.size());

        return this.basket;
    }


    public void resetBasket() {
        basket.clear();
    }
}
