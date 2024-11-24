-- SEITE 1: Leader Schuhe
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('800', '7', 'LEATHER_BOOTS', 'Durchgelaschte Schuhe', 'Dieser Schuh hat seine Lebensdauer erreicht!', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (800, 8, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "freigeschlatet_item", 801);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "freigeschlatet_menge",3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "min_ruestung", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (800, "max_ruestung", 0.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('801', '7', 'LEATHER_BOOTS', 'Schuh ohne Sohle', 'Dieser schuhe hat nichtmal eine Sohle', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (801, 8, 4);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "freigeschlatet_item", 802);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "freigeschlatet_menge", 8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "min_ruestung", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (801, "max_ruestung", 0.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('802', '7', 'LEATHER_BOOTS', 'Sandellen', 'Luftig für den Sommer', 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (802, 8, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "freigeschlatet_item", 804);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "freigeschlatet_menge", 13);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "min_ruestung", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (802, "max_ruestung", 0.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('803', '7', 'LEATHER_BOOTS', 'Spielzeug Schuhe', 'Diese Schuhe sind zum Spielen für\nKinder gedacht. Sie schützt\ndich nicht wirklich!', 2, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (803, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (803, 9, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "min_ruestung", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (803, "max_ruestung", 0.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('804', '7', 'LEATHER_BOOTS', 'Lautloser Schuhe', 'Perfekt um damit zu schleichen', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (804, 8, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (804, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "freigeschlatet_item", 805);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "min_ruestung", 0.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (804, "max_ruestung", 0.55);

-- SEITE 2: Eisen Schuhe
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('805', '7', 'IRON_BOOTS', 'Quischige Schuhe', 'Das Geräusch sagt alles!', 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (805, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (805, 1, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "freigeschlatet_item", 806);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "freigeschlatet_menge", 24);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "min_ruestung", 0.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (805, "max_ruestung", 0.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('806', '7', 'IRON_BOOTS', 'Sumpfschuhe', 'Diese Schuhe wurden in einem Mohr gefunden.', 1, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (806, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (806, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (806, 1, 6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "freigeschlatet_item", 807);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "freigeschlatet_menge", 27);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "min_ruestung", 1.25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (806, "max_ruestung", 1.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('807', '7', 'IRON_BOOTS', 'Yanniks Schuhe', 'Yannik war ein schmiede geselle.', 1, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (807, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (807, 1, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "freigeschlatet_item", 809);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "freigeschlatet_menge", 30);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "min_ruestung", 0.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (807, "max_ruestung", 1.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('808', '7', 'IRON_BOOTS', 'Ritterschuhe', 'Leben die überhaupt noch?', 2, 9);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (808, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (808, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (808, 1, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (808, 15, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "min_ruestung", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (808, "max_ruestung", 1.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('809', '7', 'IRON_BOOTS', 'Schuhe des Admirals', 'Nur würdige Kämpfer dürfen diese Schuhe tragen.', 1, 10);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (809, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (809, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (809, 1, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (809, 13, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "freigeschlatet_item", 810);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "freigeschlatet_menge", 33);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "min_ruestung", 1.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (809, "max_ruestung", 1.7);

-- SEITE 3: Gold Schuhe
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('810', '7', 'GOLDEN_BOOTS', 'Schueh des Frühaufsteher', 'Morgenstund hat gold im mund.', 1, 11);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (810, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (810, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (810, 3, 2);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (810, 11, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "freigeschlatet_item", 811);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "freigeschlatet_menge", 35);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "min_ruestung", 1.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (810, "max_ruestung", 2.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('811', '7', 'GOLDEN_BOOTS', 'Sprinter', 'Der Träger wird durch diese\nSchuhe deutlich schneller!', 1, 12);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (811, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (811, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (811, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (811, 3, 2);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (811, 14, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "freigeschlatet_item", 813);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "freigeschlatet_menge", 37);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "min_ruestung", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (811, "max_ruestung", 2.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('812', '7', 'GOLDEN_BOOTS', 'Schuhe eins Reliktes', 'Wo ist wohl der Rest der Rüstung?', 2, 13);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (812, 16, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "min_ruestung", 2.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (812, "max_ruestung", 2.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('813', '7', 'GOLDEN_BOOTS', 'Schuhe der Goldspur', 'Der Legende nach verwandt\nsich alles in Gold, was\ndiese Schuhe berühren!', 1, 14);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (813, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (813, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (813, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (813, 3, 4);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (813, 12, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "freigeschlatet_item", 814);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "freigeschlatet_menge", 40);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "min_ruestung", 2.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (813, "max_ruestung", 3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('814', '7', 'GOLDEN_BOOTS', 'Schuhe des Dinners', 'Der Dinner hat einen großzügigen Herren.', 1, 15);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (814, 19, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "freigeschlatet_item", 815);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "freigeschlatet_menge", 43);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "min_ruestung", 2.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (814, "max_ruestung", 3.2);

-- SEITE 4: Diamant Schuhe
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('815', '7', 'DIAMOND_BOOTS', 'Piraten Schuhe', 'Welchem Unheil haben diese Schuhe mal gehört?', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 4, 3);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 18, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (815, 28, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "freigeschlatet_item", 816);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "min_ruestung", 2.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (815, "max_ruestung", 3.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('816', '7', 'DIAMOND_BOOTS', 'Sprung Schuhe', 'Mit keinem Schuh kannst du höher springen.', 1, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 4, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (816, 14, 2);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "freigeschlatet_item", 818);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "min_ruestung", 3.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (816, "max_ruestung", 3.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('817', '7', 'DIAMOND_BOOTS', 'Merlins schuhe', 'Welcher Zauber diesen\nTretern wohl innewohnt?', 2, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 25, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (817, 27, 2);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "min_ruestung", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (817, "max_ruestung", 4.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('818', '7', 'DIAMOND_BOOTS', 'Wasser Schuhe', 'Kann man damit über Wasser laufen?', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 3, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 4, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 22, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (818, 26, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "freigeschlatet_item", 819);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "min_ruestung", 3.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (818, "max_ruestung", 4.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('819', '7', 'DIAMOND_BOOTS', 'Assassinen Schuhe', 'Diese Schuhe schützen nicht nur\n jeder ist in ihr auch schnell!', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (819, 23, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "min_ruestung", 4.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (819, "max_ruestung", 4.6);