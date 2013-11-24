/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Usuarios;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ConjuntoUsuarios {
    
    ArrayList<Usuario> listUsuarios;

    public ConjuntoUsuarios(){
        
    }
    
    public ConjuntoUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }
    
    public Usuario getUsuario(int idUsuario){
        return listUsuarios.get(idUsuario);
    }
    
    public void newUsuario(String nombre, String apellidos, String email, int rol, String password, String nick, String avatar){
        Usuario newUsuario = new Usuario(nombre, apellidos, email, rol, password, nick, avatar);
        listUsuarios.add(newUsuario);
    }
    
    public boolean openSesion(String nick, String password){
        return true;
    }
    
    public boolean closeSesion(int idUsuario){
        return true;
    }
    
    public void deleteUsuario(int idUsuario){
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