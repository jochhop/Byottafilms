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
    private long idUsuario;
    private long idPelicula;
    private float nota;   

    public Valoracion(){}
    
    public Valoracion(float nota, int idUsuario, int idPelicula) {
        this.nota = nota;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
    }

    public float getNota() {
        return nota;
    }
    
    public long getUsuario(){
        return idUsuario;
    }
    
    public long getPelicula(){
        return idPelicula;
    }
}
