CREATE TABLE IF NOT EXISTS shop_ressource (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shop INT NOT NULL,
    ressource INT NOT NULL,
    menge BIGINT,
    FOREIGN KEY (shop) REFERENCES shop(id),
    FOREIGN KEY (ressource) REFERENCES ressource(id)
);