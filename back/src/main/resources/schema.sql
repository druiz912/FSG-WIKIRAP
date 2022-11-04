CREATE TABLE IF NOT EXISTS person(
    id integer PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname1 VARCHAR(50) NOT NULL,
    surname2 VARCHAR(50),
    age integer NOT NULL,
    date_of_birth VARCHAR(10),
    origen VARCHAR(100),
    height float,
    occupation VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS artist
(
    id integer PRIMARY KEY NOT NULL,
    id_person integer NOT NULL,
    apodo VARCHAR(80) NOT NULL,
    url_image VARCHAR(120) NOT NULL,
    details VARCHAR(255) NOT NULL,
    id_album integer NOT NULL,
    CONSTRAINT FK_artist_person_id FOREIGN KEY (id)
      REFERENCES person (id) ON DELETE SET NULL,
    CONSTRAINT FK_artist_album_id FOREIGN KEY (id)
      REFERENCES album (id) ON DELETE SET NULL
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS album
(
    id integer PRIMARY KEY NOT NULL,
    id_artist integer NOT NULL,
    id_song integer NOT NULL,
    url_title_page VARCHAR(120) NOT NULL,
    title VARCHAR(150) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    departure_date VARCHAR(10),
    CONSTRAINT FK_album_artist_id FOREIGN KEY (id)
      REFERENCES artist (id) ON DELETE SET NULL,
    CONSTRAINT FK_album_song_id FOREIGN KEY (id)
      REFERENCES song (id) ON DELETE SET NULL
)ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS song
(
    id integer PRIMARY KEY NOT NULL,
    title VARCHAR(150) NOT NULL,
    duration VARCHAR(10) NOT NULL,
    description VARCHAR(255),
    departure_date VARCHAR(10),
    id_album integer NOT NULL,
    CONSTRAINT FK_song_album_id FOREIGN KEY (id)
      REFERENCES album (id) ON DELETE SET NULL
)ENGINE = INNODB;

