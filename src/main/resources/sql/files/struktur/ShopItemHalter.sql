CREATE TABLE IF NOT EXISTS shop_item_halter (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    typ ENUM("ITEM_FRAME", "ARMOR_STAND") NOT NULL,
    location TEXT NOT NULL,
    item_1 INT DEFAULT NULL,
    item_2 INT DEFAULT NULL,
    item_3 INT DEFAULT NULL,
    item_4 INT DEFAULT NULL,
    item_5 INT DEFAULT NULL,
    item_6 INT DEFAULT NULL,

    FOREIGN KEY (shop) REFERENCES shop(id),
    FOREIGN KEY (item_1) REFERENCES shop_item(id),
    FOREIGN KEY (item_2) REFERENCES shop_item(id),
    FOREIGN KEY (item_3) REFERENCES shop_item(id),
    FOREIGN KEY (item_4) REFERENCES shop_item(id),
    FOREIGN KEY (item_5) REFERENCES shop_item(id),
    FOREIGN KEY (item_6) REFERENCES shop_item(id)
);
