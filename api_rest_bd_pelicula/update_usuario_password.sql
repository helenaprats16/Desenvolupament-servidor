-- Actualiza la tabla usuario para soportar login JWT

ALTER TABLE usuario
    ADD COLUMN IF NOT EXISTS password VARCHAR(255);

UPDATE usuario
SET password = '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5jG1QdPZXoqBYwygJyI072QtdgQ36'
WHERE password IS NULL;

ALTER TABLE usuario
    ALTER COLUMN password SET NOT NULL;
