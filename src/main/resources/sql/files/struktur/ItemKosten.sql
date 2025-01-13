CREATE TABLE IF NOT EXISTS item_kosten (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item INT NOT NULL,
    ressource INT NOT NULL,
    menge INT NOT NULL,

    FOREIGN KEY (item) REFERENCES item(id),
    FOREIGN KEY (ressource) REFERENCES ressource(id)
);