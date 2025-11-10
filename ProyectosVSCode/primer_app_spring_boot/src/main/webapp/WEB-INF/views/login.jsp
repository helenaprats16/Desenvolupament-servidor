<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>	
<div class="container">
  <p>
    <font color="red">${errores}</font>
  </p>

  <mvc:form method="post" action="login" modelAttribute="usuarioEdit">
      <div class="form-group mb-3">
        <mvc:label path="nickname">Nickname: </mvc:label>
        <mvc:input path="nickname" class="form-control"/>
        <mvc:errors path="nickname" cssClass="text-warning mt-1"/>
      </div>
      <div class="form-group mb-3">
        <mvc:label path="passw">Password:</mvc:label>
        <mvc:input type="password"  path="passw" class="form-control"/>
        <mvc:errors path="passw" cssClass="text-warning mt-1"/>
      </div>
    
    <br><input type="submit" value="Login" class="btn btn-success">
  </mvc:form>
  </div>

	
	
<%@ include file="../jspf/footer.jspf" %>