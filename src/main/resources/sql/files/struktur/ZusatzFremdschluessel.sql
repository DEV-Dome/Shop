ALTER TABLE shop_item ADD CONSTRAINT fk_ausgestellt FOREIGN KEY (ausgestellt) REFERENCES shop_item_halter(id);