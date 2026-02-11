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
    <p>Si desea añadir nueva documentación introduzca los datos:</p>
    <br>
    <font color="red">${errores}</font>
</div>
<mvc:form method="post" action="add-docAlumno" modelAttribute="docAlumno">
    <mvc:errors path="comentario" cssClass="text-warning"/><br>
    <mvc:errors path="tipo" cssClass="text-warning"/>
    <input name="dni" type="hidden" value="${alumno.getDni_alumno()}"/>
    <div class="form-row">
        <div class="col">
            <mvc:label path="comentario">Comentario:</mvc:label>
            <mvc:textarea path="comentario" rows="2" cols="70"/>
        </div>
        <div class="col">
            <mvc:label path="tipo">Tipo:</mvc:label>
            <ul>
                <mvc:radiobuttons path="tipo" items="${opcionesTipoDoc}" element="p"/>
            </ul>
        </div>
        <div class="col">
            <input type="submit" value="Añadir" class="btn btn-success"/>
        </div>
    </div>
</mvc:form>

<table class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Tipo</th>
            <th>Comentario</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${docAlumnos}" var="docAlumno">
            <tr>
                <td>&nbsp;${docAlumno.getId()}&nbsp;</td>
                <td>&nbsp;${docAlumno.getTipo()}&nbsp;</td>
                <td>&nbsp;${docAlumno.getComentario()}&nbsp;</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../jspf/footer.jspf" %>
