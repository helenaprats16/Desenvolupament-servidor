-- Crear la tabla alumnos
DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
dni VARCHAR(9), 
nombre VARCHAR(50) NOT NULL,
edad INT ,
ciclo VARCHAR(5),
curso INT ,
erasmus BOOLEAN DEFAULT FALSE,
lenguaje_Favorito VARCHAR(20),
genero VARCHAR(1),
horario VARCHAR(1),
pais VARCHAR(5),
hobbies VARCHAR(100),
CONSTRAINT pk_alumnos PRIMARY KEY(dni));

--Rellenar la tabla alumnos con algunos valores iniciales
insert into alumnos(dni,nombre,edad,ciclo,curso,erasmus,lenguaje_Favorito,genero,horario,pais,hobbies) 
    values('11111111A','Jose Garcia',21,'DAM',1,FALSE,'Java','H','M','ES',' ');
insert into alumnos(dni,nombre,edad,ciclo,curso,erasmus,lenguaje_Favorito,genero,horario,pais,hobbies) 
    values('22222222B','Maria Gonzalez',32,'DAW',2,TRUE,'Java','M','T','ES',' ');
insert into alumnos(dni,nombre,edad,ciclo,curso,erasmus,lenguaje_Favorito,genero,horario,pais,hobbies) 
    values('33333333C','Pedro Martínez',43,'DAW',2,FALSE,'Java','H','M','ES',' ');
