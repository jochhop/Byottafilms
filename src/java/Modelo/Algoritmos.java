/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;


import Excepciones.ErrorDatoNoValido;
import Modelo.Peliculas.Pelicula;
import Modelo.Usuarios.Usuario;
import Modelo.Valoraciones.Valoracion;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

/**
 *
 * @author Marci
 */
public class Algoritmos {
 
    public void ejecucionTrainingCoseno(int k, ArrayList<Pelicula> peliculas) throws ErrorDatoNoValido, IOException {
        
        
        
        // Estructura que representa el modelo de similitud (clave: id de pelicula; valor: lista de idPelicula-Similitud).
        HashMap<Long, TreeSet<ItemSim>> modeloSimilitud;
        // Variables auxiliares:
        long tiempoTraining;
        Parametros param = new Parametros();


        // PASO 1: Calcular la similitud de cada item con el resto, saltándonos las valoraciones de los usuarios que forman parte del test.
        tiempoTraining = System.currentTimeMillis();
            modeloSimilitud = getModeloSimilitud_byCoseno(k, peliculas);
        tiempoTraining = System.currentTimeMillis() - tiempoTraining;
        
        
        mostrarModeloSimilitud(modeloSimilitud);
        // SERIALIZAR DESERIALIZAR UN MODELO SIMILIUTD
        String url = k+"-Coseno";
        SerializarModeloSimilitud selializar = new SerializarModeloSimilitud(modeloSimilitud);
        selializar.serializar("C:\\Users\\Marci\\Documents\\NetBeansProjects\\Byottafilms\\src\\java\\Recursos\\"+url);

    }
    
    public double similitudCoseno(Pelicula i1, Pelicula i2){
        // Variables auxiliares:
        double norma1 = 0;
        double norma2 = 0;
        int val1;
        int val2;
        Long key;
        double numerador = 0;
        int comun = 0;
        // Constante de la MEJORA del Factor de relevancia
        int N = 20;
        
        // 1. Nos quedamos con la películas que tenga menos valoraciones.
        if (i1.getValoraciones().size() < i2.getValoraciones().size()){
            for (Entry<Long,Valoracion> e : i1.getValoraciones().entrySet()) {
                key = e.getKey();
                
                // 3. Comprobamos que la otra película haya sido valorada por el mismo usuario.
                if (i2.getValoraciones().containsKey(key)){
                    // 4. Realizamos los cálculos de similitud.
                    val1 = e.getValue().getNota();
                    val2 = i2.getValoraciones().get(key).getNota();

                    norma1 = norma1 + val1 * val1;
                    norma2 = norma2 + val2 * val2;

                    numerador = numerador + val1 * val2;
                    ++comun;
                }
                
            }
        }else{
            for (Entry<Long,Valoracion> e : i2.getValoraciones().entrySet()) {
                key = e.getKey();
                if (i1.getValoraciones().containsKey(key)){
                    val2 = e.getValue().getNota();
                    val1 = i1.getValoraciones().get(key).getNota();

                    norma1 = norma1 + val1 * val1;
                    norma2 = norma2 + val2 * val2;

                    numerador = numerador + val1 * val2;
                    ++comun;
                }
                
            }
        }
        if (norma1 != 0 && norma2 !=0){
            double sim = numerador / (Math.sqrt(norma1) * Math.sqrt(norma2));
            // Aplicamos la MEJORA del Factor de relevancia.
            if (comun < N){
                sim = sim * ((comun*1.0)/N);
            }
            if (sim > 1){
                return 1;
            }
            return sim;
        }else{
            return 0;
        }
        
    }
    
    public HashMap<Long, TreeSet<ItemSim>> getModeloSimilitud_byCoseno(int k, ArrayList<Pelicula> pelisCompletas) {
        // Estructura que representa el modelo de similitud (clave: id de pelicula; valor: lista de idPelicula-Similitud).
        HashMap<Long, TreeSet<ItemSim>> modelo_similitud = new HashMap();
        // Variables auxiliares:
        TreeSet<ItemSim> fila1;
        TreeSet<ItemSim> fila2;
        long id1;
        long id2;
        double similitud;
        ArrayList<Pelicula> peliculas = new ArrayList();

        //List<Pelicula> peliculas = getPeliculasBD(instancia);
        for (int i=0; i<pelisCompletas.size(); ++i){
            if(pelisCompletas.get(i).getMedia() != -1){
                peliculas.add(new Pelicula(pelisCompletas.get(i)));
            }
        }
        long numPeliculas = peliculas.size();

        for (long i=0; i<numPeliculas; ++i){
            //System.out.println(" pelicula "+i+" de "+numPeliculas);
            //###// 1.1: Sacar la película numero i. Nota: estudiar si se pueden sacar todas de golpe.
            //Pelicula it1 = getPeliculaBD_byPos(instancia, i);
            Pelicula it1 = peliculas.get((int)i);
            id1 = it1.getID();
            
            for (long j=i+1; j<numPeliculas; ++j){
                //###// 1.2: Sacar la película numero j.
                //Pelicula it2 = getPeliculaBD_byPos(instancia, j);
                Pelicula it2 = peliculas.get((int)j);
                id2 = it2.getID();
                
                // 1.2: Calculo de la similitud entre it1 e it2.
                similitud = similitudCoseno(it1, it2);
                
                // 1.3: Guardar la similitud en una estructura.
                    //### 1.3: En el modelo definitivo, la similitud se guardará en la base de datos.
                    //###//Similitud s1 = new Similitud(it1.id,it2.id,similitud);
                //     NOTA: Hay que guardar, a la vez, tanto la similitud sim(id1,id2) como sim (id2,id1)
                if (modelo_similitud.containsKey(id1)){
                    fila1 =  modelo_similitud.get(id1);
                    fila1.add(new ItemSim(id2,similitud));
                    if (fila1.size() > k){
                        fila1.remove(fila1.last());
                    }
                    
                    if (modelo_similitud.containsKey(id2)){
                        fila2 =  modelo_similitud.get(id2);
                        fila2.add(new ItemSim(id1,similitud));
                        if (fila2.size() > k){
                            fila2.remove(fila2.last());
                        }
                    }else{
                        modelo_similitud.put(id2, new TreeSet<ItemSim>());
                        modelo_similitud.get(id2).add(new ItemSim(id1,similitud));
                    }
                }else{
                    modelo_similitud.put(id1, new TreeSet<ItemSim>());
                    modelo_similitud.get(id1).add(new ItemSim(id2,similitud));
                    
                    if (modelo_similitud.containsKey(id2)){
                        fila2 =  modelo_similitud.get(id2);
                        fila2.add(new ItemSim(id1,similitud));
                        if (fila2.size() > k){
                            fila2.remove(fila2.last());
                        }
                    }else{
                        modelo_similitud.put(id2, new TreeSet<ItemSim>());
                        modelo_similitud.get(id2).add(new ItemSim(id1,similitud));
                    }
                }
            }
        }
        
        return modelo_similitud;
    }
         
   public ArrayList<Recomendacion> getRecomendaciones( Usuario u, HashMap<Long, TreeSet<ItemSim>> modeloSimilitud, GestorPersistencia instancia){
        ArrayList<Recomendacion> recom_list = new ArrayList();
        GestorBBDD gestor = new GestorBBDD();
        
        // Nota: cargamos todas las medias de las peliculas a memoria para acelerar la ejecución
        ArrayList<Pelicula> peliculas = gestor.selectPeliculas(instancia).getPeliculas();
        // 2. Hallamos las películas que aún no están valoradas.
        ArrayList<Long> peliculasAPredecir = new ArrayList();
        System.out.println(peliculas.size());
        for(Pelicula i : peliculas) {
            if(!peliculasAPredecir.contains(i.getID()) && !u.buscarIdP(i.getID())){
                if(i.getMedia() != -1){
                    peliculasAPredecir.add(i.getID());
                }
            }
        }
        
        // 3. Calculamos las valoraciones para las películas no valoradas.
        TreeSet<ItemSim> valores = new TreeSet();
        double v;
        for(Long p : peliculasAPredecir){
            
            v = calcularPrediccionWA( u,modeloSimilitud.get(p),peliculas);
            if (v != -1){
                valores.add(new ItemSim(p,v));
            }
        }
        
        // 4. Devolvemos las peliculas ordenadas de mayor a menor valoracion.
        // NOTA: se puede devolver la valoracion esperada junto con la pelicula (o un porcentaje: valoracion/5 * 100)
        Pelicula p;
        if(valores.isEmpty()){
                        System.out.println("soy null");

           return null;
        }else{
            double mayor = valores.first().getSim();
            for(ItemSim i : valores){
                p = getPeliculaById(peliculas,i.getId());
                recom_list.add(new Recomendacion(p,(i.getSim()/mayor)*5.0));
            }
        }
        return recom_list;
    }
    private double calcularPrediccionWA(Usuario u, TreeSet<ItemSim> vecinos, ArrayList<Pelicula> peliculas) {
        // Estructura con solamente las valoraciones que un usuario ha realizado sobre los k vecinos mas cercanos a idP
        ArrayList<Valoracion> valoracionesCercanas = new ArrayList();
        for (ItemSim i : vecinos) {
            if ((u.buscarIdP(i.getId()))){
                // 1.3. Si es así se almacena en la estructura valoracionesCercanas.
                valoracionesCercanas.add(u.getValoraciones().get(i.getId()));
            }
        }
        
        if (!valoracionesCercanas.isEmpty()){
            // PASO 2: Conseguir las medias.

            // 2.1. Media del usuario en cuentión.
            double mediaU = u.getMedia();
            // NOTA: Necesitamos la media de cada pelicula vecina. Se irá pidiendo con forme haga falta.

            // PASO 3: Cálculo de la predicción.
            double mediaK;
            double numerador = 0;
            double denominador = 0;
            long idPAux;
            ItemSim itemSim;
            Iterator<Valoracion> it1 = valoracionesCercanas.iterator();
            Valoracion v;

            while(it1.hasNext()){
                v = it1.next();
                idPAux = v.getPelicula();
                mediaK = getPeliculaById(peliculas, idPAux).getMedia();
                itemSim = buscarVecino(idPAux, vecinos);

                numerador = numerador + itemSim.getSim()*(v.getNota()-mediaK) ;
                denominador = denominador + itemSim.getSim();
            }

            if (denominador != 0){
                return mediaU + numerador/denominador;
            }else{
                return 0;
            }
        }else{
            return -1;
        }
        
    } 
    private ItemSim buscarVecino(long idP, TreeSet<ItemSim> vecinos) {
        Iterator<ItemSim> it = vecinos.iterator();
        ItemSim i;
        
        while (it.hasNext()){
            i = it.next();
            
            if (i.getId()== idP){
                return i;
            }
        }
        
        return null;
    }
    public Pelicula getPeliculaById(ArrayList<Pelicula> peliculas, long idPelicula){
        boolean encontrado = true;
        int i = 0;
        Pelicula aux = new Pelicula();
        while(encontrado && i<peliculas.size()){
            if(peliculas.get(i).getID() == idPelicula){
                encontrado = false;
                aux = peliculas.get(i);

            }
            i++;
        }
        return aux;
    }
    public void mostrarModeloSimilitud(HashMap<Long, TreeSet<ItemSim>> modeloSimilitud) {
        System.out.println("ESTADO: Modelo de similitud creado.");
        System.out.println("  Modelo de similitud:");
        long centinela = 0;
        for(Entry<Long, TreeSet<ItemSim>> e : modeloSimilitud.entrySet()){
//            if (e.getValue() == null){
//                System.out.println(" Existe una fila del modelo de similitud vacia - idP="+e.getKey());
//                centinela = e.getKey();
//            }
            System.out.println("    Pelicula("+e.getKey()+"):");
            for(ItemSim i : e.getValue()){
                System.out.println("      ("+i.getId()+"-"+i.getSim()+")");
            }
            System.out.println();
        }
//        System.out.println(" No existe una fila del modelo de similitud vacia - Centinela="+centinela);
    }
}
