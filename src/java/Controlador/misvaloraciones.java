/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.GestorBBDD;
import Modelo.Peliculas.Pelicula;
import Modelo.Usuarios.Usuario;
import Modelo.Valoraciones.Valoracion;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose
 */
public class misvaloraciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion_actual = request.getSession(true);
            Usuario user=(Usuario)sesion_actual.getAttribute("user");
            ArrayList<Valoracion> valoraciones;
            ArrayList<Pelicula> pelis = null;
            if(user!=null){
                valoraciones=GestorBBDD.selectValoracionesByUsuario(GestorPersistencia.instancia(), user.getId());
            }else{
                valoraciones=null;
            }
            if(valoraciones!=null){
                Pelicula peli;
                for(int i=0;i<valoraciones.size();i++){
                    peli=(Pelicula)GestorBBDD.selectPeliculaById(GestorPersistencia.instancia(), valoraciones.get(i).getPelicula());
                    if(peli!=null){
                        //pelis.add(peli);
                    }
                }
            }else{
                pelis=null;
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("head.jsp");
            dispatcher.include(request, response);
            request.setAttribute("pelis",pelis);
            dispatcher = request.getRequestDispatcher("misvaloraciones.jsp");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("footer.jsp");
            dispatcher.include(request, response);
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
