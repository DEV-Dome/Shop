CREATE TABLE IF NOT EXISTS shop_item_vorlage (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    item INT NOT NULL,
    hergestellt INT NOT NULL DEFAULT 0,
    freigeschaltet ENUM("JA", "NEIN") NOT NULL DEFAULT "NEIN",

    FOREIGN KEY (item) REFERENCES item(id),
    FOREIGN KEY (shop) REFERENCES shop(id)
);