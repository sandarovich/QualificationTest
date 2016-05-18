INSERT INTO product (name, price)
VALUES ('Icecream', 10);
INSERT INTO product (name, price)
VALUES ('TV', 4500);
INSERT INTO product (name, price)
VALUES ('Car', 200000);
INSERT INTO product (name, price)
VALUES ('Flat', 2000000);

Insert into purchase (date)
VALUES ('2016-2-1' :: timestamp);

Insert into purchase (date)
VALUES ('2016-2-2' :: timestamp);

Insert into purchase (date)
VALUES ('2016-3-4' :: timestamp);

Insert into purchase (date)
VALUES ('2016-5-5' :: timestamp);

Insert into purchase (date)
VALUES ('2016-5-5' :: timestamp);

Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (3, 1, 1);
Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (3, 2, 1);

Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (1, 4, 2);

Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (4, 3, 3);

Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (2, 3, 4);

Insert into purchase_item (quantity, product_id, purchase_id)
VALUES (3, 3, 4);








