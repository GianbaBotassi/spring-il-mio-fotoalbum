INSERT INTO roles (name) VALUES('SUPERADMIN');
INSERT INTO roles (name) VALUES('ADMIN');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('cighi@gmail.com', 'Cighi', 'Nudo', '2023-11-20 10:35', '{noop}cighi');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('mare@gmail.com', 'Mare', 'Profondo', '2023-11-20 10:35','{noop}mare');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('montagna@gmail.com', 'Montagna', 'Alta', '2023-11-20 10:35','{noop}montagna');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('climbing@gmail.com', 'Climbing', 'Everyday', '2023-11-20 10:35','{noop}climbing');

INSERT INTO roles_users (user_id, role_id) VALUES(1, 1);
INSERT INTO roles_users (user_id, role_id) VALUES(1, 2);
INSERT INTO roles_users (user_id, role_id) VALUES(2, 2);
INSERT INTO roles_users (user_id, role_id) VALUES(3, 2);
INSERT INTO roles_users (user_id, role_id) VALUES(4, 2);

INSERT INTO photos (title, description, visible ,user_id) VALUES('barche','Barche in mezzo al mare' , 1, 2);
INSERT INTO photos (title, description, visible ,user_id) VALUES('mare orizzonte','Una bella vista del mare' , 1, 2);
INSERT INTO photos (title, description, visible ,user_id) VALUES('montagna neve','Una cima innevata all alba' , 1, 3);
INSERT INTO photos (title, description, visible ,user_id) VALUES('montagne lago','Una bella vista di un lago montano' , 1, 3);
INSERT INTO photos (title, description, visible ,user_id) VALUES('climbing spiombo','Una bel tiro strapiombante' , 1, 4);
INSERT INTO photos (title, description, visible ,user_id) VALUES('artificial climbing','Arrampicata con friends e nuts' , 1, 4);


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



INSERT INTO message (email, text_message, received) VALUES('luighi@gmail.com','Ciao! Ho apprezzato molto le foto sul tuo sito. Grazie per condividerle!','2023-06-22 11:11:11');
INSERT INTO message (email, text_message, received) VALUES('larenata@gmail.com','Le tue foto sono davvero ispiratrici. Spero di vederne presto altre!','2023-06-22 11:11:11');
INSERT INTO message (email, text_message, received) VALUES('pinni@gmail.com','Grazie per la condivisione delle tue foto. Hai un talento incredibile!','2023-06-22 11:11:11');