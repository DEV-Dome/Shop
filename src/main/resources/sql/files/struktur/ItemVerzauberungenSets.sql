CREATE TABLE IF NOT EXISTS item_verzauberungen_sets (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(512),
    beschreibung TEXT,
    verbesserung_1 TEXT,
    verbesserung_2 TEXT,
    ressource INT NOT NULL,

    custom_model_data_helm VARCHAR(150) NOT NULL,
    custom_model_data_ruestung VARCHAR(150) NOT NULL,
    custom_model_data_hose VARCHAR(150) NOT NULL,
    custom_model_data_schuhe VARCHAR(150) NOT NULL,

    FOREIGN KEY (ressource) REFERENCES ressource(id)
);