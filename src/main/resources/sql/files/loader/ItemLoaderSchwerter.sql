-- SEITE 1: Holz Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('100', '2', 'WOODEN_SWORD', 'Knappen Schwert', 'Dieses Schert wird mal einen Ritter gehören!', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (100, 6, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "freigeschlatet_item", 102);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "freigeschlatet_menge", 10);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "min_schaden", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "max_schaden", 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "min_angriffsgeschwindigkeit", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (100, "max_angriffsgeschwindigkeit", 0.3);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('102', '2', 'WOODEN_SWORD', 'Holzschafft', 'Ein langes Stückholz!', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (102, 6, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "freigeschlatet_item", 104);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "freigeschlatet_menge", 15);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "min_schaden", 1.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "max_schaden", 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "min_angriffsgeschwindigkeit", 0.25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (102, "max_angriffsgeschwindigkeit", 0.35);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('103', '2', 'WOODEN_SWORD', 'Spielzeug Schwert 1', 'Kinder nutzen dieses Schwert oft zum Spielen.\n Weil Sie so harmlos ist, geben Eltern \n Sie gerne ihren Kindern', 2, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (103, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (103, 13, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "min_schaden", 0.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "max_schaden", 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "min_angriffsgeschwindigkeit", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (103, "max_angriffsgeschwindigkeit", 0.2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('104', '2', 'STICK', 'Rund Schwert', 'Dieses Schwert wurde so lange\n geschliffen bis es rund war!', 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (104, 6, 6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "freigeschlatet_item", 105);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "min_schaden", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "max_schaden", 2.5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "min_angriffsgeschwindigkeit", 0.35);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (104, "max_angriffsgeschwindigkeit", 0.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('105', '2', 'WOODEN_SWORD', 'Holzbrecher', 'Dieses Schwert bricht bei jedem Schlag. Es sollte mit Vorsieht genutzt werden!', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (105, 6, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (105, 11, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "freigeschlatet_item", 106);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "min_schaden", 2.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "max_schaden", 2.8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "min_angriffsgeschwindigkeit", 0.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (105, "max_angriffsgeschwindigkeit", 0.45);

-- SEITE 2: Stein Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('106', '2', 'STONE_SWORD', 'Schwert der Steinklumpen', 'Ein paar aneinander gereihte Steine.\n Wer das Wohl abbekommt?', 1, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (106, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (106, 7, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "kategorie_xp", 8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "freigeschlatet_item", 107);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "freigeschlatet_menge", 22);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "min_schaden", 2.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "max_schaden", 2.85);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "min_angriffsgeschwindigkeit", 0.25);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (106, "max_angriffsgeschwindigkeit", 0.4);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('107', '2', 'STONE_SWORD', 'Kieselstein Schwert', 'Auch kleine Steine können Schaden verursachen!', 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (107, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (107, 7, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (107, 16, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "freigeschlatet_item", 108);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "freigeschlatet_menge", 24);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "min_schaden", 2.8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "max_schaden", 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "min_angriffsgeschwindigkeit", 0.2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (107, "max_angriffsgeschwindigkeit", 0.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('108', '2', 'STONE_SWORD', 'Glattes Steinschwert', 'Niemand hätte gedacht,\n dass Steine so funkeln können!', 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (108, 6, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (108, 7, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (108, 15, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "kategorie_xp", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "freigeschlatet_item", 109);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "freigeschlatet_menge", 25);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "min_schaden", 2.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "max_schaden", 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "min_angriffsgeschwindigkeit", 0.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (108, "max_angriffsgeschwindigkeit", 0.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('109', '2', 'STONE_SWORD', 'Zerbröckelndes Schwert', 'Wo diese es Schwert nur draufgeschlagen\n wurde? Auf jeden Fall sind das nur\n noch die Überreste!', 1, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (109, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (109, 7, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (109, 18, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "kategorie_xp", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "freigeschlatet_item", 110);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "freigeschlatet_menge", 27);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "min_schaden", 3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "max_schaden", 3.3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "min_angriffsgeschwindigkeit", 0.25);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (109, "max_angriffsgeschwindigkeit", 0.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('110', '2', 'STONE_SWORD', 'Arturs Schwert', 'Dies Schwert stecke eins in einem\n  Stein. Wer es da wohl\n herausgezogen hat?', 1, 10);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (110, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (110, 7, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (110, 12, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (110, 20, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "kategorie_xp", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "freigeschlatet_item", 111);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "freigeschlatet_menge", 30);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "min_schaden", 3.2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "max_schaden", 3.8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "min_angriffsgeschwindigkeit", 0.45);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (110, "max_angriffsgeschwindigkeit", 0.55);

-- SEITE 3: Eisen Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('111', '2', 'IRON_SWORD', 'Rostschwert', 'Man sieht dem Schwert sein alter an! Was\n es wohl schon erlebt hat?', 1, 11);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (111, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (111, 7, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (111, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (111, 11, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (111, 22, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "kategorie_xp", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "freigeschlatet_item", 112);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "freigeschlatet_menge", 32);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "min_schaden", 3.35);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "max_schaden", 4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "min_angriffsgeschwindigkeit", 0.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (111, "max_angriffsgeschwindigkeit", 0.55);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('112', '2', 'IRON_SWORD', 'Verzaubertes Schwert', 'Eine Hexe hat dieses Ritterschwert verhext.\n Es kann jetzt noch besser werden!', 2, 12);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (112, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (112, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (112, 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (112, 23, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (112, 24, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "kategorie_xp", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "freigeschlatet_menge",0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "min_schaden", 4.2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "max_schaden", 5.1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "min_angriffsgeschwindigkeit", 0.50);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (112, "max_angriffsgeschwindigkeit", 0.62);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('113', '2', 'IRON_SWORD', 'Silber Schwert', 'Dieses Schwert ist perfekt gegen\n Algune Monster geeignet!', 1, 13);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (113, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (113, 7, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (113, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (113, 12, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (113, 19, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "kategorie_xp", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "freigeschlatet_item", 114);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "freigeschlatet_menge", 35);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "min_schaden", 4.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "max_schaden", 4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "min_angriffsgeschwindigkeit", 0.52);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (113, "max_angriffsgeschwindigkeit", 0.62);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('114', '2', 'IRON_SWORD', 'Glänzende Schwert', 'Das Funkeln dieses Schwertes\n ist meilenweit zu sehen.\n Wer es wohl poliert hat?', 1, 14);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (114, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (114, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (114, 1, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (114, 15, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (114, 25, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "freigeschlatet_item", 115);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "freigeschlatet_menge", 37);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "min_schaden", 3.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "max_schaden", 4.4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "min_angriffsgeschwindigkeit", 0.53);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (114, "max_angriffsgeschwindigkeit", 0.59);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('115', '2', 'IRON_SWORD', 'Schwert des Ritters', 'Ein mächtiges Schwert, welches\n eins von einem tapfer Ritter\n getragen wurde!', 1, 15);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (115, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (115, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (115, 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (115, 13, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (115, 24, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "freigeschlatet_item", 116);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "freigeschlatet_menge", 40);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "min_schaden", 3.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "max_schaden", 5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "min_angriffsgeschwindigkeit", 0.55);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (115, "max_angriffsgeschwindigkeit", 0.6);

-- SEITE 4: Gold Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('116', '2', 'GOLDEN_SWORD', 'Geschärftes Schwert', 'Ein durchschnittliches Schwert, welches\n so lange geschärft wurde, bis es gut war.', 1, 16);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (116, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (116, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (116, 3, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (116, 14, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (116, 17, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "freigeschlatet_item", 117);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "freigeschlatet_menge", 42);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "min_schaden", 4.1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "max_schaden", 5.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "min_angriffsgeschwindigkeit", 0.53);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (116, "max_angriffsgeschwindigkeit", 0.61);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('117', '2', 'GOLDEN_SWORD', 'Lackiertes Schwert', 'Dieses Schwert ist eigentlich ein\n Eisenschwert. Welches anschließend nur\n mit Gold überzogen wurde.', 1, 17);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (117, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (117, 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (117, 3, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (117, 13, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (117, 25, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "kategorie_xp", 17);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "freigeschlatet_item", 119);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "freigeschlatet_menge", 45);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "min_schaden", 4.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "max_schaden", 5.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "min_angriffsgeschwindigkeit", 0.55);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (117, "max_angriffsgeschwindigkeit", 0.64);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('118', '2', 'GOLDEN_SWORD', 'Schwert des Diebes', 'Dieses Schwert stammt von einem\n Dieb. Welcher sich das Schwert nur durch\n seine Beute leisten konnte!', 2, 18);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 3, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 12, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 24, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (118, 25, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "kategorie_xp", 17);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "min_schaden", 5.4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "max_schaden", 6);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "min_angriffsgeschwindigkeit", 0.63);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (118, "max_angriffsgeschwindigkeit", 0.79);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('119', '2', 'GOLDEN_SWORD', 'Königliches Schwert', 'Dieses Schwert Nutzen Könige\n bei ihren Zeremonien', 1, 19);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (119, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (119, 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (119, 3, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (119, 15, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (119, 21, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "kategorie_xp", 17);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "freigeschlatet_item", 120);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "freigeschlatet_menge", 47);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "min_schaden", 4.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "max_schaden", 5.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "min_angriffsgeschwindigkeit", 0.57);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (119, "max_angriffsgeschwindigkeit", 0.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('120', '2', 'GOLDEN_SWORD', 'Schlüssel der Schatzkammer', 'Der Legende nach kommt man mit\n diesem Schwert in jene Schatzkammer rein!', 1, 20);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (120, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (120, 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (120, 3, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (120, 11, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (120, 24, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "kategorie_xp", 18);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "freigeschlatet_item", 122);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "freigeschlatet_menge", 50);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "min_schaden", 5.1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "max_schaden", 6);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "min_angriffsgeschwindigkeit", 0.61);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (120, "max_angriffsgeschwindigkeit", 0.75);

-- SEITE 5: Diamanten Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('121', '2', 'DIAMOND_SWORD', 'Das Prachtstück', 'Dieses Schwert beinhaltet\n jeden möglichen Edelstein! ', 2, 21);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 4, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 15, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 26, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (121, 27, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "kategorie_xp", 22);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "min_schaden", 6.1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "max_schaden", 6.85);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "min_angriffsgeschwindigkeit", 0.82);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (121, "max_angriffsgeschwindigkeit", 0.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('122', '2', 'DIAMOND_SWORD', 'Splitter Schwert', 'Dieses Schwert besteht aus\n vielen kleinen Splittern!', 1, 22);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (122, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (122, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (122, 4, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (122, 12, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (122, 27, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "kategorie_xp", 18);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "freigeschlatet_item", 123);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "freigeschlatet_menge", 52);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "min_schaden", 5.6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "max_schaden", 6.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "min_angriffsgeschwindigkeit", 0.67);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (122, "max_angriffsgeschwindigkeit", 0.77);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('123', '2', 'DIAMOND_SWORD', 'Blende Schwert', 'Das Material des Schwertes ist so klar,\n dass man davon gesendet werden kann!', 1, 23);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (123, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (123, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (123, 4, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (123, 11, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (123, 18, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "kategorie_xp", 18);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "freigeschlatet_item", 124);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "freigeschlatet_menge", 55);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "min_schaden", 5.4);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "max_schaden", 6.3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "min_angriffsgeschwindigkeit", 0.67);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (123, "max_angriffsgeschwindigkeit", 0.82);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('124', '2', 'DIAMOND_SWORD', 'Schmutziges Schwert', 'Das Schwert ist schmutzig,\n dass es kaum noch erkennt.', 1, 24);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (124, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (124, 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (124, 4, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (124, 12, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (124, 23, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "kategorie_xp", 20);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "freigeschlatet_item", 125);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "freigeschlatet_menge", 57);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "min_schaden", 6.0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "max_schaden", 6.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "min_angriffsgeschwindigkeit", 0.72);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (124, "max_angriffsgeschwindigkeit", 0.85);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('125', '2', 'DIAMOND_SWORD', 'Schwert vom Schneemann', 'Ist das Schwert noch Blau oder schon Weiß?', 1, 25);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (125, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (125, 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (125, 4, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (125, 13, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (125, 26, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "kategorie_xp", 20);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "freigeschlatet_item", 126);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "freigeschlatet_menge", 60);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "min_schaden", 5.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "max_schaden", 6.7);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "min_angriffsgeschwindigkeit", 0.8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (125, "max_angriffsgeschwindigkeit", 0.87);
-- SEITE 6: Nether Schwerter
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('126', '2', 'NETHERITE_SWORD', 'Schwert der Höllenanwärter', 'Wer in die Hölle will, hat hiermit besser Karten', 1, 26);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 4, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 5, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 11, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 14, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 18, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (126, 27, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "kategorie_xp", 26);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "freigeschlatet_item", 128);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "freigeschlatet_menge", 62);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "min_schaden", 6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "max_schaden", 6.9);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "min_angriffsgeschwindigkeit", 0.79);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (126, "max_angriffsgeschwindigkeit", 0.89);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('127', '2', 'NETHERITE_SWORD', 'Feuerpest', 'Ein wirklich heißes schwert!', 2, 27);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 4, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 5, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 11, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 13, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 25, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (127, 26, 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "kategorie_xp", 30);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "min_schaden", 6.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "max_schaden", 8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "min_angriffsgeschwindigkeit", 0.78);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (127, "max_angriffsgeschwindigkeit", 0.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('128', '2', 'NETHERITE_SWORD', 'Pech Schwarzes Schwert', 'Dieses Schwert ist so schwarz, es\n absolviert sogar licht!', 1, 28);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 1, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 4, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 5, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 12, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 13, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 17, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (128, 26, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "kategorie_xp", 28);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "freigeschlatet_item", 130);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "freigeschlatet_menge", 65);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "min_schaden", 6.5);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "max_schaden", 7.1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "min_angriffsgeschwindigkeit", 0.82);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (128, "max_angriffsgeschwindigkeit", 0.89);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('129', '2', 'NETHERITE_SWORD', 'Das verschuldet Schwert', 'Der legende nach schultet, der das\n Schwert berührt. Seinem Besitzer Geld!', 2, 29);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 4, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 5, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 12, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 14, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 17, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (129, 27, 3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "kategorie_xp", 30);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "min_schaden", 6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "max_schaden", 7.2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "min_angriffsgeschwindigkeit", 0.85);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (129, "max_angriffsgeschwindigkeit", 1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('130', '2', 'NETHERITE_SWORD', 'Schwert des Teufels', 'Du hast es geschafft, das Schwert\n des Teufels nachzubauen!\n Das schaffen nur wenige.', 1, 30);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 1, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 4, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 5, 6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 11, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 13, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 17, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (130, 26, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "kategorie_xp", 28);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "freigeschlatet_item", 0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "min_schaden", 6.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "max_schaden", 7.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "min_angriffsgeschwindigkeit", 0.85);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (130, "max_angriffsgeschwindigkeit", 0.93);
