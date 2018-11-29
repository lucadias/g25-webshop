package ch.hslu.enapp.webshop.jsf;


import ch.hslu.enapp.webshop.services.CustomerRoleServicesBeanLocal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class UserSessionJSF implements Serializable {
    private static final long serialVersionUID = 1492259801008765070L;

    private String username;

    @Inject
    private CustomerRoleServicesBeanLocal customertorolebean;

    private boolean isAdmin;


    public void setisAdmin(){
        customertorolebean.getCustomerToRole().forEach(i -> {
            if(i.getRole().equals("admin"))
                this.isAdmin = true;
        });
    }

    public String getUsername() {
        if (null == username) {
            try {
                final FacesContext facesContext = FacesContext.getCurrentInstance();
                username = facesContext.getExternalContext().getUserPrincipal().getName();


                final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Hello " + username, "");
                setisAdmin();
                facesContext.addMessage(null, message);
                facesContext.getExternalContext().getFlash().setKeepMessages(true);
            } catch (NullPointerException ex) {
                username = null;
            }
        }

        return username;
    }

    public String logout() throws IOException {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();

        final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Goodbye " + username, "");
        facesContext.addMessage(null, message);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);

        return "/index?faces-redirect=true";
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
}
