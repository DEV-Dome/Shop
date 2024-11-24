-- SEITE 1: Leader Hose
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('700', '6', 'LEATHER_LEGGINGS', 'Loch Hose', 'Diese Hose ist total durchlöchert', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (700, 8, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "freigeschlatet_item", 701);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "freigeschlatet_menge",3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "min_ruestung", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (700, "max_ruestung", 0.3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('701', '6', 'LEATHER_LEGGINGS', 'Besser als Nichts', 'Immer noch besser als keine Hose', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (701, 8, 6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "freigeschlatet_item", 702);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "freigeschlatet_menge", 8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "min_ruestung", 0.18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (701, "max_ruestung", 0.35);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('702', '6', 'LEATHER_LEGGINGS', 'Diebshose', 'Diese Hose wird oft von Dieben getragen', 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (702, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (702, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "freigeschlatet_item", 704);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "freigeschlatet_menge", 13);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "min_ruestung", 0.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (702, "max_ruestung", 0.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('703', '6', 'LEATHER_LEGGINGS', 'Spielzeug Hose', 'Diese Hose ist zum Spielen für\nKinder gedacht. Sie schützt\ndich nicht wirklich!', 2, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (703, 8, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (703, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "min_ruestung", 0.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (703, "max_ruestung", 0.65);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('704', '6', 'LEATHER_LEGGINGS', 'Krokodils Hose', 'Dieser Helm ist aus einem Krokodil gefertigt!', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (704, 8, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (704, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "freigeschlatet_item", 705);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "min_ruestung", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (704, "max_ruestung", 0.6);

-- SEITE 2: Eisen Hose
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('705', '6', 'IRON_LEGGINGS', 'Klumpige Hose', 'Ob der Schmied sein Handwerk konnte?', 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (705, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (705, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (705, 1, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "freigeschlatet_item", 706);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "freigeschlatet_menge", 24);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "min_ruestung", 0.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (705, "max_ruestung", 1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('706', '6', 'IRON_LEGGINGS', 'Hose von Jan', 'Wer diese Hose wohl getragen hat?', 1, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (706, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (706, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (706, 1, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "freigeschlatet_item", 708);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "freigeschlatet_menge", 27);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "min_ruestung", 0.93);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (706, "max_ruestung", 1.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('707', '6', 'IRON_LEGGINGS', 'Axt Teiler', 'Selbst die mächtigsten Axte\nkommen hier nicht durch!', 2, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (707, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (707, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (707, 1, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (707, 13, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "min_ruestung", 1.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (707, "max_ruestung", 2.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('708', '6', 'IRON_LEGGINGS', 'Seemanns Hose', 'Ob man mit dieser Hose schwimmen kann?', 1, 9);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (708, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (708, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (708, 1, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "freigeschlatet_item", 709);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "freigeschlatet_menge", 30);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "min_ruestung", 1.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (708, "max_ruestung", 1.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('709', '6', 'IRON_LEGGINGS', 'Knieschoner', 'Damit du kein Pfeil ins knie, bekommst!', 1, 10);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (709, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (709, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (709, 1, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (709, 11, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "freigeschlatet_item", 711);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "freigeschlatet_menge", 33);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "min_ruestung", 1.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (709, "max_ruestung", 2);

-- SEITE 3: Gold Hose
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('710', '6', 'GOLDEN_LEGGINGS', 'Hose des Feuers', 'Feuer kann dieser Hose einfach nichts anhaben.', 1, 11);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (710, 14, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "min_ruestung", 2.95);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (710, "max_ruestung", 3.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('711', '6', 'GOLDEN_LEGGINGS', 'Duplikaten Hose', 'Gibt es diese Hose nochmal?', 1, 12);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (711, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (711, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (711, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (711, 3, 3);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (711, 14, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "freigeschlatet_item", 712);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "freigeschlatet_menge", 35);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "min_ruestung", 2.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (711, "max_ruestung", 2.6);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('712', '6', 'GOLDEN_LEGGINGS', 'Sommerhose', 'Das ist eine Wirklich zu kurz geratene Hose.', 1, 13);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (712, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (712, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (712, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (712, 3, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (712, 16, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "freigeschlatet_item", 713);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "freigeschlatet_menge", 37);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "min_ruestung", 2.86);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (712, "max_ruestung", 3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('713', '6', 'GOLDEN_LEGGINGS', 'Vergessene Hose', 'Wo man diese Hose wohl vergessen hat?', 1, 14);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (713, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (713, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (713, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (713, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (713, 18, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "freigeschlatet_item", 714);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "freigeschlatet_menge", 40);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "min_ruestung", 2.87);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (713, "max_ruestung", 3.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('714', '6', 'GOLDEN_LEGGINGS', 'Assassinen Hose', 'Diese Hose schützt nicht nur\n jeder ist in ihr auch schnell!', 1, 15);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 3, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (714, 15, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "freigeschlatet_item", 715);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "freigeschlatet_menge", 43);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "min_ruestung", 3.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (714, "max_ruestung", 3.7);

-- SEITE 4: Diamant Hose
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('715', '6', 'DIAMOND_LEGGINGS', 'Profihose', 'Diese Hose wird nur von Profis getragen!', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 4, 4);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 25, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (715, 15, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "freigeschlatet_item", 716);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "min_ruestung", 3.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (715, "max_ruestung", 4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('716', '6', 'DIAMOND_LEGGINGS', 'Bein Prothese', 'Wenn einen ein Bein abfällt, ist diese Hose perfekt!"', 1, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 4, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (716, 14, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "freigeschlatet_item", 718);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "min_ruestung", 3.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (716, "max_ruestung", 4.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('717', '6', 'DIAMOND_LEGGINGS', 'Hose aus der 1.8', 'Man sieht ihr das alte PVP System noch an.', 2, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 4, 8);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 22, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 26, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "min_ruestung", 4.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (717, "max_ruestung", 5.15);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('718', '6', 'DIAMOND_LEGGINGS', 'Kapitäns Hose', 'Diese Hose wird nur von Kapitän getragen', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 18, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (718, 26, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "freigeschlatet_item", 719);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "min_ruestung", 4.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (718, "max_ruestung", 4.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('719', '6', 'DIAMOND_LEGGINGS', 'Diamant Hose ?', 'Sind das Diamanten oder nlaue Juwelen.', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 4, 8);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 16, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 18, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (719, 20, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "min_ruestung", 4.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (719, "max_ruestung", 5);
