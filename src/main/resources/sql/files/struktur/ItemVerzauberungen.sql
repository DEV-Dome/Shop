CREATE TABLE IF NOT EXISTS item_verzauberungen (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(512),
    beschreibung TEXT,
    item_kategorie INT,

    FOREIGN KEY (item_kategorie) REFERENCES item_kategorie(id)
);