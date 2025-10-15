<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<h1>Listado de modulos:</h1>
	<p>Bienvenido ${nombre}</p>
	<table class="table table-striped">

		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Horas</th>
				<th>Abreviatura</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${moduloList}" var="modulo">
				<tr>
					<td>&nbsp;${modulo.id}&nbsp;</td>
					<td>&nbsp;${modulo.nombre}&nbsp;</td>
					<td>&nbsp;${modulo.horas}&nbsp;</td>
					<td>&nbsp;${modulo.abreviatura}&nbsp;</td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
		<p>Introduzca los datos del nuevo modulo:</p>
		<mvc:form method="post" action="add-modulo" modelAttribute="moduloEdit">
			<div class="form-row">
			<div class="col">
				<mvc:label path="nombre">Nombre:</mvc:label>
				<mvc:input type="text" id="nombre" path="nombre" class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="horas">Horas:</mvc:label>
				<mvc:input type="number" id="horas" path="horas" class="form-control" />
				<mvc:errors path="horas" cssClass="text-warning" />
			</div>
			<div class="col">
				<mvc:label path="abreviatura">Abreviatura:</mvc:label>
				<mvc:input type="text" id="abreviatura" path="abreviatura" class="form-control" />
				<mvc:errors path="abreviatura" cssClass="text-warning" />
			</div>
			</div>
			<br>
			<input type="submit" value="Añadir modulo" class="btn btn-success">
		</mvc:form>


</div>


<%@ include file="../jspf/footer.jspf"%>