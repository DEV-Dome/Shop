-- SEITE 1: Leader Rüstung
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('500', '5', 'LEATHER_CHESTPLATE', 'Lupmige Platte', 'Diese Rüstung ist so zerfetzt,\ndass man sie kaum erkennt!', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (500, 8, 4);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "freigeschlatet_item", 501);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "freigeschlatet_menge",3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "min_ruestung", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (500, "max_ruestung", 0.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('501', '5', 'LEATHER_CHESTPLATE', 'Lederrest', 'Diese leichte Rüstung wurde\naus Leaderresten hergestellt', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (501, 8, 6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "freigeschlatet_item", 502);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "freigeschlatet_menge", 8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "min_ruestung", 0.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (501, "max_ruestung", 0.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('502', '5', 'LEATHER_CHESTPLATE', 'Halbe Rüstung', 'Die Hälfte der Rüstung fehlt einfach!', 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (502, 8, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (502, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "freigeschlatet_item", 504);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "freigeschlatet_menge", 13);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "min_ruestung", 0.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (502, "max_ruestung", 0.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('503', '5', 'LEATHER_CHESTPLATE', 'Spielzeug Rüstung', 'Diese Rüstung ist zum Spielen für\nKinder gedacht. Sie schützt\ndich nicht wirklich!', 2, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (503, 8, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (503, 9, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "min_ruestung", 0.75);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (503, "max_ruestung", 1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('504', '5', 'LEATHER_CHESTPLATE', 'Assassinen Rüstung', 'Eine der Besten leichten Rüstungen die Es gibt.\nSchützen tu sie trotzdem nicht', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (504, 8, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (504, 9, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "freigeschlatet_item", 505);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "min_ruestung", 0.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (504, "max_ruestung", 1.1);

-- SEITE 2: Eisen Rüstung
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('505', '5', 'IRON_CHESTPLATE', 'Rostige Rüstung', 'Ist diese Rüstung schon Braun?', 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (505, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (505, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (505, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (505, 13, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "freigeschlatet_item", 506);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "freigeschlatet_menge", 22);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "min_ruestung", 1.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (505, "max_ruestung", 1.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('506', '5', 'IRON_CHESTPLATE', 'Bergmans Rüstung', 'Das Rezept für diese Rüstung\nstammt aus einer mine!', 1, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (506, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (506, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (506, 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (506, 11, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "freigeschlatet_item", 507);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "freigeschlatet_menge", 25);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "min_ruestung", 1.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (506, "max_ruestung", 2.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('507', '5', 'IRON_CHESTPLATE', 'Durchdrunge Rüstung', 'Ist das ein Loch in der mitte ?', 1, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (507, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (507, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (507, 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (507, 14, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "freigeschlatet_item", 508);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "freigeschlatet_menge", 27);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "min_ruestung", 1.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (507, "max_ruestung", 2.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('508', '5', 'IRON_CHESTPLATE', 'Soldaten Rüstung', 'Diese Rüstung wird oft von Soldaten getragen', 1, 9);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (508, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (508, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (508, 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (508, 12, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "freigeschlatet_item", 509);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "freigeschlatet_menge", 30);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "min_ruestung", 2.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (508, "max_ruestung", 2.85);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('509', '5', 'IRON_CHESTPLATE', 'Stählende Rüstung', 'Wohl der beste Stahl\nden man finden kann!', 1, 10);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (509, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (509, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (509, 1, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (509, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (509, 15, 1);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "freigeschlatet_item", 510);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "freigeschlatet_menge", 33);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "min_ruestung", 2.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (509, "max_ruestung", 3);

-- SEITE 3: Gold Rüstung
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('510', '5', 'GOLDEN_CHESTPLATE', 'Schweine Rüstung', 'Wurde diese Rüstung einem Schwein geklaut?', 1, 11);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 3, 3);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 11, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 14, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (510, 15, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "freigeschlatet_item", 512);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "freigeschlatet_menge", 35);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "min_ruestung", 2.95);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (510, "max_ruestung", 3.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('511', '5', 'GOLDEN_CHESTPLATE', 'Königsrüstung', 'Diese Rüstung gehörte eins\neinem mächtigen Mann!', 2, 12);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 3, 8);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 12, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 22, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (511, 23, 1);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "min_ruestung", 4.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (511, "max_ruestung", 5.15);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('512', '5', 'GOLDEN_CHESTPLATE', 'Goblin Sack', 'Diese Rüstung wurde einem Goblin\naus dem Sack geklaut!', 1, 13);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 3, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (512, 20, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "freigeschlatet_item", 513);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "freigeschlatet_menge", 37);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "min_ruestung", 3.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (512, "max_ruestung", 3.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('513', '5', 'GOLDEN_CHESTPLATE', 'Restgold Rüstung', 'Diese Rüstung ist so\nedel wie sie aussieht!', 1, 14);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 3, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 14, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (513, 16, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "freigeschlatet_item", 514);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "freigeschlatet_menge", 40);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "min_ruestung", 3.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (513, "max_ruestung", 4.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('514', '5', 'GOLDEN_CHESTPLATE', 'Moderne Rüstung', 'Diese Rüstung sieht Toll aus.\nKann Sie einen aber auch schützen?', 1, 15);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 3, 8);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 19, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (514, 24, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "freigeschlatet_item", 515);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "freigeschlatet_menge", 43);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "min_ruestung", 4.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (514, "max_ruestung", 5);

-- SEITE 4: Diamant Rüstung
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('515', '5', 'DIAMOND_CHESTPLATE', 'Himmelsblau Rüstung', 'Diese Rüstung ist so Blau wie der Himmel.', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 3, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 4, 3);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 22, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 14, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 21, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (515, 19, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "freigeschlatet_item", 516);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "min_ruestung", 5.05);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (515, "max_ruestung", 5.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('516', '5', 'DIAMOND_CHESTPLATE', 'Rüstung der Hoffnung', 'Die Frage ist eher,\nwer an dieser Rüstung seine\nHoffnung verloren hat.', 1, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 4, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 25, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 15, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (516, 17, 1);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "freigeschlatet_item", 518);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "min_ruestung", 5.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (516, "max_ruestung", 6.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('517', '5', 'DIAMOND_CHESTPLATE', 'Spiegelnde Rüstung', 'In dieser Rüstung Sieht man sich selber!', 2, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 4, 10);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 16, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 23, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 26, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 27, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 28, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (517, 29, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "min_ruestung", 6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (517, "max_ruestung", 7.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('518', '5', 'DIAMOND_CHESTPLATE', 'Markenstück', 'Diese Rüstung scheint Markenware zu sein!', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 23, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 27, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 20, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (518, 19, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "freigeschlatet_item", 519);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "min_ruestung", 5.98);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (518, "max_ruestung", 6.65);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('519', '5', 'DIAMOND_CHESTPLATE', 'Schimmernde Rüstung', 'Mit dieser Rüstung kannst\ndu deinen Gegner Blenden!', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 4, 9);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 18, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 13, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 26, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 27, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 28, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (519, 29, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "min_ruestung", 6.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (519, "max_ruestung", 7);


