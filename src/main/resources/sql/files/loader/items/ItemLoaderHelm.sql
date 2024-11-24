-- SEITE 1: Leader Helm
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('600', '4', 'LEATHER_HELMET', 'Leaderfetzen', 'Dieses Stück Leader wird einfach auf den Kopf gelegt!', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (600, 8, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "freigeschlatet_item", 601);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "freigeschlatet_menge",3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "min_ruestung", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (600, "max_ruestung", 0.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('601', '4', 'LEATHER_HELMET', 'Bettlers Hut', 'In diesen Hut werden Spenden gesammelt', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (601, 8, 4);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "freigeschlatet_item", 602);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "freigeschlatet_menge", 8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "min_ruestung", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (601, "max_ruestung", 0.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('602', '4', 'LEATHER_HELMET', 'Tigers Haut', 'Diese Rüstung ist aus der\n Haut eines Tigers gemacht', 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (602, 8, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "freigeschlatet_item", 604);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "freigeschlatet_menge", 13);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "min_ruestung", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (602, "max_ruestung", 0.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('603', '4', 'LEATHER_HELMET', 'Spielzeug Helm', 'Dieser Helm ist zum Spielen für\nKinder gedacht. Sie schützt\ndich nicht wirklich!', 2, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (603, 8, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (603, 9, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "min_ruestung", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (603, "max_ruestung", 0.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('604', '4', 'LEATHER_HELMET', 'Kunsthelm', 'Ist das Kunst oder kann das weg?', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (604, 8, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (604, 9, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "freigeschlatet_item", 605);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "min_ruestung", 0.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (604, "max_ruestung", 0.55);

-- SEITE 2: Eisen Helm
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('605', '4', 'IRON_HELMET', 'Nasenschutz', 'Schütz dieser Helm wirklich den ganzen Kopf?', 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (605, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (605, 1, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "freigeschlatet_item", 607);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "freigeschlatet_menge", 24);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "min_ruestung", 0.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (605, "max_ruestung", 0.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('606', '4', 'IRON_HELMET', 'Knappen Helm', 'Dieser Helm ist für alle,\ndie ein Ritter sein wollen.', 2, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (606, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (606, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (606, 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (606, 16, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "min_ruestung", 1.25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (606, "max_ruestung", 1.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('607', '4', 'IRON_HELMET', 'Schwertspalter', 'Nur wenige Schwerter kommen\ndurch diesen Helm.', 1, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (607, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (607, 1, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "freigeschlatet_item", 608);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "freigeschlatet_menge", 27);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "min_ruestung", 0.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (607, "max_ruestung", 1.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('608', '4', 'IRON_HELMET', 'Schmiede Fehler', 'Dieser Helm ist dir viel zu groß!', 1, 9);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (608, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (608, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (608, 1, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "freigeschlatet_item", 609);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "freigeschlatet_menge", 30);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "min_ruestung", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (608, "max_ruestung", 1.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('609', '4', 'IRON_HELMET', 'Assassinen Helm', 'Diese Rüstung schütz nicht nur\n jeder ist in ihr auch schnell!', 1, 10);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (609, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (609, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (609, 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (609, 12, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "freigeschlatet_item", 610);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "freigeschlatet_menge", 33);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "min_ruestung", 1.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (609, "max_ruestung", 1.7);

-- SEITE 3: Gold Helm
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('610', '4', 'GOLDEN_HELMET', 'Diebesgut', 'Dieser Helm ist so wertvoll, \ndass er gestohlen wurde,', 1, 11);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (610, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (610, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (610, 3, 2);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (610, 13, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "freigeschlatet_item", 611);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "freigeschlatet_menge", 35);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "min_ruestung", 1.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (610, "max_ruestung", 2.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('611', '4', 'GOLDEN_HELMET', 'Marvins Kriegshelm', 'Man weiß nicht, wo dieser Helm herkommt', 1, 12);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (611, 8, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (611, 9, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (611, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (611, 3, 2);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (611, 12, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "freigeschlatet_item", 612);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "freigeschlatet_menge", 37);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "min_ruestung", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (611, "max_ruestung", 2.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('612', '4', 'GOLDEN_HELMET', 'Zeremonien Helm', 'Wird zu besonderen Anlässen getragen!', 1, 13);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (612, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (612, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (612, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (612, 3, 4);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (612, 11, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "freigeschlatet_item", 615);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "freigeschlatet_menge", 40);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "min_ruestung", 2.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (612, "max_ruestung", 2.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('613', '4', 'GOLDEN_HELMET', 'Kustkrone', 'Man sieht ihr an, sie Gefälscht ist!', 2, 14);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (613, 18, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "min_ruestung", 2.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (613, "max_ruestung", 3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('614', '4', 'GOLDEN_HELMET', 'Krone', 'Ist der Träger ein König?', 1, 15);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 3, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (614, 15, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "freigeschlatet_item", 615);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "freigeschlatet_menge", 43);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "min_ruestung", 2.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (614, "max_ruestung", 3.2);

-- SEITE 4: Diamant Helm
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('615', '4', 'DIAMOND_HELMET', 'Magiestein Helm', 'Was wohl Magier mit dem Helm zu tun haben?', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 4, 3);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 26, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (615, 16, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "freigeschlatet_item", 616);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "min_ruestung", 2.85);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (615, "max_ruestung", 3.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('616', '4', 'DIAMOND_HELMET', 'Durchlöchter Helm', 'Wo das wohl passiert ist?', 1, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 9, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 4, 5);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 23, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (616, 13, 2);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "freigeschlatet_item", 618);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "min_ruestung", 3.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (616, "max_ruestung", 3.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('617', '4', 'DIAMOND_HELMET', 'Spiegelnder Helm', 'In diesem Helm Sieht man sich selber', 2, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 28, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (617, 25, 2);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "min_ruestung", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (617, "max_ruestung", 4.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('618', '4', 'DIAMOND_HELMET', 'Diadem', 'Eine dem Wohl am besten Aussehens Kronen.', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 8, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 3, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 4, 6);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 21, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (618, 24, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "freigeschlatet_item", 619);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "min_ruestung", 3.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (618, "max_ruestung", 4.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('619', '4', 'DIAMOND_HELMET', 'Botum', 'Der wohl stärkste Helm den es gibt!', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 8, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 9, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 3, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 4, 7);

    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 12, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 14, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (619, 24, 1);


    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "min_ruestung", 4.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (619, "max_ruestung", 4.6);