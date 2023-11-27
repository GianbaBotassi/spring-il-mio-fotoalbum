INSERT INTO photos (title, description, visible) VALUES('barche','Barche in mezzo al mare' , 1);
INSERT INTO photos (title, description, visible) VALUES('mare orizzonte','Una bella vista del mare' , 1);
INSERT INTO photos (title, description, visible) VALUES('montagna neve','Una cima innevata all alba' , 1);
INSERT INTO photos (title, description, visible) VALUES('montagne lago','Una bella vista di un lago montano' , 1);
INSERT INTO photos (title, description, visible) VALUES('climbing spiombo','Una bel tiro strapiombante' , 1);
INSERT INTO photos (title, description, visible) VALUES('artificial climbing','Arrampicata con friends e nuts' , 1);


INSERT INTO category (name, badge_color) VALUES('Montagna','brown');
INSERT INTO category (name, badge_color) VALUES('Mare','blue');
INSERT INTO category (name, badge_color) VALUES('Arrampicata','grey');
INSERT INTO category (name, badge_color) VALUES('Skialp','lightblue');
INSERT INTO category (name, badge_color) VALUES('Sun','orange');


INSERT INTO categories_photos (photo_id, category_id) VALUES(1, 2);
INSERT INTO categories_photos (photo_id, category_id) VALUES(1, 5);
INSERT INTO categories_photos (photo_id, category_id) VALUES(2, 2);
INSERT INTO categories_photos (photo_id, category_id) VALUES(2, 5);
INSERT INTO categories_photos (photo_id, category_id) VALUES(3, 1);
INSERT INTO categories_photos (photo_id, category_id) VALUES(3, 3);
INSERT INTO categories_photos (photo_id, category_id) VALUES(3, 4);
INSERT INTO categories_photos (photo_id, category_id) VALUES(4, 1);
INSERT INTO categories_photos (photo_id, category_id) VALUES(4, 3);
INSERT INTO categories_photos (photo_id, category_id) VALUES(5, 1);
INSERT INTO categories_photos (photo_id, category_id) VALUES(5, 3);
INSERT INTO categories_photos (photo_id, category_id) VALUES(6, 1);
INSERT INTO categories_photos (photo_id, category_id) VALUES(6, 3);
INSERT INTO categories_photos (photo_id, category_id) VALUES(6, 5);


INSERT INTO roles (name) VALUES('ADMIN');
INSERT INTO roles (name) VALUES('USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('cighi@gmail.com', 'Cighi', 'Nudo', '2023-11-20 10:35', '{noop}cighi');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('pinni@gmail.com', 'Pinni', 'Renata', '2023-11-20 10:35','{noop}pinni');

INSERT INTO roles_users (user_id, role_id) VALUES(1, 1);
INSERT INTO roles_users (user_id, role_id) VALUES(1, 2);
INSERT INTO roles_users (user_id, role_id) VALUES(2, 2);

INSERT INTO message (email, text_message, received) VALUES('luighi@gmail.com','Ciao! Ho apprezzato molto le foto sul tuo sito. Grazie per condividerle!','2023-06-22 11:11:11');
INSERT INTO message (email, text_message, received) VALUES('larenata@gmail.com','Le tue foto sono davvero ispiratrici. Spero di vederne presto altre!','2023-06-22 11:11:11');
INSERT INTO message (email, text_message, received) VALUES('pinni@gmail.com','Grazie per la condivisione delle tue foto. Hai un talento incredibile!','2023-06-22 11:11:11');