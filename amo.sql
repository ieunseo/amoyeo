DROP TABLE IF EXISTS buying_user;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS group_buying;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS require_buying;

DROP TABLE IF EXISTS notices;
DROP TABLE IF EXISTS users;
use spring;
CREATE TABLE users (
   user_no INT PRIMARY KEY AUTO_INCREMENT,
   user_id VARCHAR(255) NOT NULL,
   user_pwd VARCHAR(255) NOT NULL,
   user_phone VARCHAR(255),
   user_apartment VARCHAR(255),
   user_apart_num VARCHAR(255),
   user_name VARCHAR(255),
   user_rights VARCHAR(255) DEFAULT 'N'
) ENGINE=INNODB;
INSERT INTO users (
   user_id,
   user_pwd,
   user_phone,
   user_apartment,
   user_apart_num,
   user_name, user_rights
) VALUES
('user123', 'password123', '010-1234-5678', '삼육대학교 에스라관', '101', '이재혁', 'N'),
('user456', 'password456', '010-2345-6789', '삼육대학교 제1실습관', '402', '오송은','Y'),
('user789', 'password789', '010-3456-7890', '삼육대학교 제1실습관', '103', '심선우','N');

CREATE TABLE group_buying(
   buying_no INT PRIMARY KEY AUTO_INCREMENT,
   buying_text VARCHAR(255),
   buying_item VARCHAR(255),
   buying_price INT,
   buying_quality VARCHAR(255),
   user_no INT,
   FOREIGN KEY(user_no) REFERENCES users(user_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=INNODB;

CREATE TABLE require_buying(
   require_no INT PRIMARY KEY AUTO_INCREMENT,
   require_text VARCHAR(255),
   require_item VARCHAR(255),
   require_price INT,
   require_quality VARCHAR(255),
   user_no INT,
   FOREIGN KEY(user_no) REFERENCES users(user_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=INNODB;

CREATE TABLE likes(
   like_id INT PRIMARY KEY AUTO_INCREMENT,
   user_no INT,
   require_no INT,
   UNIQUE KEY unique_user_require (user_no, require_no),
   FOREIGN KEY(user_no) REFERENCES users(user_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
   FOREIGN KEY(require_no) REFERENCES require_buying(require_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=INNODB;

CREATE TABLE image(
   img_no INT PRIMARY KEY AUTO_INCREMENT,
   img_origin_filename VARCHAR(255),
   img_name VARCHAR(255),
   img_path VARCHAR(255),
   buying_no INT,
   FOREIGN KEY(buying_no) REFERENCES group_buying(buying_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=INNODB;

CREATE TABLE buying_user(
   buying_no INT,
   user_no INT,
   buying_quantity INT,
   buying_date DATE,
   PRIMARY KEY(buying_no, user_no),
   FOREIGN KEY(buying_no) REFERENCES group_buying(buying_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
   FOREIGN KEY(user_no) REFERENCES users(user_no)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=INNODB;
-- Insert users data

select * from users;
-- Insert group buying data

select * from group_buying;
-- Insert require buying data
INSERT INTO require_buying (
   require_text,
   require_item,
   require_price,
   require_quality,
   user_no
) VALUES
('맛 좋은 고구마 사가세요~!', '고구마', 5000, 'A', 1),
('맛 좋은 고구마 사가세요~!', '고구마', 6000, 'B', 2),
('맛 좋은 고구마 사가세요~!', '고구마', 7000, 'C', 3);
select * from require_buying;
-- Insert likes data
INSERT INTO likes (
   user_no,
   require_no
) VALUES
(1, 1),
(2, 2),
(3, 3);
CREATE TABLE notices (
    notice_id INT AUTO_INCREMENT PRIMARY KEY,
    notice_title VARCHAR(255) NOT NULL,
    notice_content TEXT NOT NULL,
    user_no INT NOT NULL,
    author_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_no) REFERENCES users(user_no)
);
select * from users;
select * from group_buying;
select * from image;
select * from buying_user;
select * from notices;
