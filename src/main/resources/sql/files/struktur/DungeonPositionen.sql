CREATE TABLE IF NOT EXISTS dungeon_positionen (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dungeon INT NOT NULL,
    wert VARCHAR(512) NOT NULL,
    value TEXT NOT NULL,
    FOREIGN KEY (dungeon) REFERENCES dungeon(id)
);