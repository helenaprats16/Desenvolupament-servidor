-- Tabla Profesores
CREATE TABLE profesores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla Cursos
CREATE TABLE cursos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    abreviatura VARCHAR(255) NOT NULL,
    ciclo VARCHAR(255),
    tutor  BIGINT REFERENCES profesores(id) ON DELETE SET NULL
);

-- Tabla Alumnos
CREATE TABLE alumnos (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INTEGER,
    curso BIGINT REFERENCES cursos(id) ON DELETE SET NULL,
    erasmus BOOLEAN DEFAULT FALSE,
    lenguaje_favorito VARCHAR(255) DEFAULT '',
    genero VARCHAR(255),
    horario VARCHAR(255),
    pais VARCHAR(255),
    hobbies VARCHAR(255)
);

-- Tabla Modulos
CREATE TABLE modulos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    num_horas INTEGER,
    abreviatura VARCHAR(255),
    curso BIGINT REFERENCES cursos(id) ON DELETE SET NULL,
    profesor BIGINT REFERENCES profesores(id) ON DELETE SET NULL
);

-- Tabla Matriculaciones que enlaza Alumno y Modulo
CREATE TABLE matriculaciones (
    id SERIAL PRIMARY KEY,
    alumno_dni VARCHAR(9) REFERENCES alumnos(dni) ON DELETE CASCADE,
    modulo_id BIGINT REFERENCES modulos(id) ON DELETE CASCADE,
    nota VARCHAR(255)
);