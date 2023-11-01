CREATE table empresa
(
    id            SERIAL PRIMARY KEY,
    identificador UUID         NOT NULL,
    razao_social  VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255) not null,

);