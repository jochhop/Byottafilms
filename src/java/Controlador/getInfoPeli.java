/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.JSON.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author jose
 */
public class getInfoPeli {
        String urlTrailer;
        String urlPoster;
        String descripcion;
        String actores;
        String director;
        String estilo;
        String guionista;
        
        public getInfoPeli(String nombrepeli) throws MalformedURLException, IOException{
            String urlaux2;
            String urlaux3;
            urlaux2 = "http://gdata.youtube.com/feeds/api/videos?"
                    + "q="+nombrepeli.toString().replaceAll(" ", "+")+"official+trailer+movie"
                    + "&max-results=1"
                    + "&alt=json";
            
            urlaux3 = "http://www.omdbapi.com/?"
                    + "t="+nombrepeli.toString().replaceAll(" ", "%20");
            
            URL urlv = new URL(urlaux2);
            URL urlf = new URL(urlaux3);
            
            //URLConnection connection = url.openConnection();
            URLConnection connection2 = urlv.openConnection();
            URLConnection connection3 = urlf.openConnection();
            
            //connection.addRequestProperty("Referer", "http://localhost/");
            connection2.addRequestProperty("Referer", "http://localhost/");
            connection3.addRequestProperty("Referer", "http://localhost/");
            
            String line;
            
            //StringBuilder builder = new StringBuilder();
            StringBuilder builder2 = new StringBuilder();
            StringBuilder builder3 = new StringBuilder();
            
            /*BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null) {
                    builder.append(line);
            }*/
            
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
            while((line = reader2.readLine()) != null) {
                    builder2.append(line);
            }
            
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
            while((line = reader3.readLine()) != null) {
                    builder3.append(line);
            }

            //JSONObject json = new JSONObject(builder.toString());
            //JSONObject auxson = new JSONObject(json.getJSONObject("responseData").getJSONArray("results").get(0).toString());
            
            JSONObject json2 = new JSONObject(builder2.toString());
            System.out.println("el json 2: "+json2.toString());
            if(!json2.getJSONObject("feed").isNull("entry")){
                JSONObject auxson2 = new JSONObject(json2.getJSONObject("feed").getJSONArray("entry").get(0).toString());
                JSONObject auxson3 = new JSONObject(auxson2.getJSONArray("link").get(0).toString());
                urlTrailer=auxson3.getString("href").replaceAll("&feature=youtube_gdata", "").toString();
                urlTrailer=urlTrailer.substring(31);
            }else{
                urlTrailer="";
            }

            JSONObject json3 = new JSONObject(builder3.toString());
            System.out.println("json3 es "+json3.toString());
            if(!json3.isNull("Poster")){
                urlPoster=json3.getString("Poster").toString();
                descripcion=json3.getString("Plot").toString();
                actores=json3.getString("Actors").toString();
                estilo=json3.getString("Genre").toString();
                director=json3.getString("Director").toString();
                guionista=json3.getString("Writer").toString();
            }else{
                urlPoster="";
                descripcion="";
                actores="";
                estilo="";
                director="";
                guionista="";
            }
            
        }
        
        public String getPoster(){return urlPoster;}
        public String getTrailer(){return urlTrailer;}
        public String getDescripcion(){return descripcion;}
        public String getActores(){return actores;}
        public String getEstilo(){return estilo;}
        public String getDirector(){return director;}
        public String getGuionista(){return guionista;}
        public void setPoster(String poster){urlPoster=poster;}
        public void setTrailer(String trailer){urlTrailer=trailer;}
        public void setDescripcion(String desc){descripcion=desc;}
        public void setActores(String act){actores=act;}
        public void setEstilo(String est){estilo=est;}
        public void setDirector(String dir){director=dir;}
        public void setGuionista(String gui){guionista=gui;}
}
