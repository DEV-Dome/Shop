CREATE TABLE IF NOT EXISTS shop_item (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    item INT NOT NULL,
    ausgestellt INT DEFAULT NULL,

    FOREIGN KEY (shop) REFERENCES shop(id),
    FOREIGN KEY (item) REFERENCES item(id),
    FOREIGN KEY (ausgestellt) REFERENCES shop_item_halter(id)
);