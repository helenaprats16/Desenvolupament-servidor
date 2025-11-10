<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
  <h1>Alumno a modificar:</h1>
  <p>
    <font color="red">${errores}</font>
  </p>
  <mvc:form method="post" action="update-alumno" modelAttribute="alumnoEdit">
    <div class="form-row">
      <div class="col">
        <mvc:label path="dni">Dni:</mvc:label>
        <mvc:input path="dni" type="text" class="form-control"/>
        <mvc:errors path="dni" cssClass="text-warning"/>
      </div>
      <div class="col">
        <mvc:label path="nombre">Nombre:</mvc:label>
        <mvc:input type="text" id="nombre" path="nombre" class="form-control"/>
        <mvc:errors path="nombre" cssClass="text-warning"/>
      </div>
    </div>
    <div class="form-row">
      <div class="col">
        <mvc:label path="edad">Edad:</mvc:label>
        <mvc:input type="number" id="edad" path="edad" class="form-control"/>
        <mvc:errors path="edad" cssClass="text-warning"/>
      </div>
      <div class="col">
        <mvc:label path="ciclo">Ciclo:</mvc:label>
        <mvc:input type="text" id="ciclo" path="ciclo" class="form-control"/>
        <mvc:errors path="ciclo" cssClass="text-warning"/>
      </div>
      <div class="col">
        <mvc:label path="curso">Curso:</mvc:label>
        <mvc:input type="number" id="curso" path="curso" class="form-control"/>
        <mvc:errors path="curso" cssClass="text-warning"/>
      </div>
    </div>
    <br><input type="submit" value="Modificar" class="btn btn-success">
  </mvc:form>
</div>

<%@ include file="../jspf/footer.jspf"%>

