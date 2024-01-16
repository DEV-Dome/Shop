CREATE TABLE IF NOT EXISTS shop (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    template INT NOT NULL,
    owner VARCHAR(36) NOT NULL,
    shop_level VARCHAR(16) NOT NULL,
    shop_ordner VARCHAR(500),
    shop_zones INT NOT NULL,
    FOREIGN KEY (template) REFERENCES shop_template(id)
);