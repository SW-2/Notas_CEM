/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Garcia
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String usuario;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public void cambiarPagina(){
        try{
            System.out.println("orithoi---------"+usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index_profesor.xhtml");
        }catch (Exception e){
            System.out.println("error en redirect "+e.getMessage());
        }
    }
}
