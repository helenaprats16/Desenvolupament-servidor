# API REST BD Película

Projecte d'una API REST per gestionar:
- pel·lícules
- directors
- gèneres
- login amb JWT (rols USER i ADMIN)

## Què fa el projecte
- Permet llistar, crear, editar i borrar dades.
- Té control de permisos per rol.
- Té documentació Swagger per provar endpoints.

## Requisits
- Docker
- Java 17+
- Maven (o `mvnw`)

## Com executar (pas a pas)

### 1) Alçar la base de dades amb Docker Compose
Des de la carpeta arrel del projecte:

```bash
docker compose up -d
```

Açò crea PostgreSQL en:
- port: `5432`
- database: `pelicula`
- user: `pelicula`
- password: `pelicula`

### 2) Executar l'API Spring Boot
Amb Maven:

```bash
mvn spring-boot:run
```

O amb Maven Wrapper (Windows):

```bash
mvnw.cmd spring-boot:run
```

L'API arranca en:
- `http://localhost:8090`

## Credencials de prova (seed)
- usuari admin: `admin`
- usuari user: `carlosm`
- password per als usuaris seed: `password123`

## Swagger
URL de Swagger UI:
- `http://localhost:8090/swagger-ui/index.html`

URL OpenAPI JSON:
- `http://localhost:8090/api-docs`
