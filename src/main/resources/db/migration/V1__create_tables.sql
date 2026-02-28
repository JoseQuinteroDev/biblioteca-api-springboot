CREATE TABLE autores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    pais VARCHAR(80),
    PRIMARY KEY (id)
);

CREATE TABLE libros (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_libros_isbn UNIQUE (isbn),
    CONSTRAINT fk_libros_autor
        FOREIGN KEY (autor_id) REFERENCES autores(id)
);