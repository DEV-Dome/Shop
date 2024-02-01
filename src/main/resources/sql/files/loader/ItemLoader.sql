INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_AXE', 'Äxte', 'Baue und erforsche die mächtigsten Äxte', 1);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Anfänger Axt I', 'Diese Axt wurde von einem Schreinerlehrling \n hergestellt und dient nun dem Zweck, \n das Schreinerhandwerk zu erlernen.', 1, 'STANDART', 3, 10, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (1, 1, 3);

        INSERT INTO item_werte (item, wert, value) VALUES (1, "shop_xp", 1);
        INSERT INTO item_werte (item, wert, value) VALUES (1, "kategorie_xp", 5);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Axt von Roland', 'Man munkelt diese Art von Äxten, gehört nicht\n nur einen Roland. Nein er soll diese \n erfunden haben.', 2, 'ITEM', NULL, NULL, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (2, 1, 5);

        INSERT INTO item_werte (item, wert, value) VALUES (2, "shop_xp", 1);
        INSERT INTO item_werte (item, wert, value) VALUES (2, "kategorie_xp", 7);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Spielzeug Axt', 'Kinder nutzen diese Axt oft zum Spielen.\n Weil Sie so harmlos ist, geben Eltern \n Sie gerne ihren Kindern.', 1, 'STANDART', 4, 12, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (3, 1, 2);

        INSERT INTO item_werte (item, wert, value) VALUES (3, "shop_xp", 1);
        INSERT INTO item_werte (item, wert, value) VALUES (3, "kategorie_xp", 3);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Anfänger Axt II', 'Diese Axt wurde von einem \n Schreinerlehrling hergestellt. Welcher schon \n etwas Erfahrung gesammelt hat. ', 1, 'STANDART', 5, 10, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (4, 1, 5);

        INSERT INTO item_werte (item, wert, value) VALUES (4, "shop_xp", 1);
        INSERT INTO item_werte (item, wert, value) VALUES (4, "kategorie_xp", 5);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Axt des Holzfällers Lehrlings', 'Dieser klassischen Axttype wird häufig \n von Holzfällern Lehrlingen genutzt.', 1, 'STANDART', 6, 15, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (5, 1, 8);

        INSERT INTO item_werte (item, wert, value) VALUES (5, "shop_xp", 2);
        INSERT INTO item_werte (item, wert, value) VALUES (5, "kategorie_xp", 6);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'STONE_AXE', 'Reststein Axt', 'Diese Axt wird aus Stein Resten gefertigt und ist \n deshalb nicht besonders robust,', 1, 'STANDART', 7, 15, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 2, 1);

        INSERT INTO item_werte (item, wert, value) VALUES (6, "shop_xp", 3);
        INSERT INTO item_werte (item, wert, value) VALUES (6, "kategorie_xp", 8);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'STONE_AXE', 'Granit Axt', 'Eine Axt aus Granit, fragt sich nur, ob Sie \n auch Holz hacken kann?', 1, 'STANDART', 8, 17, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 2, 3);

        INSERT INTO item_werte (item, wert, value) VALUES (7, "shop_xp", 3);
        INSERT INTO item_werte (item, wert, value) VALUES (7, "kategorie_xp", 8);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'STONE_AXE', 'Anfänger Axt III', 'Ein Lehrling im letzten Jahr hat Sie gefertigt. \n Deshalb diese Axt so perfekt aus!', 1, 'STANDART', 9, 18, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 2, 5);

        INSERT INTO item_werte (item, wert, value) VALUES (8, "shop_xp", 2);
        INSERT INTO item_werte (item, wert, value) VALUES (8, "kategorie_xp", 9);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, freischalt_typ, freischalt_item, freischalt_menge, reinfolge) VALUES ('1', 'STONE_AXE', 'Weiße Axt', 'Diese Axt aus Stein wurde ein \n einfach weiß angemalt.', 1, 'STANDART', 10, 20, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 2, 4);

        INSERT INTO item_werte (item, wert, value) VALUES (9, "shop_xp", 3);
        INSERT INTO item_werte (item, wert, value) VALUES (9, "kategorie_xp", 9);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Axt des Holzfällers gesellen', 'Dieser klassischen Axttype wird \n häufig von Holzfällern gesellen genutzt.', 2, 10);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 2, 8);

        INSERT INTO item_werte (item, wert, value) VALUES (10, "shop_xp", 3);
        INSERT INTO item_werte (item, wert, value) VALUES (10, "kategorie_xp", 10);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_SWORD', 'Schwerter', 'Baue und erforsche die mächtigsten Schwerter', 1);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_SHOVEL', 'Schaufeln', 'Baue und erforsche die mächtigsten Schaufeln', 1);
