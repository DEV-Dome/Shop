INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_AXE', 'Äxte', 'Baue und erforsche die mächtigsten Äxte', 1);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Anfänger Axt I', 'Diese Axt wurde von einem Schreinerlehrling \n hergestellt und dient nun dem Zweck, \n das Schreinerhandwerk zu erlernen.', 1, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (1, 6, 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "kategorie_xp", 5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "immer_freigeschlatet", "ja");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_item", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_menge", 10);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 0.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 1);


    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Axt von Roland', 'Man munkelt diese Art von Äxten, gehört nicht\n nur einen Roland. Nein er soll diese \n erfunden haben.', 2, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (2, 6, 5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "kategorie_xp", 7);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 7.5);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Spielzeug Axt', 'Kinder nutzen diese Axt oft zum Spielen.\n Weil Sie so harmlos ist, geben Eltern \n Sie gerne ihren Kindern.', 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (3, 6, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "kategorie_xp", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_item", 4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_menge", 12);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 0.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 0.5);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Anfänger Axt II', 'Diese Axt wurde von einem \n Schreinerlehrling hergestellt. Welcher schon \n etwas Erfahrung gesammelt hat. ', 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (4, 6, 5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "kategorie_xp", 5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_item", 5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_menge", 10);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 1.5);
    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'WOODEN_AXE', 'Axt des Holzfällers Lehrlings', 'Dieser klassischen Axttype wird häufig \n von Holzfällern Lehrlingen genutzt.', 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (5, 6, 8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "shop_xp", 2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "kategorie_xp", 6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_item", 6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_menge", 15);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 0.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 3);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Reststein Axt', 'Diese Axt wird aus Stein Resten gefertigt und ist \n deshalb nicht besonders robust,', 1,  6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 7, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "shop_xp", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_item", 7);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_menge", 15);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 3);
    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Granit Axt', 'Eine Axt aus Granit, fragt sich nur, ob Sie \n auch Holz hacken kann?', 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 7, 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "shop_xp", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_item", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_menge", 17);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 2.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 3.5);
    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Anfänger Axt III', 'Ein Lehrling im letzten Jahr hat Sie gefertigt. \n Deshalb diese Axt so perfekt aus!', 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 7, 5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "shop_xp", 2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "kategorie_xp", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "immer_freigeschlatet", "nein");


        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_item", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_menge", 18);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 5);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Weiße Axt', 'Diese Axt aus Stein wurde ein \n einfach weiß angemalt.', 1, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 7, 4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "shop_xp", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "kategorie_xp", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_item", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_menge", 20);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 9);

    INSERT INTO item (item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', 'STONE_AXE', 'Axt des Holzfällers gesellen', 'Dieser klassischen Axttype wird \n häufig von Holzfällern gesellen genutzt.', 2, 10);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 7, 8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "shop_xp", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "kategorie_xp", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_item", 11);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_menge", 25);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 5.5);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_SWORD', 'Schwerter', 'Baue und erforsche die mächtigsten Schwerter', 2);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_HOE', 'Hacken', 'Baue und erforsche die mächtigsten Schaufeln', 3);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_HELMET', 'Helme', 'Baue und erforsche die mächtigsten Helme', 4);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_CHESTPLATE', 'Brustplatten', 'Baue und erforsche die mächtigsten Brustplatten', 5);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_LEGGINGS', 'Hosen', 'Baue und erforsche die mächtigsten Hosen', 6);

INSERT INTO item_kategorie (icon, name, beschreibung, reinfolge) VALUES ('NETHERITE_BOOTS', 'Schuhe', 'Baue und erforsche die mächtigsten Schuhe', 6);
