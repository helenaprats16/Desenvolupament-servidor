DROP TABLE IF EXISTS modulos;
CREATE TABLE modulos(
    id IDENTITY ,
    nombre VARCHAR(50) NOT NULL,
    num_horas INT,
    abreviatura VARCHAR(5),
    CONSTRAINT pk_modulos PRIMARY KEY(id)
);

--Rellenar la tabla modulos con valores iniciales
--La clave primaria es autoincrementativa
insert into modulos(nombre,num_horas,abreviatura) values('Programacion',8,'PRO');

insert into modulos(nombre,num_horas,abreviatura) values('Desarrollo Web en Entorno Servidor',8,'DWES');

insert into modulos(nombre,num_horas,abreviatura) values('Entornos de desarrollo',3,'EDD');


