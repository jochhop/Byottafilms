<%-- 
    Document   : admin
    Created on : 16-dic-2013, 22:18:36
    Author     : jose
--%>
<%@page import="Modelo.Usuarios.Usuario"%>
<% 
        HttpSession session_actual=request.getSession(true);
        Usuario user=(Usuario)session_actual.getAttribute("user");
    %>
<%if(user!=null && user.getRol()>0){%>
    <ul class="nav nav-tabs">
      <li class="active"><a href="#pelicula" data-toggle="tab">Añadir película</a></li>
      <li><a href="#usuario" data-toggle="tab">Añadir usuario</a></li>
    </ul>
    <div class="tab-content">
        <div id="pelicula" class="tab-pane fade active in">
            <form class="form-horizontal" role="form" style="margin-top: 30px;" method="POST" action="/Byottafilms/insertarpeli">
            <div class="form-group">
              <label for="inputTitulo" class="col-sm-2 control-label">Título</label>
              <div class="col-sm-10">
                <input type="text" name="titulo" class="form-control" id="inputTitulo" placeholder="Título de la película">
              </div>
            </div>
            <div class="form-group">
              <label for="inputAnio" class="col-sm-2 control-label">Año</label>
              <div class="col-sm-10">
                <input type="text" name="anio" class="form-control" id="inputAnio" placeholder="Año de la película">
              </div>
            </div>
            <div class="form-group">
              <label for="inputMedia" class="col-sm-2 control-label">Media</label>
              <div class="col-sm-10">
                <input type="text" name="media" class="form-control" id="inputMedia" placeholder="Nota de la película">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPortada" class="col-sm-2 control-label">Portada</label>
              <div class="col-sm-10">
                <input type="file" name="portada" class="form-control" id="inputPortada" placeholder="Portada de la película">
              </div>
            </div>
            <div class="form-group">
              <label for="inputTrailer" class="col-sm-2 control-label">Trailer</label>
              <div class="col-sm-10">
                <input type="text" name="trailer" class="form-control" id="inputTitulo" placeholder="Trailer de la película">
              </div>
            </div>
            <div class="form-group">
              <label for="inputDesc" class="col-sm-2 control-label">Descripción</label>
              <div class="col-sm-10">
                  <textarea class="form-control" name="descripcion" id="inputDesc" placeholder="Descripción de la película"></textarea>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Guardar película</button>
                <button type="reset" class="btn btn-default">Borrar formulario</button>
              </div>
            </div>
            </form>
        </div>
        <div id="usuario" class="tab-pane fade">
            <form class="form-horizontal" role="form" style="margin-top: 30px;" method="POST" action="/Byottafilms/nuevapeli">
            <div class="form-group">
              <label for="inputNombre" class="col-sm-2 control-label">Nombre</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputNombre" placeholder="Nombre">
              </div>
            </div>
            <div class="form-group">
              <label for="inputApe" class="col-sm-2 control-label">Apellidos</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputApe" placeholder="Apellidos">
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <label for="inputNick" class="col-sm-2 control-label">Nick</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputNick" placeholder="Nick">
              </div>
            </div>
            <div class="form-group">
              <label for="inputFoto" class="col-sm-2 control-label">Foto</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" id="inputFoto" placeholder="Sube una foto">
              </div>
            </div>
            <div class="form-group">
              <label for="inputRol" class="col-sm-2 control-label">Rol</label>
              <div class="col-sm-10">
                <select class="form-control" id="inputRol">
                    <option>Usuario normal</option>
                    <option>Administrador</option>
                  </select>
              </div>
            </div>        
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Guardar usuario</button>
                <button type="reset" class="btn btn-default">Borrar formulario</button>
              </div>
            </div>
            </form>
        </div>
    </div>
<%}else{%>
    <h2>Acceso denegado.</h2>
<%}%>