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
        
        public getInfoPeli(String nombrepeli) throws MalformedURLException, IOException{
            String urlaux2;
            String urlaux3;
            urlaux2 = "http://gdata.youtube.com/feeds/api/videos?"
                    + "q="+nombrepeli.toString().replaceAll(" ", "+")+"official+trailer"
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
            JSONObject auxson2 = new JSONObject(json2.getJSONObject("feed").getJSONArray("entry").get(0).toString());
            JSONObject auxson3 = new JSONObject(auxson2.getJSONArray("link").get(0).toString());
           
            JSONObject json3 = new JSONObject(builder3.toString());
            
            urlTrailer=auxson3.getString("href").replaceAll("&feature=youtube_gdata", "").toString();
            //System.out.println(json3.toString());
            urlPoster=json3.getString("Poster").toString();
            descripcion=json3.getString("Plot").toString();
        }
        
        public String getPoster(){return urlPoster;}
        public String getTrailer(){return urlTrailer;}
        public String getDescripcion(){return descripcion;}
}
