-- Actualiza la tabla usuario para soportar login JWT

ALTER TABLE usuario
    ADD COLUMN IF NOT EXISTS password VARCHAR(255);

UPDATE usuario
SET password = '$2a$10$nCOnsvVWpUL04flv1ApuC./AHbrsqBzxkff3nKf9X3GXsIn6TZxlC'
WHERE password IS NULL
    OR password = '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5jG1QdPZXoqBYwygJyI072QtdgQ36';

ALTER TABLE usuario
    ALTER COLUMN password SET NOT NULL;
