ALTER TABLE topicos
ADD CONSTRAINT fk_respostas_id
FOREIGN KEY (respostas_id) REFERENCES respostas(id);