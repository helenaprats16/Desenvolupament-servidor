DROP TABLE IF EXISTS director CASCADE;
DROP TABLE IF EXISTS genero CASCADE;
DROP TABLE IF EXISTS usuarios_roles CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS pelicula CASCADE;
DROP TABLE IF EXISTS valoracion CASCADE;


CREATE TABLE director (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE
);

CREATE TABLE genero (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios_roles (
    idUsuario BIGINT NOT NULL,
    idRol INT NOT NULL,
    PRIMARY KEY (idUsuario, idRol),
    CONSTRAINT usuarios_roles_fk_usuarios FOREIGN KEY (idUsuario)
        REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT usuarios_roles_fk_roles FOREIGN KEY (idRol)
        REFERENCES roles (id) ON DELETE CASCADE
);

-- TABLA PELÍCULA SIN CHECK
CREATE TABLE pelicula (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    director_id BIGINT NOT NULL,
    genero_id BIGINT NOT NULL,
    año INT NOT NULL,  -- SIN CHECK
    duracion_min INT NOT NULL,
    sinopsis TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (director_id) REFERENCES director(id),
    FOREIGN KEY (genero_id) REFERENCES genero(id)
);

CREATE TABLE valoracion (
    id BIGSERIAL PRIMARY KEY,
    pelicula_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    puntuacion INT NOT NULL,
    comentario TEXT,
    fecha_valoracion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    likes INT DEFAULT 0,
    
    FOREIGN KEY (pelicula_id) REFERENCES pelicula(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


