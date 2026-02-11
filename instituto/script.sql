CREATE TABLE docalumnos(
    id SERIAL PRIMARY KEY,
    dni VARCHAR(9) NOT NULL REFERENCES alumnos(dni) ON DELETE CASCADE,
    nombre_fichero VARCHAR(255) NOT NULL,
    comentario TEXT,
    base64_documento TEXT,
    extension_documento VARCHAR(5),
    content_type_documento VARCHAR(50),
    creado_por VARCHAR(255)
);