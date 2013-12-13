/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Valoraciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Gabriel
 */
@Entity (name="Valoraciones")
public class Valoracion implements Serializable{
      
    @Id
    @GeneratedValue
    private long ID;
    private long idUsuario;
    private long idPelicula;
    private int nota;   

    public Valoracion(){}
    
     public Valoracion(int nota, long idUsuario, long idPelicula) {        
        this.nota = nota;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
    }
    
    public Valoracion(long id,int nota, long idUsuario, long idPelicula) {
        this.ID = id;
        this.nota = nota;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
    }

    public int getNota() {
        return nota;
    }
    
    public long getUsuario(){
        return idUsuario;
    }
    
    public long getPelicula(){
        return idPelicula;
    }
}
