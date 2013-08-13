/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.CalificacionEstudiante;
import Clases.Curso;
import java.util.List;
import javax.faces.model.ListDataModel;
import logica.CursoLogic;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Jorge Garcia
 */
public class CursoDataModel extends ListDataModel<Curso> implements SelectableDataModel<Curso>,java.io.Serializable  {    

    public CursoDataModel() {
    }

    
    public CursoDataModel(List<Curso> list) {
        super(list);
    }

    @Override  
    public Curso getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        CursoLogic cl = new CursoLogic();
        List<Curso> cs = (List<Curso>) getWrappedData();
        for(Curso c: cs){
            if(cl.buscarUnico(rowKey).getCurId() == c.getCurId()){
                return c;
            }
        }
        return null;  
    }
    
    @Override  
    public Object getRowKey(Curso c) {  
        return c.getCurId();
    }
    
    
}
