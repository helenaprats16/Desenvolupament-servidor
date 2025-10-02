<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
    <h1>Listado de alumnos:</h1>
    <p>Bienvenido ${nombre}</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th><a>Dni</a></th>
                <th><a>Nombre</a></th>
                <th><a>Edad</a></th>
                <th><a>Ciclo</a></th>
                <th><a>Curso</a></th>
                <th><a>Acciones</a></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${alumno}" var="alumno">
                <tr>
                    <td>&nbsp;${alumno.getDni()}&nbsp;</td>
                    <td>&nbsp;${alumno.getnombre()}&nbsp;</td>
                    <td>&nbsp;${alumno.getciclo()}&nbsp;</td>
                    <td>&nbsp;${alumno.getcurso()}&nbsp;</td>
                    <td>&nbsp;${alumno.getedad()}&nbsp;</td>
                    <td><a class="btn btn-success" href="update-alumno?dni=${alumno.getDni()}">Modificar</a></td>
                    <td><a class="btn btn-danger" href="del-alumno?dni=${alumno.getDni()}">Borrar</a></td>
                	<td><a class="btn btn-success" href="docs-alumno?dni=${alumno.getDni()}">Documentación</a></td>
                	
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
        <a class="btn btn-success" href="add-alumno">Añadir alumno</a>
    </p>
</div>


<%@ include file="../jspf/footer.jspf" %>