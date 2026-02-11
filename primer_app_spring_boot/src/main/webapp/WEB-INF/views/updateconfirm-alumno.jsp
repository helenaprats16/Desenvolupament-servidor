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
                <td>&nbsp;${alumnoEdit.getDni()}&nbsp;</td>
                <td>&nbsp;${alumnoEdit.getNombre()}&nbsp;</td>
                <td>&nbsp;${alumnoEdit.getCiclo()}&nbsp;</td>
                <td>&nbsp;${alumnoEdit.getCurso()}&nbsp;</td>
            </tr>
        </tbody>
    </table>
    <br>
    <p>Si desea modificar este alumno con los datos mostrados pulse en "Modificar", si no pulse en "Volver al listado"</p>
    <br>
</div>
	<mvc:form method="post" action="updateconfirm-alumno" modelAttribute="alumnoEdit">
        <!-- Dades ocultes -->
        <mvc:hidden path="dni"/>
        <mvc:hidden path="nombre"/>
        <mvc:hidden path="edad"/>
        <mvc:hidden path="ciclo"/>
        <mvc:hidden path="curso"/>

        <input type="submit" value="Confirmar modificación" class="btn btn-danger"/>
        <a href="list-alumno" class="btn btn-warning">Volver al listado</a>
    </mvc:form>
  

<%@ include file="../jspf/footer.jspf" %>
