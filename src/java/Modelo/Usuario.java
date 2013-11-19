/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Gabriel
 */
public class Usuario {

    int id;
    String nombre;
    String apellidos;
    String email;
    int rol;
    String password;
    String nick;
    String avatar;

    public Usuario(){}

    public Usuario(int id, String nombre, String apellidos, String email, int rol, String password, String nick, String avatar) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.password = password;
        this.nick = nick;
        this.avatar = avatar;
    }    
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public int getRol() {
        return rol;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    public String getAvatar() {
        return avatar;
    }

}
