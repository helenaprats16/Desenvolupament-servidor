<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

<div class="container">
    <h1>Documentación del alumno:</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Dni</th>
                <th>Nombre</th>
                <th>Ciclo</th>
                <th>Curso</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>&nbsp;${alumno.getDni_alumno()}&nbsp;</td>
                <td>&nbsp;${alumno.getNombre_alumno()}&nbsp;</td>
                <td>&nbsp;${alumno.getCiclo_alumno()}&nbsp;</td>
                <td>&nbsp;${alumno.getCurso_alumno()}&nbsp;</td>
            </tr>
        </tbody>
    </table>
    <br>
    <p>Si desea borrar este alumno pulse en "Borrar", si no pulse en "Volver"</p>
    <br>
</div>

        <div class="col">
         <td><a class="btn btn-danger" href="del-alumnoborrar?dni=${alumno.getDni_alumno()}">Borrar</a></td>
         <td><a class="btn btn-warning" href="list-alumno">Volver</a></td>
        	
        </div>
  

<%@ include file="../jspf/footer.jspf" %>
