/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuarios;

import Modelo.Valoraciones.Valoracion;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author Gabriel
 */
@Entity(name="Usuarios")
public class Usuario implements Serializable {
    
    @Id    
    private long ID;
    private String nombre;
    private String apellidos;
    private String email;
    private int rol;
    private String password;
    private String nick;
    private String avatar;
    @OneToMany(cascade=CascadeType.ALL)
    private Map<Long,Valoracion> valoraciones;
    private double media;

    public Usuario(){}

    public Usuario(long id, String nombre, String apellidos, String email, int rol, String password, String nick, String avatar, Map<Long,Valoracion> valoraciones) {
        this.ID = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.password = password;
        this.nick = nick;
        this.avatar = avatar;
        this.valoraciones = valoraciones;
    }
    
        public Usuario(long id) {
        this.ID = id;
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.rol = 0;
        this.password = "";
        this.nick = "";
        this.avatar = "";
        this.valoraciones = new HashMap<Long, Valoracion>();
    }
    
    public Usuario(String nombre, String apellidos, String email, int rol, String password, String nick, String avatar){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.password = password;
        this.nick = nick;
        this.avatar = avatar;
    }
    
    public long getId() {
        return ID;
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

    public void setValoracion(Valoracion val){        
        valoraciones.put(ID, val);        
    }
    
    public Map<Long,Valoracion> getValoraciones(){
        return valoraciones;
    }

    public double getMedia() {
        return media;
    }
    
}
