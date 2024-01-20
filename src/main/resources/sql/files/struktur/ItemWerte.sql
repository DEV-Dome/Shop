CREATE TABLE IF NOT EXISTS item_werte (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item INT NOT NULL,
    wert VARCHAR(512) NOT NULL,
    value TEXT NOT NULL,
    FOREIGN KEY (item) REFERENCES item(id)
);