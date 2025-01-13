CREATE TABLE IF NOT EXISTS item_seltenheit (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(512),
    farbe CHAR(2),
    aufwertung_menge INT DEFAULT 2,
    aufwertung_ressource INT,

    FOREIGN KEY (aufwertung_ressource) REFERENCES ressource(id)
);