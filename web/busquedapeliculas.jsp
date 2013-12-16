<%-- 
    Document   : busquedapeliculas
    Created on : 16-dic-2013, 21:54:44
    Author     : jose
--%>
<%@page import="Modelo.Usuarios.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<% ArrayList<Pelicula> pelis=((ArrayList<Pelicula>)request.getAttribute("pelis"));%>
<h2>Resultados encontrados <%=pelis.size()%></h2>
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
        <% for(int i=0;i<pelis.size();i++){ %>
        <tr>
            <td><%= pelis.get(i).getID() %></td>
            <td><%= pelis.get(i).getAnio() %></td>
            <td><%= pelis.get(i).getTitulo() %></td>
            <%if(pelis.get(i).getMedia()!=-1){%>
                <td><%= String.format("%.2f", pelis.get(i).getMedia()) %></td>
            <%}else{%>
                <td>-</td>
            <%}%>
            <td><a title="Ver detalles" href=<%="/Byottafilms/pelicula?id="+pelis.get(i).getID()%>><span class="glyphicon glyphicon-eye-open"></span></td>
        </tr>
        <% } %>
      </tbody>
</table>

