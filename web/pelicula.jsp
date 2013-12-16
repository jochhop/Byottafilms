<%-- 
    Document   : pelicula
    Created on : 13-dic-2013, 16:02:17
    Author     : jose
--%>
<%@page import="Controlador.getInfoPeli"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%Pelicula peli=(Pelicula)request.getAttribute("peli");
  getInfoPeli info=(getInfoPeli)request.getAttribute("info");
%>
<style type="text/css">
    .star-rating{ list-style:none; margin: 0px; padding:0px; width: 100px; height: 20px; position: relative; background: url(http://www.cssblog.es/ejemplos/star_rating.gif) top left repeat-x; } .star-rating li{ padding:0px; margin:0px; /**/ float: left; /* */ } .star-rating li a{ display:block; width:20px; height: 20px; text-decoration: none; text-indent: -9000px; z-index: 20; position: absolute; padding: 0px; } .star-rating li a:hover{ background: url(http://www.cssblog.es/ejemplos/star_rating.gif) left bottom; z-index: 1; left: 0px; } .star-rating a.one-star{ left: 0px; } .star-rating a.one-star:hover{ width:20px; } .star-rating a.two-stars{ left:20px; } .star-rating a.two-stars:hover{ width: 40px; } .star-rating a.three-stars:hover{ width: 60px; } .star-rating a.three-stars{ left: 40px; } .star-rating a.four-stars{ left: 60px; } .star-rating a.four-stars:hover{ width: 80px; } .star-rating a.five-stars{ left: 80px; } .star-rating a.five-stars:hover{ width: 100px; }
</style>
<h1 class="center"><%=peli.getTitulo()%></h1>
<div class="row">
    <div class="col-md-3">
        <img style="width:100%; margin-bottom:20px;" src=<%=info.getPoster()%>></img>
        <!-- Button trigger modal -->
        <button class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#myModal">
          Ver trailer
        </button>
    </div>
        <div class="col-md-7">
            <table class="table">
                <tr>
                   <td style="font-weight: bold">Título</td>
                   <td><%=peli.getTitulo()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Año</td>
                   <td><%=peli.getAnio()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Género</td>
                   <td><%=info.getEstilo()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Director</td>
                   <td><%=info.getDirector()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Guionista</td>
                   <td><%=info.getGuionista()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Actores</td>
                   <td><%=info.getActores()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Sinopsis</td>
                   <td><%=info.getDescripcion()%></td>
                </tr>
            </table>
        </div>
    <div class="col-md-2">
        <div class="byotta" style="width: 200px; height:162px;margin-bottom: 20px;background: url(http://static.freepik.com/foto-gratis/dibujos-animados-de-bellota_17-706205826.jpg);background-size: 200px;background-repeat: no-repeat">
            <p title="Puntuación media" style="font-size:70pt;font-weight:bold;color:white;padding-top:10px;padding-right:10px;text-align: center;">4.5</p>
        </div>
        <ul class="star-rating">
            <li><a class="one-star" title="Valora 1 estrella de 5" href="#">1</a></li>
            <li><a class="two-stars" title="Valora 2 estrellas de 5" href="#">2</a></li>
            <li><a class="three-stars" title="Valora 3 estrellas de 5" href="#">3</a></li>
            <li><a class="four-stars" title="Valora 4 estrellas de 5" href="#">4</a></li>
            <li><a class="five-stars" title="Valora 5 estrellas de 5" href="#">5</a></li>
        </ul>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Trailer de <%=peli.getTitulo()%></h4>
      </div>
      <div class="modal-body">
        <%=info.getTrailer()%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
