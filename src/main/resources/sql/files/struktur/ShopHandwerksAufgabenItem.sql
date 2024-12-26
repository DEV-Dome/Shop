CREATE TABLE IF NOT EXISTS shop_handwerks_aufgaben_item (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item INT NOT NULL,
    menge INT NOT NULL,
    fortschritt  INT NOT NULL DEFAULT 0,
    belohnung VARCHAR(512) NOT NULL,
    belohnung_menge INT NOT NULL,


    FOREIGN KEY (item) REFERENCES item(id)
);