DROP TABLE IF EXISTS CATEGORIA;
DROP TABLE IF EXISTS CURSO;

CREATE TABLE CATEGORIA (
    CODIGO INTEGER NOT NULL,
    DESCRICAO VARCHAR(255),
    PRIMARY KEY(CODIGO)
);

CREATE TABLE CURSO (
    ID INTEGER AUTO_INCREMENT,
    DESCRICAO VARCHAR(50) NOT NULL,
    DATA_INICIO DATE NOT NULL,
    DATA_TERMINO DATE NOT NULL,
    QNT_ALUNOS INTEGER,
    CATEGORIA_CODIGO INTEGER NOT NULL,
    PRIMARY KEY(ID)
);

ALTER TABLE CATEGORIA
ADD CONSTRAINT UK_CATEGORIA_1 UNIQUE(DESCRICAO);

ALTER TABLE CURSO
ADD CONSTRAINT UK_CURSO_1 UNIQUE (DESCRICAO);

ALTER TABLE CURSO
ADD CONSTRAINT FK_CURSO_1
FOREIGN KEY (CATEGORIA_CODIGO)
REFERENCES CATEGORIA;

