<%-- 
    Document   : index
    Created on : 19-nov-2013, 13:06:55
    Author     : Gabriel
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<table class="table table-striped">
    <thead>
        <tr>
        <th>ID</th>
        <th>Año</th>
        <th>Título</th>
        <th>Opciones</th>
        </tr>
    </thead>
    <tbody>
        <% ArrayList<Pelicula> pelis=((ArrayList<Pelicula>)request.getAttribute("pelis"));
        %>
        <% for(int i=0;i<pelis.size();i++){ %>
        <tr>
            <td><%= pelis.get(i).getID() %></td>
            <td><%= pelis.get(i).getAnio() %></td>
            <td><%= pelis.get(i).getTitulo() %></td>
            <td><a href=<%="/Byottafilms/pelicula/?id="+pelis.get(i).getID()%>><span class="glyphicon glyphicon-eye-open"></span></td>
        </tr>
        <% } %>
      </tbody>
</table>
<ul class="pager">
    <% if(pelis.get(0).getID()>1) {%>
        <li><a href=<%="/Byottafilms/?min="+(pelis.get(9).getID()-20)+"&max="+(pelis.get(9).getID()-10)%>>Anterior</a></li>
    <%}%>
    <li><a href=<%="/Byottafilms/?min="+pelis.get(9).getID()+"&max="+(pelis.get(9).getID()+10)%>>Siguiente</a></li>

</ul>