/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jose
 */
public class ConjuntoUsuarios {
    
    ArrayList<Usuario> listUsuarios;

    public ConjuntoUsuarios(){
        this.listUsuarios = new ArrayList();
    }
    
    public ConjuntoUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }
    public Usuario getUsuarioById(long idUsuario) {
        
        for(Usuario aux: listUsuarios){
            if(aux.getId() == idUsuario){
                return aux;
            }
        }
        return null;
    }
    
    public Usuario getUsuario(int posicion){
        return listUsuarios.get(posicion);
    }
    
    public void newUsuario(String nombre, String apellidos, String email, int rol, String password, String nick, String avatar){
        Usuario newUsuario = new Usuario(nombre, apellidos, email, rol, password, nick, avatar);
        listUsuarios.add(newUsuario);
    }
    public void newUsuario(Usuario u){
        listUsuarios.add(u);
    }
    
    public boolean openSesion(String nick, String password){
        return true;
    }
    
    public boolean closeSesion(long idUsuario){
        return true;
    }
    
    public void deleteUsuario(int idUsuario){
        Iterator<Usuario> it = listUsuarios.iterator();
        
        while(it.hasNext()){
            
        }
        if(listUsuarios.remove(listUsuarios.get(idUsuario))){
            //TODO
        }else{
            //TODO
        }
    }
    
    public ArrayList<Usuario> searchUsuarios(String cadena){
        return this.listUsuarios;
    }
}