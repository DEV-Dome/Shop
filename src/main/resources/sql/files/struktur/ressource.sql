CREATE TABLE IF NOT EXISTS ressource (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    icon VARCHAR(250) NOT NULL,
    name VARCHAR(50) NOT NULL,
    beschreibung TEXT NOT NULL,
    typ ENUM('STANDART', 'AUFWERTER', 'WAHRUNG') NOT NULL,
    minimale_kosten DECIMAL(8,2) NOT NULL,
    maximale_kosten DECIMAL(8,2) NOT NULL,
    reinfolge INT NOT NULL
);