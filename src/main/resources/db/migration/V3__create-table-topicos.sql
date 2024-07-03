CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    autor_id BIGINT,
    curso_id BIGINT,
    respostas_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);