/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Clases.CalificacionEstudiante;
import Clases.HibernateUtil;
import Clases.Persona;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jorge Garcia
 */
public class CalificacionEstudianteDB {
    private Session ss;

    public CalificacionEstudianteDB() {
        this.ss = HibernateUtil.getSessionFactory().openSession();
    }
    
    public ArrayList<CalificacionEstudiante> obtenerPorCursoMateria(String curmat){
        ArrayList<CalificacionEstudiante> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CalificacionEstudiante>) this.ss.createQuery("from Clases.CalificacionEstudiante where curmat_id="+curmat).list();
            tx.commit();
            this.ss.close();
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en obtener Calificacion por curso y materia"+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<CalificacionEstudiante> obtenerPorCursoMateriaParcial(String curmat, String parcial){
        ArrayList<CalificacionEstudiante> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            ret = (ArrayList<CalificacionEstudiante>) this.ss.createQuery("from Clases.CalificacionEstudiante where curmat_id="+curmat+" and parc_id="+parcial).list();
            tx.commit();
            this.ss.close();
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en obtener Calificacion por curso y materia"+e.getMessage());
            return null;
        }
    }
    
    
    
    public boolean actualizarCalificacion(CalificacionEstudiante ce){
        try{
            ce.setCalestFecha(new Date());
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            System.out.println("ASIGNANDO calificacion a Estudiante");
            this.ss.update(ce);
            tx.commit();
            this.ss.close();
            return true;
        }catch(Exception e){
            System.out.println("En ASIGNAR Calificaion a estudiante DB - "+e.getMessage()+" - - ");
            return false;
        }
    }
    
    public boolean eliminarPorCursoMateria(String curmat){
        ArrayList<CalificacionEstudiante> notas = obtenerPorCursoMateria(curmat);
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = this.ss.beginTransaction();
            System.out.println("ELIMINANDO ACTAS calificacion a Estudiante");
            for(CalificacionEstudiante ce : notas){
                if(ce.getCalestNota() != null){
                    this.ss.delete(ce);
                }
            }
            tx.commit();
            this.ss.close();
            return true;
        }catch (Exception e){
            Transaction tx = this.ss.getTransaction();
            System.out.println("ERROR EN --------- ELIMINANDO ACTAS calificacion a Estudiante");
            tx.rollback();
            return false;
        }
    }
}
