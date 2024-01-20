CREATE TABLE IF NOT EXISTS shop_template_zonen (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    template INT NOT NULL,
    locationen_1 TEXT NOT NULL,
    locationen_2 TEXT NOT NULL,
    anordnung INT,
    FOREIGN KEY (template) REFERENCES shop_template(id)
);