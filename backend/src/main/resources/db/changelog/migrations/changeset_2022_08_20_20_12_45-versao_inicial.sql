-- liquibase formatted sql
-- changeset sombriks:changeset_2022_08_20_20_12_45-versao_inicial.sql

create table currencyes
(
    id   integer             not null primary key auto_increment,
    nome varchar(255) unique not null
);

create table quotations
(
    id         integer      not null primary key auto_increment,
    currencyes_id integer      not null,
    nome       varchar(255) not null,
    constraint fk_quotations_currencyes_id foreign key (currencyes_id) references currencyes (id)
);

create table estados_documento
(
    id   integer             not null primary key auto_increment,
    nome varchar(255) unique not null
);

create table documentos
(
    id                   integer      not null primary key auto_increment,
    quotations_id            integer      not null,
    estados_documento_id integer      not null,
    titulo               varchar(255) not null,
    constraint fk_documentos_quotations_id foreign key (quotations_id) references quotations (id),
    constraint fk_documentos_estados_documento_id foreign key (estados_documento_id) references estados_documento (id)
);

create table historico_documentos
(
    id            integer      not null primary key auto_increment,
    documentos_id integer      not null,
    data          timestamp    not null default now(),
    mudanca       varchar(255) not null,
    constraint fk_historico_documentos_documentos_id foreign key (documentos_id) references documentos (id)
);

-- rollback drop table historico_documentos;
-- rollback drop table documentos;
-- rollback drop table estados_documento;
-- rollback drop table quotations;
-- rollback drop table currencyes;
