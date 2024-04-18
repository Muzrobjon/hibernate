CREATE TABLE public.author (
    id integer NOT NULL,
    name character varying
);

ALTER TABLE public.author OWNER TO postgres;

CREATE SEQUENCE public.author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.author_id_seq OWNER TO postgres;

ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;

CREATE TABLE public.book (
    id integer NOT NULL,
    title character varying,
    "authorId" integer
);

CREATE SEQUENCE public.book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.book_id_seq OWNER TO postgres;

ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;

ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);

ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);

SELECT pg_catalog.setval('public.author_id_seq', 1, false);

SELECT pg_catalog.setval('public.book_id_seq', 1, false

ALTER TABLE public.book OWNER TO postgres;

CREATE SEQUENCE public.book_id_seq);

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.book
    ADD CONSTRAINT "authorFk" FOREIGN KEY ("authorId") REFERENCES public.author(id) NOT VALID;
