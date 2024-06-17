CREATE TABLE IF NOT EXISTS shop_item_werte (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item INT NOT NULL,
    schlussel VARCHAR(512) NOT NULL,
    inhalt TEXT NOT NULL,
    FOREIGN KEY (item) REFERENCES shop_item(id)
);