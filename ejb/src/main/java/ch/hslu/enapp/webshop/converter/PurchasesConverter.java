package ch.hslu.enapp.webshop.converter;

import ch.hslu.enapp.webshop.dto.Customertorole;
import ch.hslu.enapp.webshop.dto.Purchase;
import ch.hslu.enapp.webshop.entity.CustomertoroleEntity;
import ch.hslu.enapp.webshop.entity.PurchaseEntity;

import java.io.Serializable;

public class PurchasesConverter implements Serializable {

    private static final long serialVersionUID = 1492259801008765071L;

    public PurchaseEntity convertToEntity(Purchase dto) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setCustomer(dto.getCustomer());
        purchase.setDatetime(dto.getDatetime());
        purchase.setId(dto.getId());
        purchase.setState(dto.getState());
        return purchase;
    }


    public Purchase convertToDTO(PurchaseEntity entity) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(entity.getCustomer());
        purchase.setDatetime(entity.getDatetime());
        purchase.setId(entity.getId());
        purchase.setState(entity.getState());
        return purchase;
    }
}