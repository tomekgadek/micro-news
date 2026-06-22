CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(150),
    surname VARCHAR(200),
    city VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE login (
    login VARCHAR(100) UNIQUE,
    pass VARCHAR(100),
    id_user INT UNIQUE,
    FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE section (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150),
    PRIMARY KEY (id)
);

CREATE TABLE article (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100),
    content TEXT,
    date_art DATETIME,
    id_section INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_section) REFERENCES section(id)
);

CREATE TABLE image (
    id INT NOT NULL AUTO_INCREMENT,
    href VARCHAR(100),
    title VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE art_user (
    id_article INT,
    id_user INT,
    date_read DATETIME,
    FOREIGN KEY (id_article) REFERENCES article(id),
    FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE art_img (
    id_article INT,
    id_image INT,
    FOREIGN KEY (id_article) REFERENCES article(id),
    FOREIGN KEY (id_image) REFERENCES image(id)
);
