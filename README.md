📚 LiterAlura – Challenge Java
🚀 Descripción
LiterAlura es una aplicación Java que consume la API de Gutendex para buscar y registrar libros y autores en una base de datos.
El proyecto forma parte del challenge de Alura Latam y Oracle Next Education.

🛠️ Tecnologías utilizadas
☕ Java 17

🐘 PostgreSQL

🌐 Spring Data JPA

🔗 Gutendex API

🧰 Maven

🎯 Funcionalidades principales
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

📂 Estructura del proyecto
Código
src/main/java/com/literalura/literalura/
 ├── model/        # Entidades Autor y Libro
 ├── repository/   # Repositorios JPA
 ├── service/      # Lógica de negocio
 └── principal/    # Clase Principal con menú interactivo
▶️ Ejecución
Clonar el repositorio:

bash
git clone https://github.com/JosCapdev/LiterAlura.git
Configurar la base de datos PostgreSQL en application.properties.

Ejecutar la clase Principal desde tu IDE o con Maven:

bash
mvn spring-boot:run

👨‍💻 Autor
Proyecto desarrollado por JosCapdev como parte del challenge LiterAlura – Alura Latam & Oracle Next Education.
