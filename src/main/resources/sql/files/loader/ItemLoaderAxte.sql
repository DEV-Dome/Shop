-- SEITE 2: Holz Axt
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('1', '1', 'WOODEN_AXE', 'Anfänger Axt I', 'Diese Axt wurde von einem Schreinerlehrling \n hergestellt und dient nun dem Zweck, \n das Schreinerhandwerk zu erlernen.', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (1, 6, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_item", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "freigeschlatet_menge", 10);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_schaden", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_schaden", 0.6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "min_angriffsgeschwindigkeit", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (1, "max_angriffsgeschwindigkeit", 0.4);
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('2', '1', 'WOODEN_AXE', 'Axt von Roland', 'Man munkelt diese Art von Äxten, gehört nicht\n nur einen Roland. Nein er soll diese \n erfunden haben.', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (2, 6, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_item", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "freigeschlatet_menge", 15);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "min_schaden", 0.7);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "max_schaden", 1.3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "min_angriffsgeschwindigkeit", 0.25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (2, "max_angriffsgeschwindigkeit", 0.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('3', '1', 'WOODEN_AXE', 'Spielzeug Axt', 'Kinder nutzen diese Axt oft zum Spielen.\n Weil Sie so harmlos ist, geben Eltern \n Sie gerne ihren Kindern.', 2, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (3, 6, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (3, 11, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "min_schaden", 0.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "max_schaden", 0.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "min_angriffsgeschwindigkeit", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "max_angriffsgeschwindigkeit", 0.3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('4', '1', 'WOODEN_AXE', 'Anfänger Axt II', 'Diese Axt wurde von einem \n Schreinerlehrling hergestellt. Welcher schon \n etwas Erfahrung gesammelt hat. ', 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (4, 6, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_item", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "min_schaden", 1.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "max_schaden", 1.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "min_angriffsgeschwindigkeit", 0.43);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (4, "max_angriffsgeschwindigkeit", 0.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('5', '1', 'WOODEN_AXE', 'Axt des Holzfällers Lehrlings', 'Dieser klassischen Axttype wird häufig \n von Holzfällern Lehrlingen genutzt.', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (5, 6, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (105, 13, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_item", 6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "min_schaden", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "max_schaden", 1.9);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "min_angriffsgeschwindigkeit", 0.45);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (5, "max_angriffsgeschwindigkeit", 0.55);

-- SEITE 2: Stein Axt
    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('6', '1', 'STONE_AXE', 'Reststein Axt', 'Diese Axt wird aus Stein Resten gefertigt und ist \n deshalb nicht besonders robust,', 1,  6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (6, 7, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_item", 7);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "freigeschlatet_menge", 22);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "min_schaden", 0.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "max_schaden", 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "min_angriffsgeschwindigkeit", 0.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (6, "max_angriffsgeschwindigkeit", 0.55);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('7', '1', 'STONE_AXE', 'Granit Axt', 'Eine Axt aus Granit, fragt sich nur, ob Sie \n auch Holz hacken kann?', 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (7, 18, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_item", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "freigeschlatet_menge", 24);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "min_schaden", 0.7);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "max_schaden", 2.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "min_angriffsgeschwindigkeit", 0.45);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (7, "max_angriffsgeschwindigkeit", 0.57);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('8', '1', 'STONE_AXE', 'Anfänger Axt III', 'Ein Lehrling im letzten Jahr hat Sie gefertigt. \n Deshalb diese Axt so perfekt aus!', 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 7, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (8, 20, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "kategorie_xp", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "immer_freigeschlatet", "nein");


        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_item", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "freigeschlatet_menge", 25);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "min_schaden", 1.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "max_schaden", 2.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "min_angriffsgeschwindigkeit", 0.51);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (8, "max_angriffsgeschwindigkeit", 0.59);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('9', '1', 'STONE_AXE', 'Weiße Axt', 'Diese Axt aus Stein wurde ein \n einfach weiß angemalt.', 2, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (9, 16, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "kategorie_xp", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_item", ,0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "min_schaden", 1.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "max_schaden", 2.4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "min_angriffsgeschwindigkeit", 0.48);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (9, "max_angriffsgeschwindigkeit", 0.65);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('10', '1', 'STONE_AXE', 'Axt des Holzfällers gesellen', 'Dieser klassischen Axttype wird \n häufig von Holzfällern gesellen genutzt.', 1, 10);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 7, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 12, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (10, 19, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "kategorie_xp", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_item", 11);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "freigeschlatet_menge", 30);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "min_schaden", 1.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "max_schaden", 2.3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "min_angriffsgeschwindigkeit", 0.54);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (10, "max_angriffsgeschwindigkeit", 0.63);

-- SEITE 3: Eisen Axt
    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('11', '1', 'IRON_AXE', 'Misch Axt', 'Diese Axt ist aus allem möglichem zusammen gemischt.', 1, 11);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (11, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (11, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (11, 13, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (11, 23, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "kategorie_xp", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "freigeschlatet_item", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "freigeschlatet_menge", 32);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "min_schaden", 1.8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "max_schaden", 2.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "min_angriffsgeschwindigkeit", 0.54);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (11, "max_angriffsgeschwindigkeit", 0.65);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('12', '1', 'IRON_AXE', 'Silber Axt', 'Diese Axt besteht zu einem Großteil aus Silber.', 1, 12);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (12, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (12, 2, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (12, 1, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (12, 11, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (12, 22, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "kategorie_xp", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "freigeschlatet_item", 13);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "freigeschlatet_menge", 34);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "min_schaden", 2.1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "max_schaden", 2.6);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "min_angriffsgeschwindigkeit", 0.58);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (12, "max_angriffsgeschwindigkeit", 0.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('13', '1', 'IRON_AXE', 'Volkers Axt', 'Ob sich Volker und Roland kennen ?', 1, 13);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (13, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (13, 2, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (13, 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (13, 15, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (13, 26, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "kategorie_xp", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "freigeschlatet_item", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "freigeschlatet_menge", 35);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "min_schaden", 2.4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "max_schaden", 2.8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "min_angriffsgeschwindigkeit", 0.65);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (13, "max_angriffsgeschwindigkeit", 0.72);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('14', '1', 'IRON_AXE', 'Verrottete Axt', 'Diese Axt sieht so verrottet aus,\n als wenn sie Jahre zehnte nicht genutzt worden wäre.', 1, 14);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (14, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (14, 2, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (14, 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (14, 15, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (14, 26, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "freigeschlatet_item", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "freigeschlatet_menge", 37);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "min_schaden", 2.4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "max_schaden", 3.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "min_angriffsgeschwindigkeit", 0.69);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (14, "max_angriffsgeschwindigkeit", 0.76);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('15', '1', 'IRON_AXE', 'Jagende Axt', 'Diese Axt jagt, ihre Feinde von alleine.', 1, 15);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (15, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (15, 2, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (15, 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (15, 14, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (15, 20, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "freigeschlatet_item", 16);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "freigeschlatet_menge", 40);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "min_schaden", 2.8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "max_schaden", 3.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "min_angriffsgeschwindigkeit", 0.72);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (15, "max_angriffsgeschwindigkeit", 0.8);

-- SEITE 4: Gold Axt
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('16', '1', 'GOLDEN_AXE', 'Ladirende Axt', 'Wann sich diese Axt wohl aufgelöst hat?', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (16, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (16, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 2, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (16, 3, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (16, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (16, 23, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "kategorie_xp", 15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "freigeschlatet_item", 18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "freigeschlatet_menge", 42);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "min_schaden", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "max_schaden", 3.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "min_angriffsgeschwindigkeit", 0.75);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (16, "max_angriffsgeschwindigkeit", 0.81);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('17', '1', 'GOLDEN_AXE', 'Axt des Schweins', 'Schweine in der Hölle tragen diese Äxte', 2, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 2, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 3, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 15, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 22, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (17, 23, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "min_schaden", 3.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "max_schaden", 4.2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "min_angriffsgeschwindigkeit", 0.80);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (17, "max_angriffsgeschwindigkeit", 0.93);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('18', '1', 'GOLDEN_AXE', 'Kunstgold Axt', 'Diese Axt besteht nicht aus echtem Gold!', 1, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (18, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (18, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 2, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (18, 3, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (18, 12, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (18, 16, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "freigeschlatet_item", 19);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "min_schaden", 2.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "max_schaden", 3.7);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "min_angriffsgeschwindigkeit", 0.78);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (18, "max_angriffsgeschwindigkeit", 0.85);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('19', '1', 'GOLDEN_AXE', 'Sparschwein schlachter', 'Kann jedes Sparschwein tötem.', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (19, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (19, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 2, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (19, 3, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (19, 11, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (19, 20, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "freigeschlatet_item", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "freigeschlatet_menge", 47);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "min_schaden", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "max_schaden", 3.85);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "min_angriffsgeschwindigkeit", 0.78);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (19, "max_angriffsgeschwindigkeit", 0.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('20', '1', 'GOLDEN_AXE', 'Axt des Chefkochs', 'Damit gelingt einfach jedes Gericht!', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 2, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 3, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 12, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (20, 21, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "freigeschlatet_item", 21);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "min_schaden", 3.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "max_schaden", 4.1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "min_angriffsgeschwindigkeit", 0.82);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (20, "max_angriffsgeschwindigkeit", 0.91);

-- SEITE 5: Diamanten Axt
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('21', '1', 'DIAMOND_AXE', 'Mode Axt', 'Sieht diese Axt nicht einfach Tolle\n zu jeder Ausrüstung aus?', 1, 21);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 6, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 2, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 4, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 13, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (21, 26, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "kategorie_xp", 18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "freigeschlatet_item", 22);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "freigeschlatet_menge", 52);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "min_schaden", 3.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "max_schaden", 4.2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "min_angriffsgeschwindigkeit", 0.88);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (21, "max_angriffsgeschwindigkeit", 0.92);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('22', '1', 'DIAMOND_AXE', 'Axt des Häckerns', 'Mit dieser Axt wurden schon\n viele Menschen hingerichtet.', 1, 22);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 2, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 4, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 11, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (22, 22, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "kategorie_xp", 18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "freigeschlatet_item", 23);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "min_schaden", 3.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "max_schaden", 4.3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "min_angriffsgeschwindigkeit", 0.87);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (22, "max_angriffsgeschwindigkeit", 0.95);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('23', '1', 'DIAMOND_AXE', 'Faulende Axt', 'Diese Axt fault schon lange vor sich hin.', 1, 23);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 2, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 4, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 15, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (23, 17, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "freigeschlatet_item", 25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "freigeschlatet_menge", 57);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "min_schaden", 3.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "max_schaden", 4.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "min_angriffsgeschwindigkeit", 0.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (23, "max_angriffsgeschwindigkeit", 0.97);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('24', '1', 'DIAMOND_AXE', 'Meeres blaue Axt', 'Diese Axt ist so blau, das\n denkt man schaut in Meer!', 2, 24);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 2, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 4, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 13, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 26, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (24, 27, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "kategorie_xp", 22);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "min_schaden", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "max_schaden", 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "min_angriffsgeschwindigkeit", 0.91);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (24, "max_angriffsgeschwindigkeit", 1.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('25', '1', 'DIAMOND_AXE', 'Blitzende Axt', 'Wenn diese Axt geschwungen wird,\n könnte man meinen, dass es blitzt', 1, 25);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 2, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 4, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 12, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (25, 27, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "freigeschlatet_item", 26);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "freigeschlatet_menge", 60);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "min_schaden", 4.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "max_schaden", 4.8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "min_angriffsgeschwindigkeit", 0.89);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (25, "max_angriffsgeschwindigkeit", 1);

-- SEITE 6: Nether Axt
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('26', '1', 'NETHERITE_AXE', 'Axt vom Höllenhund', 'Der Höllenhund war eins ein Wirklich böser\n Mensch. Jedoch hat hin seit Jahren keiner\n mehr gesehen.', 1, 26);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 6, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 3, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 4, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 5, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 12, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 14, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 20, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (26, 27, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "freigeschlatet_item", 28);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "freigeschlatet_menge", 62);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "min_schaden", 4.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "max_schaden", 5.2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "min_angriffsgeschwindigkeit", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (26, "max_angriffsgeschwindigkeit", 1.3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('27', '1', 'NETHERITE_AXE', 'Metzlers Axt', 'Eine Brutale und verrückte Axt zugleich!', 2, 27);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 4, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 5, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 12, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 14, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 19, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (27, 27, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "kategorie_xp", 30);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "min_schaden", 4.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "max_schaden", 5.8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "min_angriffsgeschwindigkeit", 1.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (27, "max_angriffsgeschwindigkeit", 1.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('28', '1', 'NETHERITE_AXE', 'Axt der Nacht', 'Diese Axt wurde vornehmlich nachts für\n ihre Taten genutzt!', 1, 28);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 4, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 5, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 12, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 14, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 20, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (28, 27, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "kategorie_xp", 28);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "freigeschlatet_item", 30);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "freigeschlatet_menge", 65);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "min_schaden", 4.7);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "max_schaden", 5.3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "min_angriffsgeschwindigkeit", 1.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (28, "max_angriffsgeschwindigkeit", 1.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('29', '1', 'NETHERITE_AXE', 'Axt mit Schwunges', 'Die mit Abstand schnellste\n Axt, die es gibt!', 2, 29);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 4, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 5, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 11, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 13, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 16, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (29, 26, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "kategorie_xp", 30);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "min_schaden", 4.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "max_schaden", 5.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "min_angriffsgeschwindigkeit", 1.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (29, "max_angriffsgeschwindigkeit", 2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('30', '1', 'NETHERITE_AXE', 'Axt des Teufels', 'Du hast es geschafft, die Axt\n des Teufels nachzubauen!\n Das schaffen nur wenige.', 1, 30);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 4, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 5, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 13, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 14, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 19, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (30, 27, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "kategorie_xp", 28);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "min_schaden", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "max_schaden", 5.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "min_angriffsgeschwindigkeit", 1.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (30, "max_angriffsgeschwindigkeit", 1.7);