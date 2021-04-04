CREATE TABLE public.cliente
(
    id bigserial NOT NULL,
    nome character varying(200),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.modulo
(
    id bigserial NOT NULL,
    nome character varying(200),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.ticket
(
    id bigserial NOT NULL,
    titulo character varying(200),
    codcliente bigint,
    codmodulo bigint,
    data_abertura timestamp without time zone,
    data_encerramento timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (codcliente)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (codmodulo)
        REFERENCES public.modulo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.cliente
    OWNER to postgres;
ALTER TABLE public.modulo
    OWNER to postgres;
ALTER TABLE public.ticket
    OWNER to postgres;