-- ================================================================
-- INSERCIÓN DE DATOS DE EJEMPLO - ARTISTAS ESPAÑOLES
-- ================================================================

-- 1. Insertar 15 artistas españoles
INSERT INTO ms_artistas (id, nombre, pais, biografia, fecha_inicio_carrera, activo) VALUES
(1, 'C. Tangana', 'España', 'Cantante y compositor de trap y pop urbano español', '2016-01-01', true),
(2, 'Rosalía', 'España', 'Cantante y compositora de flamenco fusionado con pop urbano', '2017-01-01', true),
(3, 'Aitana', 'España', 'Cantante y compositora de pop española', '2017-01-01', true),
(4, 'Dani Martín', 'España', 'Cantante y compositor de pop rock español', '1999-01-01', true),
(5, 'Vetusta Morla', 'España', 'Banda de rock indie española', '2008-01-01', true),
(6, 'Love of Lesbian', 'España', 'Banda de pop rock e indie española', '1997-01-01', true),
(7, 'Beret', 'España', 'Cantante y compositor de pop urbano español', '2018-01-01', true),
(8, 'Natalia Lacunza', 'España', 'Cantante y compositora de pop indie española', '2018-01-01', true),
(9, 'Alizzz', 'España', 'Productor y cantante de electrónica y pop urbano', '2015-01-01', true),
(10, 'Bad Gyal', 'España', 'Cantante de reggaeton y dancehall española', '2016-01-01', true),
(11, 'Sen Senra', 'España', 'Cantante y compositor de pop alternativo español', '2018-01-01', true),
(12, 'Cupido', 'España', 'Dúo de pop indie español', '2020-01-01', true),
(13, 'Queralt Lahoz', 'España', 'Cantante de pop indie española', '2019-01-01', true),
(14, 'María Escarmiento', 'España', 'Cantante de pop electrónico española', '2020-01-01', true),
(15, 'Cariño', 'España', 'Banda de indie pop española', '2018-01-01', true);

-- 2. Insertar 3 álbumes
INSERT INTO ms_albumes (id, titulo, fecha_lanzamiento, discografica, numero_canciones, artista_id, activo) VALUES
(1, 'El Madrileño', '2021-02-26', 'Sony Music', 14, 1, true),
(2, 'Motomami', '2022-03-18', 'Columbia Records', 16, 2, true),
(3, '11 Razones', '2020-11-20', 'Universal Music', 11, 3, true);

-- 3. Insertar canciones para el álbum "El Madrileño" (15 canciones)
INSERT INTO ms_canciones (id, titulo, duracion_segundos, genero, numero_reproducciones, es_single, album_id, activo) VALUES
(1, 'Nominao', 180, 'Pop Urbano', 45000000, true, 1, true),
(2, 'Comerte Entera', 195, 'Pop Urbano', 35000000, true, 1, true),
(3, 'Los Tontos', 210, 'Pop Urbano', 28000000, false, 1, true),
(4, 'Ingobernable', 185, 'Pop Urbano', 22000000, false, 1, true),
(5, 'Tú Me Dejaste De Querer', 240, 'Flamenco', 38000000, true, 1, true),
(6, 'CAMBIA!', 200, 'Pop Urbano', 19000000, false, 1, true),
(7, 'Demasiadas Mujeres', 195, 'Pop Urbano', 42000000, true, 1, true),
(8, 'Para No Olvidar', 225, 'Pop Urbano', 15000000, false, 1, true),
(9, 'Nunca Estoy', 210, 'Pop Urbano', 12000000, false, 1, true),
(10, 'Comerte Entera (Remix)', 205, 'Pop Urbano', 8000000, false, 1, true),
(11, 'Párteme La Cara', 190, 'Pop Urbano', 25000000, false, 1, true),
(12, 'Hong Kong', 215, 'Pop Urbano', 9000000, false, 1, true),
(13, 'Un Veneno', 195, 'Pop Urbano', 11000000, false, 1, true),
(14, 'Antes de Morirme', 230, 'Pop Urbano', 7000000, false, 1, true),
(15, 'La Culpa', 220, 'Pop Urbano', 6000000, false, 1, true);

-- 4. Insertar canciones para el álbum "Motomami" (15 canciones)
INSERT INTO ms_canciones (id, titulo, duracion_segundos, genero, numero_reproducciones, es_single, album_id, activo) VALUES
(16, 'SAOKO', 120, 'Reggaeton', 85000000, true, 2, true),
(17, 'CANDY', 185, 'Reggaeton', 72000000, true, 2, true),
(18, 'LA FAMA', 210, 'Pop', 95000000, true, 2, true),
(19, 'BULERÍAS', 165, 'Flamenco', 45000000, false, 2, true),
(20, 'CHICKEN TERIYAKI', 145, 'Reggaeton', 38000000, false, 2, true),
(21, 'HENTAI', 175, 'Pop', 52000000, false, 2, true),
(22, 'BIZCOCHITO', 160, 'Reggaeton', 48000000, false, 2, true),
(23, 'G3 N15', 195, 'Pop', 29000000, false, 2, true),
(24, 'MOTOMAMI', 170, 'Pop', 33000000, false, 2, true),
(25, 'DIABLO', 180, 'Pop', 41000000, false, 2, true),
(26, 'DELIRIO DE GRANDEZA', 220, 'Pop', 27000000, false, 2, true),
(27, 'CUUUUuuuuuute', 150, 'Pop', 23000000, false, 2, true),
(28, 'COMO UN G', 185, 'Reggaeton', 19000000, false, 2, true),
(29, 'Abcdefg', 165, 'Pop', 16000000, false, 2, true),
(30, 'LA COMBI VERSACE', 140, 'Reggaeton', 21000000, false, 2, true);

-- 5. Insertar canciones para el álbum "11 Razones" (15 canciones)
INSERT INTO ms_canciones (id, titulo, duracion_segundos, genero, numero_reproducciones, es_single, album_id, activo) VALUES
(31, '11 Razones', 200, 'Pop', 65000000, true, 3, true),
(32, 'Corazón Sin Vida', 195, 'Pop', 58000000, true, 3, true),
(33, 'Más De Lo Que Pensé', 185, 'Pop', 42000000, false, 3, true),
(34, 'Tu Foto Del DNI', 175, 'Pop', 38000000, false, 3, true),
(35, 'Si Tú La Quieres', 210, 'Pop', 35000000, false, 3, true),
(36, 'No Puedo Vivir Sin Ti', 190, 'Pop', 29000000, false, 3, true),
(37, 'Cuando Te Fuiste', 205, 'Pop', 26000000, false, 3, true),
(38, 'Mariposas', 180, 'Pop', 32000000, false, 3, true),
(39, 'Contigo', 195, 'Pop', 24000000, false, 3, true),
(40, 'Volver A Empezar', 220, 'Pop', 18000000, false, 3, true),
(41, 'Para Siempre', 200, 'Pop', 15000000, false, 3, true),
(42, 'Sin Miedo', 185, 'Pop', 13000000, false, 3, true),
(43, 'Todo Cambió', 210, 'Pop', 11000000, false, 3, true),
(44, 'Mi Verdad', 195, 'Pop', 9000000, false, 3, true),
(45, 'Hasta El Final', 225, 'Pop', 7000000, false, 3, true);

-- 6. Insertar 15 usuarios
INSERT INTO ms_usuarios (id, username, email, nombre_completo, fecha_registro, es_premium, activo) VALUES
(1, 'coder_java', 'coder.java@ieslluissimarro.org', 'Ana García López', '2023-01-15', true, true),
(2, 'dev_python', 'dev.python@ieslluissimarro.org', 'Carlos Rodríguez Martín', '2023-02-20', true, true),
(3, 'web_dev', 'web.dev@ieslluissimarro.org', 'Laura Martínez Sánchez', '2023-03-10', false, true),
(4, 'data_science', 'data.science@ieslluissimarro.org', 'David Fernández Gómez', '2023-01-28', true, true),
(5, 'ai_master', 'ai.master@ieslluissimarro.org', 'Elena Pérez Ruiz', '2023-04-05', false, true),
(6, 'cyber_sec', 'cyber.sec@ieslluissimarro.org', 'Pablo González Díaz', '2023-02-14', true, true),
(7, 'cloud_eng', 'cloud.eng@ieslluissimarro.org', 'Marta Sánchez López', '2023-03-22', true, true),
(8, 'mobile_dev', 'mobile.dev@ieslluissimarro.org', 'Javier Romero García', '2023-01-08', false, true),
(9, 'game_dev', 'game.dev@ieslluissimarro.org', 'Sara Torres Martín', '2023-04-18', true, true),
(10, 'db_admin', 'db.admin@ieslluissimarro.org', 'Daniel Jiménez Ruiz', '2023-02-03', true, true),
(11, 'frontend_pro', 'frontend.pro@ieslluissimarro.org', 'Cristina Vargas Díaz', '2023-03-30', false, true),
(12, 'backend_master', 'backend.master@upm.es', 'Alejandro Moreno Sánchez', '2023-01-25', true, true),
(13, 'fullstack', 'fullstack@upm.es', 'Patricia Navarro López', '2023-04-12', true, true),
(14, 'devops_eng', 'devops.eng@upm.es', 'Roberto Castro Martín', '2023-02-28', false, true),
(15, 'ui_designer', 'ui.designer@upm.es', 'Nuria Ortiz García', '2023-03-15', true, true);

-- 7. Insertar 15 playlists
INSERT INTO ms_playlists (id, nombre, descripcion, es_publica, usuario_id) VALUES
(1, 'Coding Sessions', 'Música para programar toda la noche', true, 1),
(2, 'Study Flow', 'Playlist para concentrarse estudiando', true, 2),
(3, 'Spanish Hits', 'Los mejores éxitos españoles del momento', true, 3),
(4, 'Indie Español', 'Descubre el indie español más actual', true, 4),
(5, 'Urban Vibes', 'Lo mejor del urbano español', true, 5),
(6, 'Focus Mode', 'Música para mantener la concentración', false, 6),
(7, 'Weekend Mood', 'Para desconectar del código', true, 7),
(8, 'Road Trip', 'Música para viajes largos', true, 8),
(9, 'Gym Sessions', 'Energía para entrenar', true, 9),
(10, 'Chill Coding', 'Código relajado con buena música', true, 10),
(11, 'Spanish Classics', 'Clásicos modernos españoles', true, 11),
(12, 'New Discoveries', 'Nuevos artistas por descubrir', true, 12),
(13, 'Work From Home', 'Ambiente musical para teletrabajar', true, 13),
(14, 'Party Spanish', 'Fiesta con música española', false, 14),
(15, 'Relax & Code', 'Música tranquila para programar', true, 15);

-- 8. Insertar relaciones playlists-canciones
INSERT INTO ms_playlists_canciones (playlist_id, cancion_id, orden) VALUES
-- Playlist 1: Coding Sessions
(1, 1, 1), (1, 16, 2), (1, 31, 3), (1, 7, 4), (1, 18, 5),
-- Playlist 2: Study Flow
(2, 33, 1), (2, 39, 2), (2, 44, 3), (2, 12, 4), (2, 26, 5),
-- Playlist 3: Spanish Hits
(3, 1, 1), (3, 16, 2), (3, 31, 3), (3, 7, 4), (3, 18, 5),
-- Playlist 4: Indie Español
(4, 33, 1), (4, 39, 2), (4, 12, 3), (4, 26, 4), (4, 44, 5),
-- Playlist 5: Urban Vibes
(5, 1, 1), (5, 7, 2), (5, 16, 3), (5, 20, 4), (5, 28, 5),
-- Playlist 6: Focus Mode
(6, 33, 1), (6, 39, 2), (6, 44, 3), (6, 12, 4), (6, 26, 5),
-- Playlist 7: Weekend Mood
(7, 1, 1), (7, 16, 2), (7, 7, 3), (7, 18, 4), (7, 31, 5),
-- Playlist 8: Road Trip
(8, 31, 1), (8, 1, 2), (8, 16, 3), (8, 33, 4), (8, 7, 5),
-- Playlist 9: Gym Sessions
(9, 16, 1), (9, 1, 2), (9, 20, 3), (9, 7, 4), (9, 28, 5),
-- Playlist 10: Chill Coding
(10, 33, 1), (10, 39, 2), (10, 44, 3), (10, 12, 4), (10, 26, 5),
-- Playlist 11: Spanish Classics
(11, 1, 1), (11, 31, 2), (11, 7, 3), (11, 18, 4), (11, 16, 5),
-- Playlist 12: New Discoveries
(12, 33, 1), (12, 39, 2), (12, 44, 3), (12, 12, 4), (12, 26, 5),
-- Playlist 13: Work From Home
(13, 33, 1), (13, 39, 2), (13, 44, 3), (13, 12, 4), (13, 26, 5),
-- Playlist 14: Party Spanish
(14, 1, 1), (14, 16, 2), (14, 7, 3), (14, 20, 4), (14, 28, 5),
-- Playlist 15: Relax & Code
(15, 33, 1), (15, 39, 2), (15, 44, 3), (15, 12, 4), (15, 26, 5);

-- 9. Insertar historial de reproducciones
INSERT INTO ms_historial_reproducciones (id, usuario_id, cancion_id, segundos_reproducidos, reproduccion_completa) VALUES
(1, 1, 1, 180, true), (2, 1, 16, 120, true), (3, 2, 31, 200, true),
(4, 3, 7, 195, true), (5, 4, 18, 210, true), (6, 5, 33, 185, true),
(7, 6, 39, 195, true), (8, 7, 44, 195, true), (9, 8, 12, 215, true),
(10, 9, 26, 220, true), (11, 10, 1, 180, true), (12, 11, 16, 120, true),
(13, 12, 31, 200, true), (14, 13, 7, 195, true), (15, 14, 18, 210, true);

-- Verificar las inserciones
SELECT 'Artistas insertados: ' || COUNT(*) FROM ms_artistas;
SELECT 'Álbumes insertados: ' || COUNT(*) FROM ms_albumes;
SELECT 'Canciones insertadas: ' || COUNT(*) FROM ms_canciones;
SELECT 'Usuarios insertados: ' || COUNT(*) FROM ms_usuarios;
SELECT 'Playlists insertadas: ' || COUNT(*) FROM ms_playlists;
SELECT 'Relaciones playlist-canción: ' || COUNT(*) FROM ms_playlists_canciones;
SELECT 'Reproducciones en historial: ' || COUNT(*) FROM ms_historial_reproducciones;