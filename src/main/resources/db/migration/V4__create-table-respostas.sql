CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    topico_id BIGINT,
    autor_id BIGINT,

    FOREIGN KEY (topico_id) REFERENCES topicos(id),
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);