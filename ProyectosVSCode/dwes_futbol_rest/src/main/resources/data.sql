-- Copyright Jose Ramón Cebolla - 2025
-- -----------------------------------------------------
-- Table `ciudades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ciudades;
CREATE  TABLE IF NOT EXISTS ciudades(
id IDENTITY,
nombre VARCHAR(50),
habitantes BIGINT,
 CONSTRAINT pk_ciudades PRIMARY KEY(id));


-- -----------------------------------------------------
-- Table `jornadas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jornadas` ;

CREATE  TABLE IF NOT EXISTS `jornadas` (
  `num` BIGINT NOT NULL ,
  `fecha` VARCHAR(10) NULL DEFAULT NULL ,
  CONSTRAINT pk_jornadas PRIMARY KEY (`num`) );
-- ---------------------------------------------------
-- INSERCIÓN DE DATOS
-- ---------------------------------------------------

INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (2,'Alacant',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (3,'Albacete',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (4,'Almeria',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (5,'Astúries',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (6,'Àvila',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (7,'Badajoz',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (8,'Barcelona',1615000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (9,'Bilbao',353000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (10,'Biscaia',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (11,'Burgos',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (12,'Càceres',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (13,'Cadis',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (14,'Cantàbria',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (15,'Castelló',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (16,'Ceuta',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (17,'Ciudad Real',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (18,'Còrdova',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (19,'Cornellà de Llobregat',85000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (20,'Cuenca',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (21,'Getafe',169000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (22,'Girona',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (23,'Granada',234000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (24,'Guadalajara',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (25,'Guipúscoa',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (26,'Huelva',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (27,'Illes Balears',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (28,'Jaén',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (29,'La Corunya',246000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (30,'La Rioja',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (31,'Las Palmas',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (32,'Lleida',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (33,'Lleó',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (34,'Lugo',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (35,'Madrid',3293000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (36,'Màlaga',568000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (37,'Melilla',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (38,'Múrcia',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (39,'Navarra',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (40,'Orense',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (41,'Osca',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (42,'Palència',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (43,'Palma de Mallorca',400000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (44,'Pamplona',197000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (45,'Pontevedra',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (46,'Salamanca',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (47,'Sant Sebastià',186000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (48,'Santa Cruz de Tenerife',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (49,'Saragossa',680000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (50,'Segòvia',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (51,'Sevilla',702000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (52,'Sória',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (53,'Tarragona',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (54,'Terol',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (55,'Toledo',NULL);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (56,'València',798000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (57,'Valladolid',313000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (58,'Vigo',297000);
INSERT INTO `ciudades` (`id`,`nombre`,`habitantes`) VALUES (59,'Zamora',NULL);

/*
-- Query: select * from jornadas
*/
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (1,'2019-08-19');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (2,'2019-08-26');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (3,'2020-12-29');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (4,'2019-09-16');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (5,'2019-09-23');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (6,'2002-01-11');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (7,'2020-05-13');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (8,'2019-10-21');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (9,'2019-10-28');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (10,'2019-11-04');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (11,'2019-11-11');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (12,'2019-11-18');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (13,'2019-11-25');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (14,'2019-12-02');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (15,'2019-12-09');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (16,'2019-12-16');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (17,'2019-12-22');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (18,'2020-01-06');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (19,'2020-01-13');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (20,'2020-01-20');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (21,'2020-01-27');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (22,'2020-02-03');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (23,'2020-02-10');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (24,'2020-02-17');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (25,'2020-02-24');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (26,'2020-03-03');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (27,'2020-03-10');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (28,'2020-03-17');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (29,'2020-03-31');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (30,'2020-04-07');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (31,'2020-04-14');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (32,'2020-04-21');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (33,'2020-04-28');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (34,'2020-05-05');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (35,'2020-05-12');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (36,'2020-05-19');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (37,'2020-05-29');
INSERT INTO `jornadas` (`num`,`fecha`) VALUES (38,'2020-06-01');