INSERT INTO photos (title, description, picture, visible) VALUES('barche','Barche in mezzo al mare' , 'https://plus.unsplash.com/premium_photo-1680964717433-bc078a238170?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);
INSERT INTO photos (title, description, picture, visible) VALUES('mare orizzonte','Una bella vista del mare' , 'https://images.unsplash.com/photo-1497290756760-23ac55edf36f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);
INSERT INTO photos (title, description, picture, visible) VALUES('montagna neve','Una cima innevata all alba' , 'https://plus.unsplash.com/premium_photo-1673254928968-b6513f32d43b?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);
INSERT INTO photos (title, description, picture, visible) VALUES('montagne lago','Una bella vista di un lago montano' , 'https://images.unsplash.com/photo-1465056836041-7f43ac27dcb5?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);
INSERT INTO photos (title, description, picture, visible) VALUES('climbing spiombo','Una bel tiro strapiombante' , 'https://plus.unsplash.com/premium_photo-1661888350177-48a9efbf6985?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);
INSERT INTO photos (title, description, picture, visible) VALUES('artificial climbing','Arrampicata con friends e nuts' , 'https://images.unsplash.com/photo-1586627161720-ee2849303aee?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);


INSERT INTO category (name) VALUES('Montagna');
INSERT INTO category (name) VALUES('Mare');
INSERT INTO category (name) VALUES('Arrampicata');
INSERT INTO category (name) VALUES('Skialp');
INSERT INTO category (name) VALUES('Sun');


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