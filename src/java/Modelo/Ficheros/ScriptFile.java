/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Ficheros;

import Modelo.Valoraciones.ConjuntoValoraciones;
import Modelo.Usuarios.ConjuntoUsuarios;
import Modelo.Peliculas.ConjuntoPeliculas;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ScriptFile {
    private ConjuntoPeliculas peliculas;
    private ConjuntoValoraciones valoraciones;
    private ConjuntoUsuarios usuarios;
    private ArrayList<String> nombres;
    private ArrayList<String> apellidos;
    private String password;
    
    public ScriptFile(){
        peliculas = new ConjuntoPeliculas();
        valoraciones = new ConjuntoValoraciones();
        usuarios = new ConjuntoUsuarios();
        password = "abc123";
        nombres.add("jose");
        nombres.add("ruben");
        nombres.add("gabri");
        nombres.add("marcial");
        nombres.add("diego");
        nombres.add("anastasio");
        nombres.add("manuel");
        nombres.add("rosa");
        nombres.add("raquel");
        nombres.add("michaella");
        nombres.add("abogao");
        nombres.add("tomi");
        apellidos.add("cortada");
        apellidos.add("rosal");
        apellidos.add("pene");
        apellidos.add("caballo");
        apellidos.add("sentado");
        apellidos.add("pinchado");
        apellidos.add("come");
        apellidos.add("borbon");
        apellidos.add("simpson");
        apellidos.add("fumao");
    
    }
    
    public String getApellido(){
        return apellidos.get((int)Math.random()+10)+" "+apellidos.get((int)Math.random()+10);
    }
    
    public String getNombre(){
        return nombres.get((int)Math.random()+12);
    }
    
    public String getNick(){
         String nick = "";
        for(int i=0;i<5;i++){
            nick = nick+(char)(int)Math.random()*26+65;
        }
        return nick;
    }
    
    public String getPassword(){
        return password;
    }
            
}
