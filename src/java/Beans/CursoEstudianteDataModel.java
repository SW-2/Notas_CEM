/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.CalificacionEstudiante;
import Clases.CursoEstudiante;
import Clases.Persona;
import java.util.List;
import javax.faces.model.ListDataModel;
import logica.PersonaLogic;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Jorge Garcia
 */
public class CursoEstudianteDataModel extends ListDataModel<CursoEstudiante> implements SelectableDataModel<CursoEstudiante>,java.io.Serializable  {    

    public CursoEstudianteDataModel() {
    }
    
    public CursoEstudianteDataModel(List<CursoEstudiante> data) {  
        super(data);  
    }
    
    @Override  
    public CursoEstudiante getRowData(String rowKey) {  
        PersonaLogic pl = new PersonaLogic();
        Persona p;
        List<CursoEstudiante> ces = (List<CursoEstudiante>) getWrappedData();
        for(CursoEstudiante ce : ces) {
            p = pl.buscarUnica(""+ce.getEstudiante().getPerId());
            if(p.getPerCedula().equals(rowKey))  
                return ce;  
        }  
        return null;  
    }
    
    @Override  
    public Object getRowKey(CursoEstudiante ce) {  
        return ce.getEstudiante().getPersona().getPerCedula();  
    }
    
}
