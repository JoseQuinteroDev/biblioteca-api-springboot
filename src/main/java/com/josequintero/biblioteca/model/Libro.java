package com.josequintero.biblioteca.model;

import jakarta.persistence.*;

@Entity // Marca esta clase como entidad JPA: se mapea a una tabla de la BD
@Table(
        name = "libros", // Nombre real de la tabla en MySQL
        indexes = {
                // Índice para acelerar búsquedas/joins por autor_id (por ejemplo: "todos los libros de un autor")
                @Index(name = "idx_libros_autor_id", columnList = "autor_id")
        }
)
public class Libro {

    @Id // Clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID lo genera MySQL (AUTO_INCREMENT)
    private Long id;

    @Column(nullable = false, length = 255) // Columna obligatoria: VARCHAR(200) NOT NULL
    private String titulo;

    @Column(nullable = false, length = 50, unique = true) // ISBN obligatorio y único (UQ) a nivel BD
    private String isbn;

    /**
     * Relación muchos-a-uno:
     * - Muchos libros pueden pertenecer al mismo autor.
     * - Cada libro tiene un único autor.
     *
     * optional = false => la relación es obligatoria (Libro no puede existir sin Autor).
     * fetch = LAZY => el autor no se carga hasta que se necesita (mejor rendimiento).
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY) //N -> 1
    @JoinColumn(
            name = "autor_id",        // Nombre de la columna FK en la tabla "libros"
            nullable = false,         // autor_id no puede ser NULL (NOT NULL)
            referencedColumnName = "id" // Columna referenciada en la tabla "autores" (por defecto sería la PK)
    )
    /*
     * En JPA referenciamos con objetos:
     * - En Java: Libro tiene un Autor (objeto).
     * - En BD: libros.autor_id guarda el id del autor.
     *
     * Si referencedColumnName no se pone, JPA asume la PK (@Id) del Autor, o sea "id".
     */
    private Autor autor;

    // Constructor vacío obligatorio para JPA/Hibernate
    public Libro() {}

    public Libro(String titulo, String isbn, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
    }

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}