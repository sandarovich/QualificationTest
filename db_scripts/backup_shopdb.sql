--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-05-23 00:53:04 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "shopDb";
--
-- TOC entry 2172 (class 1262 OID 41051)
-- Name: shopDb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "shopDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "shopDb" OWNER TO postgres;

\connect "shopDb"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12395)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 41052)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
    id bigint NOT NULL,
    name character varying(250),
    price numeric
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 41058)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 182
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- TOC entry 183 (class 1259 OID 41060)
-- Name: purchase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE purchase (
    id bigint NOT NULL,
    date timestamp without time zone
);


ALTER TABLE purchase OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 41063)
-- Name: purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE purchase_id_seq OWNER TO postgres;

--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 184
-- Name: purchase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE purchase_id_seq OWNED BY purchase.id;


--
-- TOC entry 185 (class 1259 OID 41065)
-- Name: purchase_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE purchase_item (
    id bigint NOT NULL,
    quantity numeric,
    product_id bigint,
    purchase_id bigint
);


ALTER TABLE purchase_item OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 41071)
-- Name: purchase_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE purchase_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE purchase_item_id_seq OWNER TO postgres;

--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 186
-- Name: purchase_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE purchase_item_id_seq OWNED BY purchase_item.id;


--
-- TOC entry 2033 (class 2604 OID 41073)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- TOC entry 2034 (class 2604 OID 41074)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase ALTER COLUMN id SET DEFAULT nextval('purchase_id_seq'::regclass);


--
-- TOC entry 2035 (class 2604 OID 41075)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase_item ALTER COLUMN id SET DEFAULT nextval('purchase_item_id_seq'::regclass);


--
-- TOC entry 2162 (class 0 OID 41052)
-- Dependencies: 181
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO product (id, name, price) VALUES (20, 'Juice', 50);
INSERT INTO product (id, name, price) VALUES (21, 'Flat', 1000000);
INSERT INTO product (id, name, price) VALUES (22, 'Icecream', 10);
INSERT INTO product (id, name, price) VALUES (23, 'Car', 200000);
INSERT INTO product (id, name, price) VALUES (24, 'Nuts', 2);
INSERT INTO product (id, name, price) VALUES (25, 'Pop-Corn', 25);
INSERT INTO product (id, name, price) VALUES (26, 'Robot', 900);
INSERT INTO product (id, name, price) VALUES (27, 'Milk', 10.59);


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 182
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 27, true);


--
-- TOC entry 2164 (class 0 OID 41060)
-- Dependencies: 183
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO purchase (id, date) VALUES (66, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (67, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (68, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (69, '2015-06-22 00:00:00');
INSERT INTO purchase (id, date) VALUES (70, '2015-07-19 00:00:00');
INSERT INTO purchase (id, date) VALUES (71, '2016-05-23 00:41:41.729');


--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 184
-- Name: purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('purchase_id_seq', 71, true);


--
-- TOC entry 2166 (class 0 OID 41065)
-- Dependencies: 185
-- Data for Name: purchase_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (161, 2, 20, 66);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (162, 1, 21, 66);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (163, 2, 20, 67);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (164, 1, 21, 67);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (165, 7, 22, 68);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (166, 1, 23, 68);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (167, 9, 24, 69);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (168, 2, 25, 69);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (169, 9, 24, 70);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (170, 2, 25, 70);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (171, 10, 22, 71);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (172, 4, 26, 71);
INSERT INTO purchase_item (id, quantity, product_id, purchase_id) VALUES (173, 1, 27, 71);


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 186
-- Name: purchase_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('purchase_item_id_seq', 173, true);


--
-- TOC entry 2037 (class 2606 OID 41077)
-- Name: product_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 41079)
-- Name: purchase_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase
    ADD CONSTRAINT purchase_id_pk PRIMARY KEY (id);


--
-- TOC entry 2045 (class 2606 OID 41081)
-- Name: purchase_item_id_pk_; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT purchase_item_id_pk_ PRIMARY KEY (id);


--
-- TOC entry 2039 (class 2606 OID 41083)
-- Name: unique_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT unique_name UNIQUE (name);


--
-- TOC entry 2042 (class 1259 OID 41084)
-- Name: fki_product_id_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_product_id_fk ON purchase_item USING btree (product_id);


--
-- TOC entry 2043 (class 1259 OID 41085)
-- Name: fki_purchase_id_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_purchase_id_fk ON purchase_item USING btree (purchase_id);


--
-- TOC entry 2046 (class 2606 OID 41086)
-- Name: product_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2047 (class 2606 OID 41091)
-- Name: purchase_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES purchase(id);


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-05-23 00:53:04 EEST

--
-- PostgreSQL database dump complete
--

