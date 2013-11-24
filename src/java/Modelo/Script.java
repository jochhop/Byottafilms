/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Script {
    private ConjuntoPeliculas peliculas;
    private ConjuntoValoraciones valoraciones;
    private ConjuntoUsuarios usuarios;
    private ArrayList<String> nombres;
    private ArrayList<String> ap1;
    private ArrayList<String> ap2;
    private ArrayList<String> nicks;
    
    Script(){
        peliculas = new ConjuntoPeliculas();
        valoraciones = new ConjuntoValoraciones();
        usuarios = new ConjuntoUsuarios();
    }
}
