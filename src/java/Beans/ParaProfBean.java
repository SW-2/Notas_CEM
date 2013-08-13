/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.CalificacionEstudiante;
import Clases.CursoMateria;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import logica.CalificacionEstudianteLogic;
import logica.CursoMateriaLogic;

/**
 *
 * @author Jorge Garcia
 */
@ManagedBean
@SessionScoped
public class ParaProfBean implements Serializable{
    @ManagedProperty(value="#{loginBean.usuario}")
    private String idProfesor;
    private DataModel dmCursoMaterias;
    private CursoMateria selectedCURMAT = new CursoMateria();
    private CalificacionEstudianteDataModel dmCalEst;
    private CalificacionEstudiante selectedCalEst = new CalificacionEstudiante();
    
    /**
     * Creates a new instance of ParaProfBean
     */
    public ParaProfBean() {
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public CalificacionEstudiante getSelectedCalEst() {
        return selectedCalEst;
    }

    public void setSelectedCalEst(CalificacionEstudiante selectedCalEst) {
        this.selectedCalEst = selectedCalEst;
    }
    
    

    public CalificacionEstudianteDataModel getDmCalEst() {
        return dmCalEst;
    }

    public void setDmCalEst(CalificacionEstudianteDataModel dmCalEst) {
        this.dmCalEst = dmCalEst;
    }

    public CursoMateria getSelectedCURMAT() {
        return selectedCURMAT;
    }

    public void setSelectedCURMAT(CursoMateria selectedCURMAT) {
        this.selectedCURMAT = selectedCURMAT;
    }
    
    public DataModel getDmCursoMaterias() {
        return dmCursoMaterias;
    }

    public void setDmCursoMaterias(DataModel dmCursoMaterias) {
        this.dmCursoMaterias = dmCursoMaterias;
    }
    
    public void simularLogin(){
        System.out.println("-------------------///////////////////toy en el evento simular :::: "+idProfesor);
        CursoMateriaLogic cml = new CursoMateriaLogic();
        ArrayList<CursoMateria> lista = cml.materiasPorProfesor(idProfesor);
        dmCursoMaterias = new ListDataModel(lista);   
    }
    
    public void mostrarActa(){
        //selectedCURMAT;
        CalificacionEstudianteLogic cel = new CalificacionEstudianteLogic();
        ArrayList<CalificacionEstudiante> lista = cel.obtenerPorCursoMateriaParcial(""+selectedCURMAT.getCurmatId(), "1");
        dmCalEst = new CalificacionEstudianteDataModel(lista);
    }
    
    public void enviarCalificaciones(){
        CalificacionEstudianteLogic cel = new CalificacionEstudianteLogic();
        System.out.println("********////****Calificacion: --->");
        for(CalificacionEstudiante ce:dmCalEst ){
            System.out.println("********////****Calificacion: --->"+ce.getCalestNota());
            if(cel.actualizarCalificacion(ce))
                System.out.println("********////**** SE GUARDO CORRECTAMENTE");
            
        }
    }
}
