CREATE TABLE usuario
(
    id              serial primary key,
    identificador   uuid                     not null,
    email           VARCHAR(320)             not null,
    password_hashed VARCHAR(255)             not null,
    perfil          VARCHAR(100)             not null, -- master, administrador, financeiro, recepcao, profissional
    ativo           boolean                  not null default true,
    id_empresa      INT REFERENCES consultorio.public.empresa (id),
    data_criacao    timestamp with time zone not null default now(),
    data_alteracao  timestamp with time zone
);