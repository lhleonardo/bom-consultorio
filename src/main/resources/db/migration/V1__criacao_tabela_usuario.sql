CREATE TABLE consultorio.public.usuario
(
    id               serial primary key,
    identificador    uuid                     not null,
    email            VARCHAR(320)             not null,
    senha            VARCHAR(255)             not null,
    ativo            boolean                  not null default true,
    administrador    boolean                  not null default false,
    email_verificado boolean                  not null default false,
    data_criacao     timestamp with time zone not null default now(),
    data_alteracao   timestamp with time zone
);

CREATE TABLE consultorio.public.usuario_empresa
(
    id           serial primary key,
    id_usuario   INT REFERENCES consultorio.public.usuario (id),
    id_empresa   INT REFERENCES consultorio.public.empresa (id),
    perfil       VARCHAR(100)             not null, -- master, admin, recepcao, financeiro, etc
    data_criacao timestamp with time zone not null default now()
);