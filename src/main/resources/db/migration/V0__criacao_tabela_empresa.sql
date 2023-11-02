CREATE table consultorio.public.empresa
(
    id                 SERIAL PRIMARY KEY,
    identificador      UUID                     NOT NULL,
    razao_social       VARCHAR(255)             NOT NULL,
    nome_fantasia      VARCHAR(255)             not null,
    cnpj               varchar(14)              not null,
    inscricao_estadual varchar(40),
    site               varchar(255) null,
    logotipo           varchar(255) null,
    data_criacao       timestamp with time zone not null default now(),
    data_alteracao     timestamp with time zone
);