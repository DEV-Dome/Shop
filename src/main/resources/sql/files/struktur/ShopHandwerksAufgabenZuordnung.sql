CREATE TABLE IF NOT EXISTS shop_handwerks_aufgaben_zuordnung (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    aufgabe INT NOT NULL,
    aufgaben_item INT NOT NULL,

    FOREIGN KEY (aufgabe) REFERENCES shop_handwerks_aufgaben(id),
    FOREIGN KEY (aufgaben_item) REFERENCES shop_handwerks_aufgaben_item(id)
);