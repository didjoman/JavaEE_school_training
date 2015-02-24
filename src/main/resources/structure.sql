CREATE SEQUENCE id_seq;
CREATE TABLE bibliographie (
id number(6) DEFAULT id_seq.nextval PRIMARY KEY,
auteur varchar(100) NOT NULL,
titre varchar(100) NOT NULL
);
