/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {
    String NomLog = "Recursos/log.txt";
    
    public void EscribirLog(String LineaLog) { 
        int hora, minutos, segundos, dia, mes;
        
        try{
            FileWriter fw = new FileWriter(NomLog, true);
            Calendar Fecha = new GregorianCalendar();
            hora = Fecha.get(Calendar.HOUR_OF_DAY);
            minutos = Fecha.get(Calendar.MINUTE);
            segundos = Fecha.get(Calendar.SECOND);
            dia = Fecha.get(Calendar.DAY_OF_MONTH);
            mes = Fecha.get(Calendar.MONTH);
            fw.write(LineaLog + " --------h:" + hora + " min:" + minutos +" seg:"+ segundos + " dia:" + dia + " mes:"+ mes + "\r\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}