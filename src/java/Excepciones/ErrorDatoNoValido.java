/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Excepciones;

/**
 *
 * @author Marci
 */
public class ErrorDatoNoValido extends Exception{

    public ErrorDatoNoValido() {
        super();
    }
    
    public ErrorDatoNoValido(String cadena){
        super(cadena);
    }
    
    
    
}
