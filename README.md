# LiterAlura 📚

🚀 Descripción
LiterAlura es una aplicación Java que consume la API de Gutendex para buscar y registrar libros y autores en una base de datos.
El proyecto forma parte del challenge de Alura Latam y Oracle Next Education.


## 🚀 Funcionalidades principales
Buscar libros por título desde la API Gutendex.

Registrar libros y autores en la base de datos.

Listar libros registrados.

Listar autores registrados.

Filtrar autores vivos en un determinado año.

Listar libros por idioma.

✨ Extras implementados

📊 Estadísticas de descargas con DoubleSummaryStatistics.

🔝 Top 10 libros más descargados.

👤 Búsqueda de autores por nombre/apellido (coincidencia parcial).


---

## 🛠️ Tecnologías utilizadas

☕ Java 17

🐘 PostgreSQL

🌐 Spring Data JPA

🔗 Gutendex API

🧰 Maven

---

## 📂 Estructura del proyecto

Código

src/main/java/com/literalura/literalura/

 ├── model/        # Entidades Autor y Libro
 
 ├── repository/   # Repositorios JPA
 
 ├── service/      # Lógica de negocio
 
 └── principal/    # Clase Principal con menú interactivo

## 📋 Requisitos previos

- Tener instalado **Java 17** o superior.
- Tener configurado **Maven**.
- Acceso a una base de datos PostgreSQL.
- Conexión a internet para consumir la API.

---

## ▶️ Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/JosCapdev/LiterAlura.git
