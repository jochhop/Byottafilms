/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;


import Modelo.Peliculas.Pelicula;
import Modelo.Usuarios.ConjuntoUsuarios;
import Modelo.Usuarios.Usuario;
import Modelo.Valoraciones.Valoracion;
import Persistencia.GestorPersistencia;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityExistsException;

/**
 *
 * @author Pc
 */
public class Cargadatos implements Serializable{
    /*ArrayList<Pelicula> pelis;
    ArrayList<Usuario> usuarios;
    HashMap mapausuarios;
    String archivopelis;
    String archivovaloraciones;
    Test test;
    */
    /**
     * 
     */
    private GestorPersistencia gestor;
    
    public Cargadatos(GestorPersistencia gp){
       gestor = gp;
    }
    
    public void cargarPeliculas(String archivo, ArrayList<Pelicula> pelis)
    {
        BufferedReader br = null;
        String line = "";
        int i = 0;
        Pelicula temp=null;
        try {
		br = new BufferedReader(new FileReader(archivo));
                line = br.readLine();
                
                gestor.getEntityManager().getTransaction().begin();
                
		while ((line = br.readLine()) != null) {
			String[] peli = line.split("\t");
                            if(peli[1].contains("NULL")){
                                temp = new Pelicula((long)Integer.parseInt(peli[0]),0,peli[2]);
                            }else{
                                temp = new Pelicula((long)Integer.parseInt(peli[0]),Integer.parseInt(peli[1]),peli[2]);
                            }
                            //System.out.println("id |" + (long)Integer.parseInt(peli[0]) + "| Nombre: |" + peli[2] + "| anno: " + Integer.parseInt(peli[1]));
                                                        
                            gestor.getEntityManager().persist(temp);                            
                            
                            pelis.add(temp);
                            peli[0]="0";peli[1]="0";peli[2]="";
                        
		}             
                gestor.getEntityManager().getTransaction().commit();
                System.out.println("peliculas cargadas");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
        }catch(NumberFormatException e) {
            System.out.println("la cagaste");
            e.printStackTrace();
	} finally {
            
                if(gestor.getEntityManager().getTransaction().isActive())
                    gestor.getEntityManager().getTransaction().rollback();
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    }
   
    public void cargarValoraciones(String archivo, ArrayList<Pelicula> pelis, ConjuntoUsuarios usuarios)
    {
        BufferedReader br2 = null;
        String line2 = "";
        int i = 0;
        int j=0;                     
                
	try {                
                br2 = new BufferedReader(new FileReader(archivo));
                line2 = br2.readLine(); //para quitar la linea de cabecera
                Valoracion tempv;
                Usuario tempu;
                System.out.println("cargando valoraciones...");
                gestor.getEntityManager().getTransaction().begin();
		while (((line2 = br2.readLine()) != null)) {
                        String[] cadenavaloracion = line2.split(",");
                        long indicePel = Integer.parseInt(cadenavaloracion[1]);
                        long indiceUsu = (long)Integer.parseInt(cadenavaloracion[0]);
                        tempv = new Valoracion(Integer.parseInt(cadenavaloracion[2]),indiceUsu,indicePel);
                        if(usuarios.getUsuarioById(indiceUsu)!=null){
                            usuarios.getUsuarioById(indiceUsu).getValoraciones().put(indicePel, tempv);
                        }else{  
                            tempu = new Usuario(indiceUsu);
                            tempu.getValoraciones().put(indicePel, tempv);
                            usuarios.newUsuario(tempu);
                            gestor.getEntityManager().persist(tempu);
                            j++;
                        }                                                                       
                        gestor.getEntityManager().persist(tempv);
                        pelis.get((int)indicePel-1).setValoracion(tempv);
                        
                       
                        //System.out.println("usuario" + tempv.iduser+"|"+usuarios.get(indiceusu-1).id);
                        i++;
                        //System.out.println(((i/3085))+ "% completo");
                }
                gestor.getEntityManager().getTransaction().commit();
                System.out.println(j+ " usuarios cargados");
                System.out.println("valoraciones cargadas");
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
        }catch(NumberFormatException e) {
            System.out.println("la cagaste");
            e.printStackTrace();
        }catch(EntityExistsException e){
            if(gestor.getEntityManager().getTransaction().isActive())
                    gestor.getEntityManager().getTransaction().rollback();
	} finally {
		if (br2 != null) {
			try {br2.close();} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    }
       
    
    public void calcularMedias(ArrayList<Usuario> usuarios, ArrayList<Pelicula> peliculas) {
        
        // 1. Calculo de la media de valoraciones para cada usuario
        double numerador;
        Iterator<Usuario> it1 = usuarios.iterator();
        Usuario u;
        try{
            gestor.getEntityManager().getTransaction().begin();

            while(it1.hasNext()){
                numerador = 0;
                u = it1.next();
                for(Map.Entry<Long,Valoracion> v : u.getValoraciones().entrySet()){
                    numerador += v.getValue().getNota();
                }
                u.setMedia(numerador/u.getValoraciones().size());
            }

            // 2. Calculo de la media de valoraciones para cada pelicula
            Iterator<Pelicula> it2 = peliculas.iterator();
            Pelicula p;
            while(it2.hasNext()){
                numerador = 0;
                p = it2.next();
                
                for(Map.Entry<Long,Valoracion> v : p.getValoraciones().entrySet()){
                    numerador += v.getValue().getNota();
                }
                if(p.getValoraciones().size() != 0){
                    p.setMedia(numerador/p.getValoraciones().size());
                }else{
                    p.setMedia(-1);
                }
            }
            gestor.getEntityManager().getTransaction().commit();
        }catch(NumberFormatException e) {
            System.out.println("la cagaste");
            e.printStackTrace();
	} finally {
            
                if(gestor.getEntityManager().getTransaction().isActive())
                    gestor.getEntityManager().getTransaction().rollback();
		
	}
    }
    
      
    
    
    /*
    public static HashMap<Long, Double> cargarPelisHashMap(ArrayList<Pelicula> pelis){
        HashMap<Long,Double>pelishm = new HashMap();
        for(int i=0;i<pelis.size();i++){
            pelishm.put(pelis.get(i).getIdPelicula(), (double) pelis.get(i).getMedia());            
        }
        return pelishm;
    }
    */
/*
    public static void main(String[] args) throws ErrorDatoInvalido, IOException, ClassNotFoundException {
        // TODO code application logic here
        final ArrayList<Pelicula> pelis=new ArrayList<>(); //lista peliculas
        final ArrayList<Usuario> usuarios=new ArrayList<>(); //lista usuarios
        HashMap mapausuarios = new HashMap(); //para buscar la posicion de un usuario en el vector a partir de su id
        String archivopelis = "src/algoritmos/peliculas2.csv";
	String archivovaloraciones = "src/algoritmos/ratings7.csv";
        final Test test = new Test();
	
        cargarpeliculas(archivopelis, pelis);
        cargarvaloraciones(archivovaloraciones, pelis, usuarios, mapausuarios);
        calcularMedias(usuarios,pelis);
      */
        ///*******************TRAININGS
        
        /*ExecutorService exec = Executors.newFixedThreadPool(5);
        ExecutorService exec2 = Executors.newFixedThreadPool(5);
        for (int k = 10; k <= 30; k+=10) {
            //...execute the task to run concurrently as a runnable:
            final int i = k;
            
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Running in: " + Thread.currentThread());
                    try {
                        // do the work to be done in its own thread
                        test.training(i, 0, usuarios, pelis);
                    } catch (            IOException | ErrorDatoInvalido ex) {
                        Logger.getLogger(Cargadatos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            });
            exec2.execute(new Runnable(){
                @Override
                public void run(){
                    try {
                        test.training(i, 1, usuarios, pelis);
                    } catch (            IOException | ErrorDatoInvalido ex) {
                        Logger.getLogger(Cargadatos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            
        }
        //Tell the executor that after these 100 steps above, we will be done: 
        exec.shutdown();
        exec2.shutdown();
        try {
            // The tasks are now running concurrently. We wait until all work is done, 
            // with a timeout of 50 seconds:
            boolean b = exec.awaitTermination(60, TimeUnit.MINUTES);
            boolean C = exec2.awaitTermination(60, TimeUnit.MINUTES);
            // If the execution timed out, false is returned:
            System.out.println("All done: " + b);
        } catch (InterruptedException e) {System.out.println(e.getMessage());}
        */
        ///**********************TESTS
  /*  
        for(int k=10;k<=30;k+=10){
            System.out.println("K = "+k);
            //Coseno + I+A con n = 0
            //test.ejecucionTest(k, 0, 0, 0, usuarios, pelis);
            //Coseno + I+A con n = 2
            //test.ejecucionTest(k, 0, 0, 2, usuarios, pelis);
            //Coseno + I+A con n = 4
            //test.ejecucionTest(k, 0, 0, 4, usuarios, pelis);
            //Coseno + I+A con n = 8
            //test.ejecucionTest(k, 0, 0, 8, usuarios, pelis);
            //Coseno + WA
            test.ejecucionTest(k, 0, 1, 0, usuarios, pelis);
            //Pearson + I+A con n = 0
            //test.ejecucionTest(k, 1, 0, 0, usuarios, pelis);
            //Pearson + I+A con n = 2
            //test.ejecucionTest(k, 1, 0, 2, usuarios, pelis);
            //Pearson + I+A con n = 4
            //test.ejecucionTest(k, 1, 0, 4, usuarios, pelis);
            //Pearson + I+A con n = 8
            //test.ejecucionTest(k, 1, 0, 8, usuarios, pelis);
            //Coseno + WA
            test.ejecucionTest(k, 1, 1, 0, usuarios, pelis);
            
        }
       
        
	System.out.println("Done");
        //______________________

    }
    */
}
