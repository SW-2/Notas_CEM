package Beans;
 
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
/**
 * Simple navigation bean
 * @author itcuties
 *
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
 
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
    
    public String ToSecretaria() {
        return "/secretaria/index.xhtml";
    }

    public String redirectToSecretaria() {
        return "/secretaria/layout.xhtml?faces-redirect=true";
    }
    

    public String ToProfesores() {
        return "/profesores/index.xhtml";
    }

    public String redirectToProfesores() {
        return "/profesores/index.xhtml?faces-redirect=true";
    }
     
    
    public String redirectToEstudiantes() {
        return "/estudiantes/index.xhtml?faces-redirect=true";
    }
    
    public String toEstudiantes() {
        return "/estudiantes/index.xhtml";
    }
     
}