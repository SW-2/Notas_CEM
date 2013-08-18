/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Clases.HibernateUtil;
import Clases.Usuario;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author USUARIP
 */
public class UsuarioDB {
    
    Session ss = null;

    public UsuarioDB() {
        this.ss = HibernateUtil.getSessionFactory().openSession();
    }
    
    public ArrayList<Usuario> obtenerTodos(){
        ArrayList<Usuario> ret = null;
        try{
            if(!this.ss.isOpen())
                this.ss = HibernateUtil.getSessionFactory().openSession();
                
            Transaction tx = this.ss.beginTransaction();
                
            ret = (ArrayList<Usuario>) this.ss.createQuery("from Clases.Usuario").list();
            tx.commit();
            this.ss.close();
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("///////////// error en obtener TODos LOS USUARIOS ......"+e.getMessage());
            return null;
        }
    }
    
}
