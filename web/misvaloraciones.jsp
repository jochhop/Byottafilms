<%-- 
    Document   : misvaloraciones
    Created on : 16-dic-2013, 17:59:12
    Author     : jose
--%>
<%@page import="Modelo.Usuarios.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%
    HttpSession sesion_actual = request.getSession(true);
    Usuario user=(Usuario)sesion_actual.getAttribute("user");
%>
<h2>Tus valoraciones <%=user.getNombre()%></h2>
<table class="table table-striped">
    <thead>
        <tr>
        <th>#</th>
        <th>Año</th>
        <th>Título</th>
        <th>Nota media</th>
        <th>Opciones</th>
        </tr>
    </thead>
    <tbody>
        <% ArrayList<Pelicula> pelis=((ArrayList<Pelicula>)request.getAttribute("pelis"));%>
        <% for(int i=0;i<pelis.size();i++){ %>
        <tr>
            <td><%= pelis.get(i).getID() %></td>
            <td><%= pelis.get(i).getAnio() %></td>
            <td><%= pelis.get(i).getTitulo() %></td>
            <td><%= String.format("%.2f", pelis.get(i).getMedia()) %></td>
            <td><a title="Ver detalles" href=<%="/Byottafilms/pelicula?id="+pelis.get(i).getID()%>><span class="glyphicon glyphicon-eye-open"></span></td>
        </tr>
        <% } %>
      </tbody>
</table>
