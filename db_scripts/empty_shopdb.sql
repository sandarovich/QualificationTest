
CREATE DATABASE "shopDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "shopDb" OWNER TO postgres;


CREATE TABLE product (
    id bigint NOT NULL,
    name character varying(250),
    price numeric
);


ALTER TABLE product OWNER TO postgres;


CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

ALTER SEQUENCE product_id_seq OWNED BY product.id;


CREATE TABLE purchase (
    id bigint NOT NULL,
    date timestamp without time zone
);


ALTER TABLE purchase OWNER TO postgres;

CREATE SEQUENCE purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE purchase_id_seq OWNER TO postgres;

ALTER SEQUENCE purchase_id_seq OWNED BY purchase.id;

CREATE TABLE purchase_item (
    id bigint NOT NULL,
    quantity numeric,
    product_id bigint,
    purchase_id bigint
);


ALTER TABLE purchase_item OWNER TO postgres;

CREATE SEQUENCE purchase_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE purchase_item_id_seq OWNER TO postgres;

ALTER SEQUENCE purchase_item_id_seq OWNED BY purchase_item.id;

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);

ALTER TABLE ONLY purchase ALTER COLUMN id SET DEFAULT nextval('purchase_id_seq'::regclass);

ALTER TABLE ONLY purchase_item ALTER COLUMN id SET DEFAULT nextval('purchase_item_id_seq'::regclass);


INSERT INTO product (id, name, price) VALUES (20, 'Juice', 50);
INSERT INTO product (id, name, price) VALUES (21, 'Flat', 1000000);
INSERT INTO product (id, name, price) VALUES (22, 'Icecream', 10);
INSERT INTO product (id, name, price) VALUES (23, 'Car', 200000);
INSERT INTO product (id, name, price) VALUES (24, 'Nuts', 2);
INSERT INTO product (id, name, price) VALUES (25, 'Pop-Corn', 25);
INSERT INTO product (id, name, price) VALUES (26, 'Robot', 900);
INSERT INTO product (id, name, price) VALUES (27, 'Milk', 10.59);

SELECT pg_catalog.setval('product_id_seq', 27, true);

INSERT INTO purchase (id, date) VALUES (66, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (67, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (68, '2016-03-23 00:00:00');
INSERT INTO purchase (id, date) VALUES (69, '2015-06-22 00:00:00');
INSERT INTO purchase (id, date) VALUES (70, '2015-07-19 00:00:00');
INSERT INTO purchase (id, date) VALUES (71, '2016-05-23 00:41:41.729');

SELECT pg_catalog.setval('purchase_id_seq', 71, true);

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


SELECT pg_catalog.setval('purchase_item_id_seq', 173, true);

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);

ALTER TABLE ONLY purchase
    ADD CONSTRAINT purchase_id_pk PRIMARY KEY (id);


ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT purchase_item_id_pk_ PRIMARY KEY (id);


ALTER TABLE ONLY product
    ADD CONSTRAINT unique_name UNIQUE (name);


CREATE INDEX fki_product_id_fk ON purchase_item USING btree (product_id);


CREATE INDEX fki_purchase_id_fk ON purchase_item USING btree (purchase_id);

ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE ONLY purchase_item
    ADD CONSTRAINT purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES purchase(id);


