
-- Para saber que usuario utilizar para logearnos que tenga permisos de arbitro
-- podemos ejecutar la siguiente Query:


SELECT u.nickname, r.nombre, u.password
FROM usuarios u, roles r, usuarios_roles usuarios_roles
WHERE usuarios_roles.idrol = r.id
AND usuarios_roles.idusuario = u.id;


-- Esto nos ha servido para darnos cuenta que con Spring Security al crear un
-- usuario no guardamos la contraseña en texto plano para que cualquiera lo
-- pueda copiar, lo ciframos como se muestra en la siguiente diapositiva.



INSERT INTO usuarios_roles (idUsuario, idRol) VALUES (5,5);