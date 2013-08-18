/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Clases.Usuario;
import db.UsuarioDB;
import java.util.ArrayList;

/**
 *
 * @author USUARIP
 */
public class UsuarioLogic {

    UsuarioDB udb;
    public UsuarioLogic() {
        udb = new UsuarioDB();
    }
    
    public ArrayList<Usuario> obtenerTodos(){
        return udb.obtenerTodos();
    }
    
    
    
    
}
