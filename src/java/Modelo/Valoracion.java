/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
@Entity (name="Valoraciones")
public class Valoracion implements Serializable{
      
    @Id
    @ManyToOne
    private Usuario valorador;
    @Temporal(TemporalType.TIMESTAMP)
    private GregorianCalendar fecha;
    private float nota;   

    public Valoracion(){}
    
    public Valoracion(float nota, GregorianCalendar fecha, Usuario valorador) {
        this.nota = nota;
        this.fecha = fecha;
        this.valorador = valorador;
    }

    public float getNota() {
        return nota;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }
    
    public Usuario getUsuario(){
        return valorador;
    }
    
}
