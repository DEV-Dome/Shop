CREATE TABLE IF NOT EXISTS item (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_kategorie INT NOT NULL,
    item_stufe INT NOT NULL,
    icon VARCHAR(512) NOT NULL,
    name VARCHAR(512) NOT NULL,
    beschreibung TEXT NOT NULL,
    reinfolge INT NOT NULL,
    FOREIGN KEY (item_kategorie) REFERENCES item_kategorie(id),
    FOREIGN KEY (item_stufe) REFERENCES item_stufen(id)
);