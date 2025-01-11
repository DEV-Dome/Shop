CREATE TABLE IF NOT EXISTS shop_item (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    item INT NOT NULL,
    item_seltenheit INT NOT NULL,
    ausgestellt INT DEFAULT NULL,

    FOREIGN KEY (shop) REFERENCES shop(id),
    FOREIGN KEY (item_seltenheit) REFERENCES item_seltenheit(id),
    FOREIGN KEY (item) REFERENCES item(id)
);