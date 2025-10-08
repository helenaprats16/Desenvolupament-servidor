<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
	<div class="container">
		<p>
			<font color="red">${errores}</font>
		</p><!-- llamamos al atributo errores -->
		<form action="login" method="post">
			<p>Introduzca su nombre:</p>
			<p><input type="text" name="nombre"/></p> 
			<p>Introduzca su contraseña: </p> 
			<p><input type="password" name="password"/></p>
			<input type="submit" value="Login"/>
	</form>
	
	</div>
<%@ include file="../jspf/footer.jspf" %>