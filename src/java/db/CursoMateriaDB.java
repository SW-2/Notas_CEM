/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Clases.CalificacionEstudiante;
import Clases.Curso;
import Clases.CursoMateria;
import Clases.HibernateUtil;
import Clases.Materia;
import Clases.Profesor;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jorge Garcia
 */
public class CursoMateriaDB {
    private Session ss;

    public CursoMateriaDB() {
        this.ss = HibernateUtil.getSessionFactory().openSession();
        //this.ss = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void llamarSP(String curso, String seccion){
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
           PreparedStatement st = this.ss.connection().prepareStatement("{call sp_cargar_materias(?,?)}");
                st.setString(1, curso);
                st.setString(2, seccion);
                st.execute();
           
            
            tx.commit();
            ss.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en main por curso  "+e.getMessage());
        }
    }
    
    public ArrayList<CursoMateria> materiasPorProfesor(String profe){
        ArrayList<CursoMateria> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CursoMateria>) this.ss.createQuery("from Clases.CursoMateria where per_id="+profe).list();
            tx.commit();
            this.ss.close();
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en obtener Materias   por Profesor!---- "+e.getMessage());
            return null;
        }
    }
    
    
    public ArrayList<CursoMateria> materiasPorCursoParalelo(String curso){
        ArrayList<CursoMateria> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CursoMateria>) this.ss.createQuery("from Clases.CursoMateria where cur_id="+curso).list();
            tx.commit();
            this.ss.close();
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en obtener Materias   por curso  "+e.getMessage());
            return null;
        }
    }
    
    public boolean guardarSinProf(Curso c, Materia m){
        CursoMateria cm = new CursoMateria();
        Profesor p = new Profesor();
        p.setPerId(38);
        cm.setCurso(c);
        cm.setMateria(m);
        cm.setCurmatId(0);
        cm.setProfesor(p);
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            System.out.println("guardando curso- materia");
            this.ss.save(cm);
            tx.commit();
            this.ss.close();
            return true;
        }catch(Exception e){
            System.out.println("En guardar curso materia DB - "+e.getMessage()+" - - ");
            return false;
        }
    }
    
    public CursoMateria buscarUnica(String curso, String materia){
        ArrayList<CursoMateria> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CursoMateria>) this.ss.createQuery("from Clases.CursoMateria where cur_id="+curso+"and mat_id="+materia).list();
            tx.commit();
            this.ss.close();
            return ret.get(0);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en UNICO obtener Materias   por curso  "+e.getMessage());
            return null;
        }
    }
    
    public CursoMateria buscarUnicaID(String id){
        ArrayList<CursoMateria> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CursoMateria>) this.ss.createQuery("from Clases.CursoMateria where curmat_id="+id).list();
            tx.commit();
            this.ss.close();
            return ret.get(0);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en UNICO obtener Materias   por ID  "+e.getMessage());
            return null;
        }
    }
    
    public CursoMateria buscarUnicaPorID(String curmat){
        ArrayList<CursoMateria> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CursoMateria>) this.ss.createQuery("from Clases.CursoMateria where curmat_id="+curmat).list();
            tx.commit();
            this.ss.close();
            return ret.get(0);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en UNICO obtener Materias   por curso  "+e.getMessage());
            return null;
        }
    }
    
    public boolean asignarProfesorACursoMateria(String prof, String curmat){
        CursoMateria cm = this.buscarUnicaPorID(curmat);
        ProfesorDB pdb = new ProfesorDB();
        Profesor p = pdb.buscarUnico(prof);
        
        cm.setProfesor(p);
        
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            System.out.println("ASIGNANDO profesor a curso materia");
            this.ss.update(cm);
            tx.commit();
            this.ss.close();
            return true;
        }catch(Exception e){
            System.out.println("En ASIGNAR profesor a curso materia DB - "+e.getMessage()+" - - ");
            Transaction tx = this.ss.beginTransaction();
            tx.rollback();
            return false;
        }
    }
    
    public boolean eliminarMateriasDeCurso(String curso){
        ArrayList<CursoMateria> curmat = materiasPorCursoParalelo(curso);
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            System.out.println("ELIMINANDO CURSO MATERIA ");
            
            for(CursoMateria cm : curmat){
                this.ss.delete(cm);
            }
            
            tx.commit();
            this.ss.close();
            return true;
        }catch(Exception e){
            System.out.println("En ELIMINAR CURSOMATERIA DB- "+e.getMessage()+" - - ");
            Transaction tx = this.ss.getTransaction();
            tx.rollback();
            return false;
        }
    }
    
    public boolean eliminarCursoMateria(String curso, String materia){
        CursoMateria curmat = buscarUnica(curso, materia);
        CalificacionEstudianteDB cedb = new CalificacionEstudianteDB();
        ArrayList<CalificacionEstudiante> notas = cedb.obtenerPorCursoMateria(""+curmat.getCurmatId());
        for(CalificacionEstudiante ce: notas){
            if(ce.getCalestNota() != null)
                return false;
        }
        if(cedb.eliminarPorCursoMateria(""+curmat.getCurmatId())){
            try{
                if(!this.ss.isOpen())
                    this.ss = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = this.ss.beginTransaction();
                System.out.println("ELIMINANDO CURSO - MATERIA ");
                this.ss.delete(curmat);
                tx.commit();
                this.ss.close();
                return true;
            }catch(Exception e){
                System.out.println("En ELIMINAR CURSOMATERIA DB- "+e.getMessage()+" - - ");
                Transaction tx = this.ss.getTransaction();
                tx.rollback();
                return false;
            }
        }
        else return false;
    }
    
}
