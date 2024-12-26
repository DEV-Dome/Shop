CREATE TABLE IF NOT EXISTS shop_handwerks_aufgaben (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    gueltig_bis DATETIME NOT NULL,
    erledigt BOOL NOT NULL DEFAULT 0,

    FOREIGN KEY (shop) REFERENCES shop(id)
);