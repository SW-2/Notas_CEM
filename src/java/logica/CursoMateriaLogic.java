/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Clases.Curso;
import Clases.CursoMateria;
import Clases.Materia;
import db.CursoMateriaDB;
import java.util.ArrayList;

/**
 *
 * @author Jorge Garcia
 */
public class CursoMateriaLogic {
    CursoMateriaDB cmdb;

    public CursoMateriaLogic() {
        cmdb = new CursoMateriaDB();
    }
    
    public boolean guardarSinProf(String cursoId, String materiaId){
        MateriaLogic ml = new MateriaLogic();
        CursoLogic cl = new CursoLogic();
        
        Materia m = ml.buscarUnica(materiaId);
        Curso c = cl.buscarUnico(cursoId);
        
        return cmdb.guardarSinProf(c, m);
    }
    
    public ArrayList<CursoMateria> materiasPorCursoParalelo(String curso){
        return cmdb.materiasPorCursoParalelo(curso);
    }
    
    public CursoMateria buscarUnica(String curso, String materia){
        return cmdb.buscarUnica(curso, materia);
    }
    
    public CursoMateria buscarUnicaID(String id){
        return cmdb.buscarUnicaID(id);
    }
    
    public ArrayList<CursoMateria> materiasPorProfesor(String profe){
        return cmdb.materiasPorProfesor(profe);
    }
    
    public boolean asignarProfesorACursoMateria(String prof, String curmat){
        return cmdb.asignarProfesorACursoMateria(prof, curmat);
    }
    
    public boolean eliminarCursoMateria(Curso c, Materia m){
        return cmdb.eliminarCursoMateria(""+c.getCurId(), ""+m.getMatId());
        
    }
}
