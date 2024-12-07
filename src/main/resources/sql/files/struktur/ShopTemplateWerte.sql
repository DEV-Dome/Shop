CREATE TABLE IF NOT EXISTS shop_template_werte (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    template INT NOT NULL,
    schlussel VARCHAR(512) NOT NULL,
    inhalt TEXT NOT NULL,
    variable_1 TEXT DEFAULT "",
    FOREIGN KEY (template) REFERENCES shop_template(id)
);