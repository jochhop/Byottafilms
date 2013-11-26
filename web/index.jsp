<%-- 
    Document   : index
    Created on : 19-nov-2013, 13:06:55
    Author     : Gabriel
--%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%
            Pelicula p = new Pelicula(11, "Titulo", "Descripcion", null, null, null);
            RequestDispatcher dispatcher = request.getRequestDispatcher("head.jsp");
            dispatcher.include(request, response);
%>
        
        <h1>Estamos trabajando para hacer la mejor web de recomendación de peliculas EVER!!</h1>
<%
            dispatcher = request.getRequestDispatcher("footer.jsp");
            dispatcher.include(request, response);
%>
