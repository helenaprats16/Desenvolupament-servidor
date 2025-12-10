-- Copyright Jose Ramón Cebolla - 2025

-- -----------------------------------------------------
-- Confirmar la inserción en la tabla
COMMIT;-- Table ciudadesINSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','sev',9,2,1,59);INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','vad',1,0,1,61);INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','vad',1,0,1,61);
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS ciudades (
  id BIGINT NOT NULL ,
  nombre VARCHAR(30) NOT NULL ,
  habitantes BIGINT NULL DEFAULT NULL ,
  PRIMARY KEY (id) );
 
-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table equipos
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS equipos (
  id VARCHAR(3) NOT NULL ,
  nombreCorto VARCHAR(20) NULL DEFAULT NULL ,
  nombreLargo VARCHAR(40) NULL DEFAULT NULL ,
  ciudad BIGINT NULL DEFAULT NULL ,
  entrenador VARCHAR(30) NULL DEFAULT NULL ,
  estadio VARCHAR(30) NULL DEFAULT NULL ,
  marca VARCHAR(30) NULL DEFAULT NULL ,
  patrocinador VARCHAR(30) NULL DEFAULT NULL ,
  presupuesto BIGINT NULL DEFAULT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT equipos_ciudad
    FOREIGN KEY (ciudad )
    REFERENCES ciudades (id ));

-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table jugadores
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS jugadores (
  idEquipo VARCHAR(3) NOT NULL DEFAULT '' ,
  dorsal BIGINT NOT NULL DEFAULT '0' ,
  nombre VARCHAR(30) NOT NULL ,
  posicion VARCHAR(10) NULL DEFAULT NULL ,
  sueldo BIGINT NULL DEFAULT NULL ,
  PRIMARY KEY (idEquipo, dorsal) ,
  CONSTRAINT jugadores_Equipo
    FOREIGN KEY (idEquipo )
    REFERENCES equipos (id ));

-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table porteros
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS porteros (
  idEquipo VARCHAR(3) NOT NULL ,
  dorsal BIGINT NOT NULL ,
  partidos INTEGER NULL DEFAULT NULL ,
  goles INTEGER NULL DEFAULT NULL ,
  PRIMARY KEY (idEquipo, dorsal) ,
  CONSTRAINT porteros_jugador
    FOREIGN KEY (idEquipo , dorsal )
    REFERENCES jugadores (idEquipo , dorsal ));

-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table goleadores
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS goleadores (
  idEquipo VARCHAR(3) NOT NULL ,
  dorsal BIGINT NOT NULL ,
  partidos INTEGER NULL DEFAULT NULL ,
  goles INTEGER NULL DEFAULT NULL ,
  penaltis INTEGER NULL DEFAULT NULL ,
  pp INTEGER NULL DEFAULT NULL ,
  minutosGol INTEGER NULL DEFAULT NULL ,
  gTtitular INTEGER NULL DEFAULT NULL ,
  gSuplente INTEGER NULL DEFAULT NULL ,
  gPuntos INTEGER NULL DEFAULT NULL ,
  gVictoria INTEGER NULL DEFAULT NULL ,
  gRemontada INTEGER NULL DEFAULT NULL ,
  porcentaje INTEGER NULL DEFAULT NULL ,
  PRIMARY KEY (idEquipo, dorsal) ,
  CONSTRAINT goleadores_jugador
    FOREIGN KEY (idEquipo , dorsal )
    REFERENCES jugadores (idEquipo , dorsal ));

-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table jornadas
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS jornadas (
  num BIGINT NOT NULL ,
  fecha DATE NULL DEFAULT NULL ,
  PRIMARY KEY (num) );

-- Confirmar la creación de la tabla
COMMIT;
-- -----------------------------------------------------
-- Table partidos
-- -----------------------------------------------------
BEGIN;
CREATE TABLE IF NOT EXISTS partidos (
  idEquipoL VARCHAR(3) NOT NULL,
  idEquipoV VARCHAR(3) NOT NULL,
  jornada BIGINT NOT NULL,
  golesL INTEGER DEFAULT NULL,
  golesV INTEGER DEFAULT NULL,
  posesionL INTEGER DEFAULT NULL,
  PRIMARY KEY (idEquipoL, idEquipoV),
  CONSTRAINT partidos_idEquipoL
    FOREIGN KEY (idEquipoL)
    REFERENCES equipos (id),
  CONSTRAINT partidos_idEquipoV
    FOREIGN KEY (idEquipoV)
    REFERENCES equipos (id),
  CONSTRAINT partidos_jornada
    FOREIGN KEY (jornada)
    REFERENCES jornadas (num)
); 
-- Confirmar la creación de la tabla
COMMIT;
-- ---------------------------------------------------
-- DATOS
-- ---------------------------------------------------
BEGIN;

INSERT INTO ciudades (id,nombre,habitantes) VALUES (2,'Alacant',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (3,'Albacete',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (4,'Almeria',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (5,'Astúries',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (6,'Àvila',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (7,'Badajoz',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (8,'Barcelona',1615000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (9,'Bilbao',353000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (10,'Biscaia',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (11,'Burgos',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (12,'Càceres',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (13,'Cadis',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (14,'Cantàbria',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (15,'Castelló',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (16,'Ceuta',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (17,'ciudad Real',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (18,'Còrdova',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (19,'Cornellà de Llobregat',85000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (20,'Cuenca',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (21,'Getafe',169000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (22,'Girona',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (23,'Granada',234000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (24,'Guadalajara',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (25,'Guipúscoa',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (26,'Huelva',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (27,'Illes Balears',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (28,'Jaén',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (29,'La Corunya',246000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (30,'La Rioja',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (31,'Las Palmas',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (32,'Lleida',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (33,'Lleó',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (34,'Lugo',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (35,'Madrid',3293000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (36,'Màlaga',568000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (37,'Melilla',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (38,'Múrcia',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (39,'Navarra',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (40,'Orense',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (41,'Osca',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (42,'Palència',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (43,'Palma de Mallorca',400000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (44,'Pamplona',197000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (45,'Pontevedra',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (46,'Salamanca',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (47,'Sant Sebastià',186000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (48,'Santa Cruz de Tenerife',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (49,'Saragossa',680000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (50,'Segòvia',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (51,'Sevilla',702000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (52,'Sória',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (53,'Tarragona',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (54,'Terol',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (55,'Toledo',NULL);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (56,'València',798000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (57,'Valladolid',313000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (58,'Vigo',297000);
INSERT INTO ciudades (id,nombre,habitantes) VALUES (59,'Zamora',NULL);
-- Confirmar la inserción en la tabla
COMMIT;

/*
-- Query: select * from equipos
*/
BEGIN;
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('ath','Athletic','Athletic Club',9,'Marcelo Bielsa','estadioo de San Mamés','Umbro','Petronor',58);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('atm','At. Madrid','Club Atlético de Madrid',35,'Diego Simeone','Vicente Calderón','Nike','Azerbaiyán',120);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('bar','Barça','Futbol Club Barcelona',8,'Tito Vilanova','Camp Nou','Qatar Foundation','UNICEF',470);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('bet','Real Betis','Real Betis Balompié',51,'Pepe Mel','Benito Villamarín','Cirsa','Andalucía',40);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('cel','Celta de Vigo','Real Club Celta de Vigo',58,'Abel Resino','estadioo de Balaídos','Adidas','Citroën',33);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('dep','Deportivo','Real Club Deportivo de La Coruña',29,'Fernando V àquez Pena','Riazor','Lotto Sport Italia','Hijos de Rivera',40);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('esp','Espanyol','Reial Club Deportiu Espanyol',8,'Javier Aguirre','Cornellà-El Prat','Puma','Cancún',46);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('gda','Granada','Granada Club de Fútbol',23,'Lucas Alcaraz','Los Cármenes','Caja Granada','',30);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('get','Getafe','Getafe Club de Fútbol',21,'Luis Garc a Plaza','Coliseum Alfonso Pérez','Joma','',42);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('lev','Llevant','LLevant Unió Esportiva',56,'Juan Ignacio Martínez Jim éez','ciudad de València','Kelme','Comunitat Valenciana',22);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('mal','Mallorca','Real Club Deportivo Mallorca',43,'Gregorio Manzano','Iberostar estadioo','Riviera Maya','Sol Melià',31);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('mga','Málaga','Málaga Club de Fútbol',36,'Manuel Pellegrini','estadioo La Rosaleda','Unesco','Porsche',80);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('osa','Osasuna','Club Atlético Osasuna',44,'Jos  Luis Mendilíbar','Reyno de Navarra','','',30);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('ray','Rayo Vallecano','Rayo Vallecano de Madrid',35,'Paco Jiménez','Campo de Fútbol de Vallecas','Erre ','',8);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('rma','Real Madrid','Real Madrid Club de Fútbol',35,'José Mourinho','Santiago Bernabéu','Adidas','',517);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('rso','Real Sociedad','Real Sociedad de Fútbol',47,'Philippe Montanier','Anoeta','Canal+','Kutxa',43);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('sev','Sevilla','Sevilla Fútbol Club',51,'Unai Emery','Ramó n Sánchez Pizjuán','','',60);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('vad','Valladolid','Real Valladolid Club de Fútbol',57,'Miroslav ?uki?','estadioo Jos  Zorrilla','Kappa','El Norte de Castilla',23);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('val','València','València Club de Futbol',56,'Ernesto Valverde','Mestalla','','',103);
INSERT INTO equipos (id,nombreCorto,nombreLargo,ciudad,entrenador,estadio,marca,patrocinador,presupuesto) VALUES ('zar','Zaragoza','Real Zaragoza',49,'Manolo Jiménez Jiménez','La Romareda','Telefó nica','Canal+',0);

-- Confirmar la inserción en la tabla
COMMIT;

/*
-- Query: select * from jugadores
*/
BEGIN;
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',1,'Gorka Iraizoz','portero',2850000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',2,'Toquero','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',3,'Aurtenetxe','defensa',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',4,'Laporte','defensa',1690000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',5,'Amorebieta','defensa',1700000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',6,'Mikel San José','medio',4310000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',8,'Iturraspe','defensa',1730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',9,'Fernando Llorente','medio',3210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',10,'Óscar de Marcos','medio',4710000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',11,'Ibai Gómez','medio',4010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',13,'Raúl Fernández','portero',470000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',14,'Markel Susaeta','medio',4710000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',15,'Iraola','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',16,'Isma López','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',17,'Iñigo Pérez','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',18,'Carlos Gurpegui','medio',2810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',19,'Iker Muniain','medio',3510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',20,'Aritz Aduriz','delantero',6310000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',21,'Ander Herrera','medio',3010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',22,'Xabi Castillo','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',23,'Ekiza','defensa',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',26,'Igor Martínez','defensa',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',27,'Ruiz de Galarreta','defensa',1920000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',28,'Ramalho','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',37,'Aketxe','defensa',2020000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ath',39,'Morán','defensa',2040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',1,'Sergio Asenjo','portero',440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',2,'Diego Godín','medio',3960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',3,'Filipe Luís','medio',3660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',4,'Mario Suárez','medio',3360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',5,'Tiago','medio',2860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',6,'Koke','medio',4060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',7,'Adrián López','medio',3960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',8,'Raúl García','medio',4260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',9,'Radamel Falcao','delantero',9260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',10,'Arda Turan','medio',4160000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',11,'Cristián Rodríguez','medio',3660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',12,'Pulido','defensa',2360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',13,'Thibaut Courtois','portero',3580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',14,'Gabi','defensa',2380000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',15,'Cisma','defensa',2390000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',18,'Cata Díaz','defensa',2420000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',19,'Diego Costa','delantero',4860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',20,'Juanfran','medio',3860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',22,'Insúa','defensa',2460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',23,'Miranda','medio',4060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',26,'Manquillo','defensa',2500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',28,'Saúl','defensa',2520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',30,'Óliver Torres','defensa',2540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',31,'Borja Galán','defensa',2550000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',34,'Iván Calero','defensa',2580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',35,'Galass','defensa',2590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',41,'Ndoye','defensa',2650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',42,'Kader','defensa',2660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('atm',43,'Thomas','defensa',2670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',1,'Víctor Valdés','portero',3040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',2,'Dani Alves','defensa',2740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',3,'Gerard Piqué','medio',3360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',4,'Cesc Fàbregas','delantero',5360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',5,'Carles Puyol','medio',1960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',6,'Xavi','medio',4060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',7,'David Villa','delantero',4760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',8,'Andrés Iniesta','medio',3660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',9,'Alexis Sánchez','delantero',4560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',10,'Lionel Messi','delantero',12860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',11,'Thiago Alcântara','medio',3260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',12,'Jonathan dos Santos','defensa',2840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',13,'José Manuel Pinto','portero',1000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',14,'Mascherano','defensa',2860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',15,'Bartra','defensa',2870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',16,'Sergio Busquets','medio',3560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',17,'Pedro Rodríguez','medio',3960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',18,'Jordi Alba','medio',3460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',19,'Montoya','defensa',2910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',21,'Adriano','medio',3760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',22,'Abidal','defensa',2940000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',25,'Alex Song','medio',2560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',27,'Deulofeu','defensa',2990000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',28,'Sergi Roberto','defensa',3000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',31,'Oier','defensa',3030000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',32,'Masip','defensa',3040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bar',37,'Cristian Tello','delantero',3860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',1,'Casto Barriga','portero',620000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',2,'Chica','defensa',1440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',3,'Mario Álvarez','medio',2360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',4,'Amaya','defensa',1460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',6,'Álex Martínez','defensa',1480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',7,'Ángel López','medio',1360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',8,'Rubén Pérez','medio',2660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',9,'Vadillo','defensa',1510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',10,'Beñat','medio',4160000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',11,'Alejandro Pozuelo','medio',1560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',12,'Paulão','medio',2660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',13,'Adrián San mediouel','portero',2810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',14,'Salva Sevilla','medio',2060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',15,'Joel Campbell','medio',3160000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',16,'Perquis','defensa',1580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',17,'Juan Carlos','defensa',1590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',18,'Molins','defensa',1600000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',19,'Jorge Molina','delantero',5460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',20,'Nosa Igiebor','medio',2060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',21,'Cañas','defensa',1630000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',22,'Dorlan Pabon','medio',2860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',23,'Nacho','defensa',1650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',24,'Rubén Castro','delantero',6760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',25,'Fabricio Agosto','portero',410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',27,'Fausto','defensa',1690000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',29,'Nono','defensa',1710000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',30,'Eder Vilarchao','defensa',1720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',32,'Manu Palancar','defensa',1740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',34,'Carlos García','defensa',1760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',35,'Borja','defensa',1770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',37,'Eneko Eizmendi','defensa',1790000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('bet',39,'Sergio Rodríguez','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',1,'Sergio Álvarez','portero',340000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',2,'Hugo Mallo','defensa',1640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',3,'Roberto Lago','defensa',1650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',4,'Borja Oubiña','medio',3760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',5,'Túñez','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',6,'Jonathan Vila','defensa',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',8,'Álex López','medio',3660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',9,'Mario Bermejo','medio',4460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',10,'Iago Aspas','delantero',5560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',11,'Pranjic','defensa',1730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',12,'Gustavo Cabral','medio',2860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',13,'Javi Varas','portero',3060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',15,'Demidov','defensa',1770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',16,'Bellvis','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',18,'Park Chu-Young','medio',2860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',19,'Orellana','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',20,'Toni','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',21,'Samuel','defensa',1830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',22,'Enrique de Lucas','medio',3060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',23,'Michael Krohn-Dehli','medio',3560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',24,'Augusto Fernández','medio',4760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',25,'Natxo Insa','medio',1960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',26,'Blanco','defensa',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',28,'Jonny','defensa',1900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',29,'Madinda','defensa',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('cel',31,'Santi Mina','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',1,'Aranzubia','portero',2730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',2,'Manuel Pablo','defensa',1830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',3,'Evaldo','defensa',1840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',4,'Alex Bergantiños','medio',3060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',5,'Zé Castro','defensa',1860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',6,'Aythami','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',8,'André Santos','defensa',1890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',9,'Pizzi','delantero',4960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',10,'Juan Domínguez','medio',2460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',11,'Riki','delantero',6060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',12,'Paulo Assunção','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',13,'Germán Lux','portero',530000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',14,'Abel Aguilar','medio',3260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',15,'Laure','defensa',1960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',16,'Bruno Gama','medio',4660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',17,'Ayoze','defensa',1980000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',18,'Javier Camuñas','medio',2660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',19,'Sílvio','medio',2060000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',20,'Jesús Vázquez','defensa',2010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',21,'Juan Carlos Valerón','medio',3360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',22,'Diogo Salomão','medio',1560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',23,'Kaká','defensa',2040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',24,'Carlos Marchena','medio',2760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',25,'Nélson Oliveira','medio',3460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('dep',28,'Insúa','defensa',2090000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',1,'Cristian Álvarez','portero',1580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',2,'Mattioni','defensa',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',3,'Raúl Rodríguez','defensa',1890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',4,'Víctor Sánchez','medio',3520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',5,'Tejera','medio',1520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',6,'Forlín','defensa',1920000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',7,'Baena','medio',2220000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',8,'Christian Stuani','delantero',4520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',9,'Sergio García','delantero',4120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',10,'Joan Verdú','delantero',5420000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',12,'Samuele Longo','medio',2620000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',13,'Kiko Casilla','portero',1990000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',15,'Héctor Moreno','medio',3820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',16,'Javi López','medio',3320000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',17,'Petrov','defensa',2030000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',18,'Capdevila','defensa',2040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',19,'Diego Colotto','medio',3120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',20,'Simão Sabrosa','medio',3120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',22,'Wakaso Mubarak','medio',3220000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',23,'Cristian Gómez','defensa',2090000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',24,'Cristian Alfonso','defensa',2100000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',26,'Edgar','defensa',2120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',27,'Víctor Álvarez','defensa',2130000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',35,'Germán','defensa',2210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('esp',46,'De Amo','defensa',2320000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',1,'Toño','portero',2210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',2,'Nyom','defensa',1650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',3,'Brahimi','defensa',1660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',4,'Fran Rico','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',5,'Diego Mainz','medio',2780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',6,'Guilherme Siqueira','medio',4380000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',7,'Carlos Aranda','medio',2980000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',8,'Iñigo López','defensa',1710000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',9,'Youssef El-Arabi','medio',4180000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',10,'Nolito','medio',1980000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',11,'Dani Benítez','medio',1280000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',12,'Diego Buonanotte','medio',2480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',13,'Roberto Fernández','portero',1120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',14,'Mikel Rico','medio',4080000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',15,'Diakhaté','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',16,'Brayan Angulo','medio',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',17,'Yebda','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',18,'Lucena','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',20,'Borja Gómez','defensa',1830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',21,'Juanma Ortiz','defensa',1840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',22,'Gabriel Torje','medio',4080000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',24,'Iriney','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',25,'Odion Ighalo','medio',3480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',28,'Recio','medio',2280000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('gda',37,'Antonio Puertas','defensa',2000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',1,'mediouel Ángel Moyà','portero',2750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',2,'Alexis Ruano','medio',3440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',3,'Mané','medio',1540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',4,'mediouel Torres','defensa',1790000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',5,'Lacen','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',6,'Lopo','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',7,'Álvaro Vázquez','medio',4140000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',8,'Sarabia','defensa',1830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',9,'Paco Alcácer','medio',2740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',10,'Gavilán','defensa',1850000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',11,'Adrián Colunga','medio',3540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',12,'Fede Fernández','medio',1640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',13,'Jordi idna','portero',810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',14,'Pedro León','medio',3440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',15,'Rafa','defensa',1900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',16,'Sergio Escudero','medio',1240000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',17,'Diego Castro','delantero',4740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',18,'Ángel Lafita','medio',3040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',19,'Abdelaziz Barrada','medio',4040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',20,'Juan Valera','medio',3340000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',21,'Míchel','defensa',1960000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',22,'Juan Rodríguez','medio',1540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',23,'Borja Fernández','medio',2040000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',24,'Hugo Fraile','defensa',1990000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',25,'Xavi Torres','defensa',2000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',32,'Postigo','defensa',2070000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',33,'Alberto Lopo','medio',2440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('get',35,'Nadal','defensa',2100000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',1,'Gustavo Munúa','portero',2700000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',4,'David Navarro','medio',3610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',5,'Héctor Rodas','defensa',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',6,'Vyntra','defensa',1690000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',7,'José Barkero','medio',4010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',8,'Nabil El Zhar','medio',3410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',9,'Robert Acquafresca','medio',1610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',10,'Vicente Iborra','medio',4010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',11,'Rubén García','medio',3810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',12,'Juanfran','defensa',1750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',13,'Keylor Navas','portero',750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',14,'Dudka','defensa',1770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',15,'Karabelas','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',16,'Pedro Ríos','medio',2610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',17,'Valdo','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',18,'Ballesteros','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',19,'Pedro López','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',20,'Juanlu','medio',2710000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',21,'Míchel Herrero','medio',4410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',22,'Christian Lell','medio',2610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',23,'Pape Diop','defensa',1860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',24,'Simão','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',26,'Garabato','defensa',1890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',27,'Roger Martí','medio',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('lev',28,'Iván López','defensa',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',1,'Juan Calatayud','portero',380000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',2,'Hutton','defensa',1500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',3,'João Victor','defensa',1510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',4,'Anderson Conceiçao','defensa',1520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',5,'Pina','defensa',1530000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',6,'Antonio López','defensa',1540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',7,'Michael Pereira','medio',2650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',8,'Emilio Nsue','medio',3450000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',9,'Giovani dos Santos','medio',3750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',10,'Tomer Hemed','delantero',5650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',11,'Javi Márquez','medio',2750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',12,'Tissone','defensa',1600000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',13,'Dudu Aouate','portero',2740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',14,'Geromel','medio',3050000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',16,'Nunes','defensa',1640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',17,'Pedro Bigas','medio',2450000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',18,'Víctor','delantero',4750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',19,'Martí','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',20,'Luna','defensa',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',21,'Alejandro Alfaro','medio',3650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',22,'Javier Arizmendi','medio',2550000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',23,'Kevin Martínez','medio',1450000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',24,'Fontás','defensa',1720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',25,'Rubén Miño','portero',230000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',26,'Yeray','defensa',1740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',27,'Ximo Navarro','defensa',1750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',30,'Álvaro','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mal',35,'Marc Fernández','defensa',1830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',1,'Carlos Kameni','portero',500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',2,'Jesús Gámez','defensa',1720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',3,'Weligton','medio',3770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',4,'Lugano','defensa',1740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',5,'Martín Demichelis','medio',4070000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',6,'Ignacio Camacho','medio',3770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',7,'Joaquín','medio',4270000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',8,'Toulalan','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',9,'Javier Saviola','delantero',4170000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',10,'Júlio Baptista','medio',2070000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',11,'Seba Fernández','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',12,'Lucas Piazon','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',13,'Willy Caballero','portero',3290000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',14,'Pedro Morales','medio',1270000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',16,'Iturra','defensa',1860000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',17,'Duda','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',18,'Eliseu','medio',3070000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',19,'Portillo','defensa',1890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',21,'Sergio Sánchez','defensa',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',22,'Francisco Portillo (Isco)','medio',4970000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',23,'Onyewu','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',24,'Roque Santa Cruz','delantero',4870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',25,'Antunes','medio',1570000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',30,'Jurado','defensa',2000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',32,'Pol','defensa',2020000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',45,'Fabrice Olinga','medio',670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('mga',46,'Álex Portillo','defensa',2160000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',1,'Riesgo','defensa',1490000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',2,'Marc Bertrán','defensa',1500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',3,'Rubén','defensa',1510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',4,'mediouel Flaño','defensa',1520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',5,'Lolo','medio',2580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',6,'mediouel de las Cuevas','medio',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',7,'Kike Sola','delantero',4880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',8,'Masueldod Shojaei','medio',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',9,'Emiliano Armenteros','medio',3480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',10,'Puñal','defensa',1580000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',11,'Annan','defensa',1590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',12,'Joseba Llorente','medio',2480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',13,'Andrés Fernández','portero',3260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',14,'Alejandro Arribas','medio',3780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',15,'Oier','defensa',1630000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',16,'Álvaro Cejudo','medio',3380000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',17,'Nino','medio',3180000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',18,'Manu Onwu','medio',880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',19,'Nano','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',20,'Francisco Silva','medio',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',21,'Roberto Torres','defensa',1690000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',22,'David Timor','medio',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',23,'Raoul Loé','medio',2780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',24,'Damiá','defensa',1720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',25,'Ricardo','defensa',1730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',26,'Imanol','defensa',1740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('osa',27,'Sisi','defensa',1750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',1,'Rubén','portero',2490000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',2,'Tito Román','medio',3630000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',3,'Casado','defensa',1790000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',4,'Labaka','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',5,'Gálvez','defensa',1810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',6,'Rodri','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',8,'Adrián','defensa',1840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',9,'José Carlos','medio',3530000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',10,'Piti Medina','delantero',6430000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',11,'Chori Domínguez','medio',4030000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',13,'Dani Giménez','portero',390000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',14,'Arbilla','defensa',1900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',15,'Raúl Tamudo','medio',830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',16,'Jordi Amat','medio',3030000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',17,'Trashorras','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',18,'Javi Fuego','defensa',1940000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',19,'Lass Bangoura','medio',3830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',20,'Franco Vazquez','medio',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',22,'Jordi','defensa',1980000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',23,'Andrija Delibasic','medio',4430000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',25,'David Cobeño','portero',900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',29,'Léo Baptistão','delantero',4430000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',31,'Roberto Trashorras','medio',3630000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',32,'Nono','defensa',2080000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',33,'Alberto Perea','defensa',2090000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',38,'Nacho Martínez','medio',1130000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('ray',39,'Isi','defensa',2150000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',1,'Iker Casillas','portero',2140000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',2,'Varane','defensa',2470000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',3,'Pepe','medio',3410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',4,'Sergio Ramos','medio',3810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',5,'Fábio Coentrão','medio',2210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',6,'Sami Khedira','medio',3210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',7,'Cristiano Ronaldo','delantero',10610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',8,'Kaká','medio',2810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',9,'Karim Benzema','delantero',5510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',10,'Mesut Özil','delantero',5210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',11,'Ricardo Carvalho','defensa',2560000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',12,'Marcelo','defensa',2570000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',13,'Antonio Adán','portero',680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',14,'Xabi Alonso','defensa',2590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',15,'Michael Essien','medio',2510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',17,'Arbeloa','defensa',2620000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',18,'Raúl Albiol','medio',2210000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',19,'Luka Modric','medio',4110000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',20,'Gonzalo Higuaín','delantero',5810000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',21,'José María Callejón','medio',3410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',22,'Ángel di María','medio',4610000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',25,'Diego López','portero',2430000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',27,'Nacho','defensa',2720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',29,'Álvaro Morata','medio',2010000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',33,'Fabinho','defensa',2780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',34,'José Rodríguez','defensa',2790000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',35,'Jesús','defensa',2800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',37,'Diego Javier Llorente','defensa',2820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rma',38,'Casemiro','defensa',2830000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',1,'Claudio Bravo','portero',2730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',2,'Carlos Martínez','medio',3090000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',3,'Mikel González','medio',3890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',4,'Gorka Elustondo','medio',1290000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',5,'Bergara','defensa',2450000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',6,'Iñigo Martínez','medio',4190000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',7,'Antoine Griezmann','delantero',4990000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',8,'Illarramendi','defensa',2480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',9,'Imanol Agirretxe','delantero',5990000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',10,'Xabi Prieto','delantero',4890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',11,'Carlos Vela','delantero',6390000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',13,'Eñaut Zubikarai','portero',900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',14,'Rubén Pardo','defensa',2540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',15,'Ansotegi','defensa',2550000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',17,'David Zurutuza','medio',2590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',18,'Chori Castro','medio',4290000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',19,'Cadamuro','defensa',2590000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',20,'José Ángel','defensa',2600000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',21,'Diego Ifrán','medio',3190000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',22,'Estrada','defensa',2620000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',23,'Javi Ros','defensa',2630000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',24,'Alberto de la Bella','medio',4190000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('rso',26,'Royo','defensa',2660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',1,'Palop','portero',1550000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',2,'Federico Fazio','medio',3440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',3,'F. Navarro','defensa',1730000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',5,'Cala','defensa',1750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',6,'Campaña','defensa',1760000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',7,'Jesús Navas','defensa',1770000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',8,'Gary Medel','medio',4340000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',9,'Álvaro Negredo','delantero',7540000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',10,'Perotti','defensa',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',11,'Ivan Rakitic','delantero',4740000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',12,'Maduro','defensa',1820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',13,'Beto','portero',1270000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',14,'Manu del Moral','medio',2640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',15,'Piotr Trochowski','medio',1240000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',17,'Javi Hervás','defensa',1870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',18,'Miroslav Stevanovic','defensa',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',19,'José Antonio Reyes','medio',3640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',20,'Babá','defensa',1900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',21,'Cicinho','medio',2840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',22,'Geoffrey Kondogbia','medio',3240000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',23,'Coke','medio',2640000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',24,'Alberto Botía','medio',2140000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',25,'Julián','defensa',1950000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',28,'Álex Rubio','defensa',1980000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',30,'Bryan Rabello','defensa',2000000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('sev',35,'Alberto Moreno','defensa',2050000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',1,'Jaime','portero',1190000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',2,'Antonio Rukavina','medio',3920000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',4,'Marc Valiente','defensa',1650000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',5,'Sereno','defensa',1660000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',6,'Jesús Rueda','defensa',1670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',7,'Neira','defensa',1680000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',8,'Javier Baraja','medio',1520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',9,'Javi Guerra','delantero',4620000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',10,'Óscar González','delantero',5920000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',11,'Daniel Larsson','defensa',1720000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',13,'Dani Hernández','portero',2250000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',14,'Omar','defensa',1750000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',15,'Alberto Bueno','medio',4020000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',16,'Lluís Sastre','medio',3120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',17,'Peña','defensa',1780000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',18,'Álvaro Rubio','defensa',1790000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',20,'Patrick Ebert','medio',3520000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',22,'Víctor Pérez','medio',3020000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',23,'Rama','defensa',1840000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',24,'Balenziaga','defensa',1850000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',25,'Manucho','delantero',4120000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',27,'Lolo','defensa',1880000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',28,'Rubén Peña','defensa',1890000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',30,'Jorge Pesca','defensa',1910000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('vad',32,'Iván Casado','defensa',1930000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',1,'Diego Alves','portero',2310000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',3,'Aly Cissokho','medio',2900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',4,'Rami','defensa',2260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',6,'Albelda','defensa',2280000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',7,'Jonas','delantero',6100000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',8,'Sofiane Feghouli','medio',3400000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',9,'Roberto Soldado','delantero',7700000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',10,'Éver Banega','medio',3500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',11,'Pablo Piatti','medio',1900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',12,'João Pereira','defensa',2340000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',13,'Vicente Guaita','portero',1290000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',14,'Barragán','defensa',2360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',15,'Jonathan Viera','medio',2200000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',16,'Nelson Valdez','medio',4300000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',17,'Andrés Guardado','medio',3500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',18,'Víctor Ruiz','defensa',2400000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',20,'Ricardo Costa','medio',3400000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',21,'Dani Parejo','medio',2900000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',22,'Mathieu','defensa',2440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',23,'Sergio Canales','medio',1800000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',24,'Tino Costa','medio',3500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',26,'Felipe','defensa',2480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',28,'Juan Bernat','defensa',2500000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('val',29,'Alex Quintanilla','defensa',2510000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',1,'Roberto','portero',2820000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',2,'Movilla','defensa',1230000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',3,'Paredes','defensa',1240000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',4,'Álvaro González','medio',3370000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',5,'Lanzaro','defensa',1260000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',6,'Rodri','medio',1570000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',7,'Bienvenu','defensa',1280000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',8,'Edu Oriol','defensa',1290000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',9,'Hélder Postiga','delantero',5970000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',10,'Apoño','delantero',4870000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',11,'Paco Montañés','medio',4670000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',12,'Romaric','defensa',1330000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',13,'Leo Franco','portero',410000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',14,'José Fernández','defensa',1350000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',15,'José Mari','defensa',1360000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',16,'Loovens','defensa',1370000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',17,'Babovic','defensa',1380000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',18,'Wilchez','defensa',1390000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',19,'Cristian Sapunaru','medio',3270000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',20,'Franco Zuculini','medio',1970000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',21,'Abraham','defensa',1420000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',22,'Pintér','defensa',1430000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',23,'Javi Álamo','defensa',1440000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',24,'Obradovic','defensa',1450000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',25,'Carmona','defensa',1460000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',26,'Ortí','defensa',1470000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',27,'Héctor','defensa',1480000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',30,'Pablo Alcolea','portero',350000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',31,'Rubén Rochina','medio',1570000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',32,'Víctor Rodríguez','medio',3370000);
INSERT INTO jugadores (idEquipo,dorsal,nombre,posicion,sueldo) VALUES ('zar',40,'Kilian Falcón','defensa',1610000);

-- Confirmar la inserción en la tabla
COMMIT;

/*
-- Query: select * from goleadores
*/
BEGIN;
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',19,30,4,0,0,608,4,0,1,2,2,10);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',14,25,3,0,0,723,3,0,1,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',11,21,6,0,0,209,6,0,1,0,0,14);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',7,30,3,0,0,463,2,1,0,1,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',21,23,5,0,0,287,5,0,0,1,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',24,17,1,0,0,1,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',15,30,4,1,0,336,4,0,0,0,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',24,33,3,0,0,979,3,0,0,1,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',33,20,1,0,0,2,1,0,0,1,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',21,25,5,0,0,240,5,0,1,1,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',14,32,2,0,0,1,2,0,0,1,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',11,11,1,0,0,426,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',4,27,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',8,31,2,0,0,1,2,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',25,19,1,0,0,1,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',2,26,3,0,0,748,3,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',9,27,7,0,0,229,7,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',16,30,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',4,30,1,0,0,3,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',29,12,2,0,0,200,1,1,0,1,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',9,33,20,3,0,138,19,1,3,2,0,38);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',7,29,5,0,0,275,3,2,0,0,0,10);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',3,22,2,0,0,741,2,0,0,1,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',21,26,1,0,0,2,1,0,1,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',17,30,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',8,28,2,0,0,950,2,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',23,30,6,0,0,205,2,4,0,0,0,14);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',22,30,6,0,0,318,5,1,0,1,1,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',18,24,2,0,0,769,1,1,0,1,1,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',7,9,1,0,0,557,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',7,31,8,0,0,324,8,0,0,0,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',2,35,1,0,1,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',25,11,1,0,0,943,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',10,29,9,7,0,254,9,0,0,0,0,26);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',10,30,4,0,0,525,4,0,1,1,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',20,33,14,1,0,175,14,0,2,5,0,35);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',24,34,6,0,0,463,6,0,1,1,0,18);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',7,18,1,0,0,934,1,0,0,1,1,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',10,31,4,0,0,647,4,0,0,1,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',23,16,1,0,0,1,1,0,0,1,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',4,34,1,0,0,3,1,0,1,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',16,13,1,0,0,544,0,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',16,35,5,0,0,585,5,0,0,1,0,11);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',5,13,1,0,0,968,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',7,26,1,0,0,1,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',18,24,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',24,22,2,0,1,927,2,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',2,26,1,0,0,2,1,0,1,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',11,33,14,4,0,183,12,2,1,0,0,22);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',4,29,10,0,0,206,10,0,0,1,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',18,28,6,0,0,207,6,0,0,0,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',11,30,4,2,0,616,4,0,0,1,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',22,20,2,0,0,850,2,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',8,29,7,0,0,269,4,3,0,1,1,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',21,24,1,0,0,2,1,0,0,1,1,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',23,18,3,0,0,477,3,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',11,31,1,0,0,1,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',19,27,2,0,0,1,2,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',37,20,7,0,0,166,5,2,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',7,34,34,6,1,80,33,1,2,2,0,35);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',11,9,1,0,0,719,1,0,0,1,1,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',21,24,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',4,32,1,0,1,3,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',22,15,1,1,1,841,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',7,25,9,0,0,131,6,3,0,1,1,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',17,19,2,0,0,551,2,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',12,21,1,0,0,648,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',17,31,7,4,0,338,6,1,1,2,0,17);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',19,25,2,0,0,1,2,0,0,1,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',19,29,8,0,0,234,6,2,0,2,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',2,34,1,0,0,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',21,25,2,0,0,444,1,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',5,24,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',22,12,1,0,0,363,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',22,14,6,0,0,179,6,0,1,0,0,12);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',18,26,1,1,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',9,27,3,0,0,785,3,0,0,1,0,10);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',8,29,2,0,0,1,1,1,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',22,23,3,0,0,237,2,1,0,0,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',10,26,3,0,0,574,3,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',5,16,1,0,0,1,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',45,2,1,0,0,77,0,1,0,1,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',12,12,1,0,0,995,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',2,26,3,0,0,706,3,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',9,24,3,0,0,270,1,2,1,0,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',3,31,1,0,0,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',19,27,2,0,0,857,2,0,0,0,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',20,13,1,0,0,1,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',20,15,1,0,0,490,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',20,16,1,0,0,924,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',22,33,3,0,0,704,3,0,0,0,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',8,29,6,0,0,402,6,0,0,0,0,12);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',22,28,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',3,25,2,0,0,1,2,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',14,27,1,0,0,2,1,0,1,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',9,26,5,0,0,428,5,0,0,1,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',20,26,14,0,0,113,12,2,1,1,1,15);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',4,8,1,0,0,440,1,0,1,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',6,32,5,5,0,545,5,0,1,0,0,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',12,25,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',15,32,2,0,0,1,2,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',9,34,12,1,0,241,12,0,0,1,1,34);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',10,32,11,2,0,239,11,0,1,1,0,32);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',11,32,3,0,0,594,3,0,0,0,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',6,31,2,0,0,1,2,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',19,31,1,0,0,2,0,1,0,1,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',9,31,13,0,0,137,11,2,0,3,2,20);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',6,31,4,0,0,698,4,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',22,35,8,2,0,363,8,0,1,1,1,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',11,31,7,1,0,385,7,0,0,0,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',9,28,8,0,0,190,5,3,0,2,1,17);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',16,29,1,0,1,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',11,22,2,1,0,625,2,0,0,1,1,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',22,20,2,0,0,395,0,2,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',8,11,1,0,0,313,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',18,23,1,0,0,647,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',9,25,7,0,0,212,7,0,0,1,0,14);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',10,34,9,3,0,318,9,0,0,0,0,21);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',7,32,4,1,0,564,4,0,0,1,1,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',15,25,2,0,0,758,2,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',7,32,13,1,0,171,13,0,2,1,0,21);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',15,15,2,0,0,365,2,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',18,26,2,0,1,1,2,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',16,26,1,0,2,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',19,30,11,1,0,180,7,4,1,1,0,21);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',19,26,4,0,0,430,4,0,0,0,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',7,28,5,2,0,436,5,0,1,0,0,14);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',9,29,2,0,0,951,1,1,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',21,28,1,0,0,1,0,1,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',12,21,1,0,0,845,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',21,30,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',10,21,1,0,0,2,1,0,1,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',22,9,2,0,0,268,2,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',20,27,2,0,0,1,2,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',20,33,1,0,2,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',20,23,1,0,0,1,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',10,12,3,0,0,244,3,0,0,1,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',8,18,3,1,0,301,3,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',9,29,11,0,0,154,10,1,0,0,0,11);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',23,11,1,0,0,817,0,1,1,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',7,29,9,0,0,210,7,2,0,2,0,31);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',6,31,3,0,0,779,3,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',19,30,3,0,0,573,3,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',29,28,7,0,0,322,7,0,0,1,0,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',10,32,46,4,0,57,40,6,1,4,3,43);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',16,27,1,0,0,1,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',5,22,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',19,31,3,0,0,668,3,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',3,11,1,0,0,861,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',14,22,1,0,0,772,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',18,5,1,0,0,86,0,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',25,23,8,0,1,194,8,0,1,2,0,17);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',3,19,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',9,33,5,0,0,321,4,1,0,2,1,15);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',4,28,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',14,33,6,0,0,475,6,0,1,1,0,15);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',5,30,4,0,0,664,4,0,0,0,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',8,13,1,0,0,320,0,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',10,30,9,0,0,214,7,2,0,1,1,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',15,19,1,0,0,2,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',23,32,1,0,0,3,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',7,23,1,0,0,1,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',21,34,4,1,0,445,3,1,1,1,0,11);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',6,14,1,0,0,978,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',3,32,2,0,0,1,2,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',14,35,2,0,0,2,2,0,0,1,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',6,31,5,0,0,392,4,1,0,0,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',23,33,2,0,0,1,2,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',8,28,2,0,0,804,2,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',38,7,1,0,0,522,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',25,16,1,0,0,961,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',25,27,3,0,0,258,1,2,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',16,28,6,0,0,125,1,5,0,2,1,10);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',17,24,3,0,0,511,3,0,0,0,0,10);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',10,14,2,0,0,605,2,0,0,1,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',20,16,1,0,0,568,0,1,1,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',25,25,4,0,0,433,4,0,0,1,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ath',10,33,6,0,0,479,6,0,0,0,0,15);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',10,33,12,0,0,234,12,0,2,1,0,26);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',11,14,1,0,0,837,1,0,0,1,1,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',9,19,3,0,0,218,2,1,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',11,35,5,0,0,601,5,0,0,0,0,14);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('cel',18,21,3,0,0,244,1,2,1,1,0,9);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',20,21,6,0,0,274,6,0,0,1,0,13);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',12,20,2,0,1,780,2,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',17,21,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',14,26,3,0,0,671,3,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',14,6,2,0,0,177,2,0,0,0,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',16,22,1,0,0,1,0,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',17,25,5,0,0,376,5,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',3,28,1,0,0,2,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('sev',15,6,2,0,0,221,2,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',10,32,15,3,0,172,15,0,1,2,1,35);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',9,32,8,4,0,358,8,0,1,1,0,18);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',9,33,28,8,0,101,28,0,1,3,0,45);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('osa',23,22,2,0,0,781,2,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',18,16,1,0,0,1,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',8,29,5,0,0,245,5,0,0,1,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',15,4,1,0,0,57,0,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',28,17,2,0,0,603,2,0,1,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',20,23,4,0,0,473,4,0,0,1,1,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',11,33,13,0,0,189,13,0,0,0,0,30);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',9,10,2,0,0,385,1,1,2,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',9,32,21,5,0,127,21,0,0,3,0,34);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',31,32,1,0,0,3,0,1,0,1,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',6,10,2,0,0,254,2,0,0,0,0,6);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',27,15,1,0,0,313,0,1,1,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',24,30,8,0,0,195,4,4,0,0,0,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',24,31,17,2,0,158,17,0,0,3,0,33);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',11,28,4,0,0,409,4,0,0,2,0,11);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',8,22,1,0,0,1,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',31,12,1,0,0,538,0,1,0,1,1,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bet',14,14,2,0,0,436,2,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',6,24,2,0,0,827,1,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',12,18,3,0,0,332,3,0,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',16,29,1,0,0,2,1,0,0,0,0,1);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',23,11,2,0,0,240,2,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('get',16,8,1,0,0,697,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',9,25,7,0,0,305,7,0,1,2,0,16);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rma',4,26,4,0,0,567,4,0,0,1,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('dep',19,15,2,0,0,602,2,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',20,23,3,0,0,464,2,1,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',8,25,3,0,0,681,3,0,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',5,11,1,0,0,267,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',11,24,2,0,0,629,1,1,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('atm',5,21,2,0,0,642,1,1,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('val',24,30,1,0,0,2,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('ray',2,32,1,0,0,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',10,35,10,0,0,227,9,1,1,2,1,26);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('lev',10,32,3,0,0,930,3,0,0,0,0,8);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mal',18,30,8,1,0,295,8,0,1,1,0,21);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('vad',22,22,3,3,0,632,3,0,1,1,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('zar',32,30,1,0,0,2,1,0,0,0,0,3);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',4,31,1,0,0,3,1,0,0,0,0,2);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('esp',22,24,3,0,0,576,2,1,0,0,0,7);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('mga',3,31,2,0,1,1,2,0,0,0,0,4);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('rso',10,32,7,2,0,370,7,0,0,2,0,11);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('bar',6,26,5,0,0,397,4,1,0,0,0,5);
INSERT INTO goleadores (idEquipo,dorsal,partidos,goles,penaltis,pp,minutosGol,gTtitular,gSuplente,gPuntos,gVictoria,gRemontada,porcentaje) VALUES ('gda',9,28,6,1,0,293,6,0,1,0,0,19);

-- Confirmar la inserción en la tabla
COMMIT;

/*
-- Query: select * from porteros
*/
BEGIN;
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('dep',1,32,63);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('mal',13,32,61);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('ath',1,32,56);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('cel',13,34,50);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('bet',13,30,45);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('zar',1,31,45);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('ray',1,27,44);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('osa',13,35,42);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('lev',1,29,41);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('val',1,24,39);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('get',1,29,39);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('mga',13,34,38);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('gda',1,24,37);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('vad',13,24,37);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('rso',1,28,36);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('bar',1,29,32);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('rma',25,23,28);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('atm',13,35,28);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('esp',1,16,24);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('esp',13,20,23);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('sev',1,15,19);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('sev',13,12,17);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('rma',1,19,17);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('gda',13,11,16);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('get',13,7,13);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('ray',25,8,13);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('vad',1,11,13);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('val',13,11,11);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('rso',13,7,9);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('mga',1,3,7);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('mal',1,3,7);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('zar',13,3,6);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('bar',13,6,6);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('lev',13,6,6);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('bet',25,2,5);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('ray',13,2,4);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('ath',13,3,4);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('bet',1,4,4);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('rma',13,3,3);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('dep',13,4,3);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('mal',25,1,2);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('atm',1,1,2);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('zar',30,2,2);
INSERT INTO porteros (idEquipo,dorsal,partidos,goles) VALUES ('cel',1,2,2);

-- Confirmar la inserción en la tabla
COMMIT;
/*
-- Query: select * from jornadas
*/
BEGIN;
INSERT INTO jornadas (num,fecha) VALUES (1,'2019-08-19');
INSERT INTO jornadas (num,fecha) VALUES (2,'2019-08-26');
INSERT INTO jornadas (num,fecha) VALUES (3,'2020-12-29');
INSERT INTO jornadas (num,fecha) VALUES (4,'2019-09-16');
INSERT INTO jornadas (num,fecha) VALUES (5,'2019-09-23');
INSERT INTO jornadas (num,fecha) VALUES (6,'2002-01-11');
INSERT INTO jornadas (num,fecha) VALUES (7,'2020-05-13');
INSERT INTO jornadas (num,fecha) VALUES (8,'2019-10-21');
INSERT INTO jornadas (num,fecha) VALUES (9,'2019-10-28');
INSERT INTO jornadas (num,fecha) VALUES (10,'2019-11-04');
INSERT INTO jornadas (num,fecha) VALUES (11,'2019-11-11');
INSERT INTO jornadas (num,fecha) VALUES (12,'2019-11-18');
INSERT INTO jornadas (num,fecha) VALUES (13,'2019-11-25');
INSERT INTO jornadas (num,fecha) VALUES (14,'2019-12-02');
INSERT INTO jornadas (num,fecha) VALUES (15,'2019-12-09');
INSERT INTO jornadas (num,fecha) VALUES (16,'2019-12-16');
INSERT INTO jornadas (num,fecha) VALUES (17,'2019-12-22');
INSERT INTO jornadas (num,fecha) VALUES (18,'2020-01-06');
INSERT INTO jornadas (num,fecha) VALUES (19,'2020-01-13');
INSERT INTO jornadas (num,fecha) VALUES (20,'2020-01-20');
INSERT INTO jornadas (num,fecha) VALUES (21,'2020-01-27');
INSERT INTO jornadas (num,fecha) VALUES (22,'2020-02-03');
INSERT INTO jornadas (num,fecha) VALUES (23,'2020-02-10');
INSERT INTO jornadas (num,fecha) VALUES (24,'2020-02-17');
INSERT INTO jornadas (num,fecha) VALUES (25,'2020-02-24');
INSERT INTO jornadas (num,fecha) VALUES (26,'2020-03-03');
INSERT INTO jornadas (num,fecha) VALUES (27,'2020-03-10');
INSERT INTO jornadas (num,fecha) VALUES (28,'2020-03-17');
INSERT INTO jornadas (num,fecha) VALUES (29,'2020-03-31');
INSERT INTO jornadas (num,fecha) VALUES (30,'2020-04-07');
INSERT INTO jornadas (num,fecha) VALUES (31,'2020-04-14');
INSERT INTO jornadas (num,fecha) VALUES (32,'2020-04-21');
INSERT INTO jornadas (num,fecha) VALUES (33,'2020-04-28');
INSERT INTO jornadas (num,fecha) VALUES (34,'2020-05-05');
INSERT INTO jornadas (num,fecha) VALUES (35,'2020-05-12');
INSERT INTO jornadas (num,fecha) VALUES (36,'2020-05-19');
INSERT INTO jornadas (num,fecha) VALUES (37,'2020-05-29');
INSERT INTO jornadas (num,fecha) VALUES (38,'2020-06-01');

-- Confirmar la inserción en la tabla
COMMIT;
/*
-- Query: select * from partidos
*/
BEGIN;
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','atm',21,3,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','bar',33,2,2,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','bet',1,3,5,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','cel',15,1,0,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','dep',13,1,1,36);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','esp',23,0,4,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','gda',29,1,0,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','get',9,1,2,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','lev',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','mal',35,2,1,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','mga',5,0,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','osa',7,1,0,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','ray',19,1,2,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','rma',31,0,3,35);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','rso',25,1,3,42);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','sev',11,2,1,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','vad',3,2,0,70);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','val',27,1,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ath','zar',17,0,2,30);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','ath',2,4,0,80);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','bar',35,1,2,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','bet',22,1,0,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','cel',17,1,0,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','dep',15,6,0,94);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','esp',25,1,0,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','gda',31,5,0,73);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','get',11,2,0,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','lev',20,2,0,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','mal',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','mga',7,2,1,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','osa',9,3,1,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','ray',4,4,3,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','rma',33,1,2,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','rso',27,0,1,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','sev',13,4,0,68);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','vad',5,2,1,67);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','val',29,1,1,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('atm','zar',19,2,0,80);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','ath',14,5,1,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','atm',16,4,1,75);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','bet',34,4,2,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','cel',10,3,1,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','dep',27,2,0,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','esp',18,4,0,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','gda',5,2,0,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','get',23,6,1,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','lev',32,1,0,69);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','mal',30,5,0,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','mga',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','osa',21,5,1,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','ray',28,3,1,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','rma',7,2,2,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','rso',1,5,1,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','sev',25,2,1,69);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','vad',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','val',3,1,0,69);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bar','zar',12,3,1,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','ath',20,1,1,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','atm',3,2,4,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','bar',15,1,2,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','cel',35,1,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','dep',33,1,1,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','esp',5,1,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','gda',11,1,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','get',29,0,0,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','lev',19,2,0,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','mal',17,1,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','mga',25,3,0,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','osa',27,2,1,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','ray',2,1,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','rma',13,1,0,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','rso',7,2,0,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','sev',31,3,3,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','vad',23,0,0,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','val',9,1,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('bet','zar',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','ath',34,1,1,68);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','atm',36,1,3,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','bar',29,2,2,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','bet',16,0,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','dep',9,1,1,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','esp',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','gda',25,2,1,29);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','get',5,2,1,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','lev',14,1,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','mal',12,1,1,80);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','mga',1,0,1,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','osa',3,2,0,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','ray',30,0,2,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','rma',27,1,2,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','rso',21,1,1,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','sev',7,2,0,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','vad',18,3,1,70);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','val',23,0,1,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('cel','zar',32,2,1,77);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','ath',32,1,1,68);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','atm',34,0,0,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','bar',8,4,5,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','bet',14,2,3,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','cel',28,3,1,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','esp',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','gda',23,0,3,19);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','get',3,1,1,58);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','lev',12,0,2,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','mal',10,1,0,31);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','mga',18,1,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','osa',1,2,0,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','ray',26,0,0,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','rma',25,1,2,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','rso',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','sev',5,0,2,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','vad',16,0,0,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','val',21,2,3,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('dep','zar',30,3,2,77);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','ath',4,3,3,42);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','atm',6,0,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','bar',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','bet',24,1,0,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','cel',19,1,0,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','dep',17,2,0,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','gda',33,0,1,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','get',13,0,2,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','lev',22,3,2,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','mal',20,3,2,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','mga',9,0,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','osa',11,0,3,33);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','ray',8,3,2,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','rma',35,1,1,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','rso',29,2,2,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','sev',15,2,2,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','vad',26,0,0,42);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','val',31,3,3,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('esp','zar',2,1,2,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','ath',10,1,2,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','atm',12,0,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','bar',24,1,2,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','bet',30,1,5,42);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','cel',6,2,1,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','dep',4,1,1,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','esp',14,0,0,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','get',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','lev',28,1,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','mal',26,1,2,77);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','mga',34,1,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','osa',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','ray',20,2,0,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','rma',22,1,0,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','rso',16,0,0,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','sev',2,1,1,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','vad',32,1,1,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','val',18,1,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('gda','zar',8,1,2,71);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','ath',28,1,0,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','atm',30,0,0,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','bar',4,1,4,31);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','bet',10,2,4,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','cel',24,3,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','dep',22,3,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','esp',32,0,2,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','gda',19,2,2,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','lev',8,0,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','mal',6,1,0,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','mga',14,1,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','osa',16,1,1,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','ray',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','rma',2,2,1,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','rso',34,2,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','sev',20,1,1,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','vad',12,2,1,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','val',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('get','zar',26,2,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','ath',18,3,1,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','atm',1,1,1,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','bar',13,0,4,28);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','bet',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','cel',33,0,1,35);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','dep',31,0,4,26);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','esp',3,3,2,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','gda',9,3,1,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','get',27,0,0,58);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','mal',15,4,0,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','mga',23,1,2,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','osa',25,0,2,32);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','ray',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','rma',11,1,2,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','rso',5,2,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','sev',29,1,0,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','vad',21,2,1,67);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','val',7,1,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('lev','zar',35,0,0,38);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','ath',16,0,1,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','atm',18,1,1,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','bar',11,2,4,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','bet',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','cel',31,1,0,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','dep',29,2,3,73);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','esp',1,2,1,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','gda',7,1,2,21);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','get',25,1,3,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','lev',34,1,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','mga',21,2,3,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','osa',23,1,1,70);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','ray',32,1,1,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','rma',9,0,5,29);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','rso',3,1,0,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','sev',27,2,1,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','vad',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','val',5,2,0,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mal','zar',14,1,1,74);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','ath',24,1,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','atm',26,0,0,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','bar',19,1,3,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','bet',6,4,0,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','cel',20,1,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','dep',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','esp',28,0,2,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','gda',15,4,0,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','get',33,2,1,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','lev',4,3,1,58);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','mal',2,1,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','osa',31,1,0,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','ray',10,1,2,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','rma',17,3,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','rso',11,1,2,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','sev',35,0,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','vad',8,2,1,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','val',13,4,0,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('mga','zar',22,1,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','ath',26,0,1,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','atm',28,0,2,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','bar',2,1,2,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','bet',8,0,0,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','cel',22,1,0,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','dep',20,2,1,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','esp',30,0,2,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','gda',17,1,2,31);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','get',35,1,0,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','lev',6,4,0,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','mal',4,1,1,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','mga',12,0,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','ray',14,1,0,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','rma',19,0,0,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','rso',32,0,0,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','sev',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','vad',10,0,1,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','val',15,0,1,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('osa','zar',24,1,0,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','ath',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','atm',23,2,1,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','bar',9,0,5,25);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','bet',21,3,0,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','cel',11,3,2,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','dep',7,2,1,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','esp',27,2,0,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','gda',1,1,0,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','get',18,3,1,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','lev',17,3,0,53);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','mal',13,2,0,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','mga',29,1,3,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','osa',33,2,2,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','rma',5,0,2,38);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','rso',31,0,2,42);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','sev',3,0,0,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','vad',25,1,2,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','val',35,0,4,38);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('ray','zar',15,0,2,38);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','ath',12,5,1,80);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','atm',14,2,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','bar',26,2,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','bet',32,3,1,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','cel',8,2,0,84);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','dep',6,5,1,88);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','esp',16,2,2,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','gda',3,3,0,69);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','get',21,4,0,70);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','lev',30,5,1,78);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','mal',28,5,2,89);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','mga',36,6,2,64);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','osa',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','ray',24,2,0,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','rso',18,4,3,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','sev',23,4,1,65);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','vad',34,4,3,67);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','val',1,1,1,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rma','zar',10,4,0,86);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','ath',6,2,0,74);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','atm',8,0,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','bar',20,3,2,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','bet',26,3,3,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','cel',2,2,1,81);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','dep',19,1,1,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','esp',10,0,1,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','gda',35,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','get',15,1,1,58);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','lev',24,1,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','mal',22,3,0,89);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','mga',30,4,2,58);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','osa',13,0,0,70);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','ray',12,4,0,72);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','rma',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','sev',17,2,1,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','vad',28,4,1,73);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','val',33,4,2,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('rso','zar',4,2,0,80);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','ath',30,2,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','atm',32,0,1,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','bar',6,2,3,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','bet',12,5,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','cel',26,4,1,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','dep',24,3,1,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','esp',34,3,0,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','gda',21,3,0,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','get',1,2,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','lev',10,0,0,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','mal',8,3,2,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','mga',16,0,2,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','osa',18,1,0,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','ray',22,2,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','rma',4,1,0,47);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','rso',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','vad',14,1,2,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','val',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('sev','zar',28,4,0,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','ath',22,2,2,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','atm',24,0,3,37);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','bar',17,1,3,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','bet',4,0,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','cel',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','dep',35,1,0,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','esp',7,1,1,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','gda',13,1,0,43);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','get',31,2,1,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','lev',2,2,0,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','mal',19,3,1,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','mga',27,1,1,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','osa',29,1,3,34);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','ray',6,6,1,75);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','rma',15,2,3,41);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','rso',9,2,2,48);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','sev',33,1,1,56);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','val',11,1,1,50);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('vad','zar',20,2,0,46);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','ath',8,3,2,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','atm',10,2,0,52);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','bar',22,1,1,40);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','bet',28,3,0,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','cel',4,2,1,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','dep',2,3,3,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','esp',12,2,1,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','gda',37,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','get',17,4,2,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','lev',26,2,2,54);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','mal',24,2,0,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','mga',32,5,1,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','osa',34,4,0,66);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','ray',16,0,1,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','rma',20,0,5,29);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','rso',14,2,5,39);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','sev',19,2,0,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','vad',30,2,1,57);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('val','zar',6,2,0,60);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','ath',36,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','atm',38,NULL,NULL,NULL);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','bar',31,0,3,31);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','bet',18,1,2,51);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','cel',13,0,1,27);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','dep',11,5,3,36);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','esp',21,0,0,62);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','gda',27,0,0,30);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','get',7,0,1,55);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','lev',16,0,1,63);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','mal',33,3,2,33);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','mga',3,0,1,49);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','osa',5,3,1,76);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','ray',34,3,0,69);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','rma',29,1,1,44);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','rso',23,1,2,45);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','sev',9,2,1,59);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','vad',1,0,1,61);
INSERT INTO partidos (idEquipoL,idEquipoV,jornada,golesL,golesV,posesionL) VALUES ('zar','val',25,2,2,50);
-- Confirmar la inserción en la tabla
COMMIT;



-- ---------------------------------------------------------------------

-- Copyright Jose Ramón Cebolla - 2025

-- -----------------------------------------------------
-- TABLAS NECESARIAS PARA IMPLEMENTAR LA SEGURIDAD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS usuarios_roles;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS usuarios (
  id BIGSERIAL PRIMARY KEY, 
  nombre VARCHAR(255) NOT NULL,
  nickname VARCHAR(255) NOT NULL UNIQUE, 
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY, 
  nombre VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Table `usuarios_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios_roles (
  idUsuario BIGINT NOT NULL,
  idRol INTEGER NOT NULL,
  PRIMARY KEY (idUsuario, idRol),
  CONSTRAINT usuarios_roles_fk_usuarios FOREIGN KEY (idUsuario)
    REFERENCES usuarios (id) ON DELETE CASCADE,
  CONSTRAINT usuarios_roles_fk_roles FOREIGN KEY (idRol)
    REFERENCES roles (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Insert roles data
-- -----------------------------------------------------
INSERT INTO roles (nombre) VALUES
('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_ENTRENADOR'),
('ROLE_JUGADOR'),
('ROLE_ARBITRO');




