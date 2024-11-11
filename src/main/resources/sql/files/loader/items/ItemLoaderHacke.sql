-- SEITE 1: Holz Hacke (Angriffsgeschwindigkeit)
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('200', '3', 'WOODEN_HOE', 'Zerbrochene Hacke', 'Diese Hacke hat die besten\n Tage schon hinter sich.', 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (200, 6, 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "immer_freigeschlatet", "ja");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "freigeschlatet_item", 202);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "freigeschlatet_menge", 10);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "min_schaden", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "max_schaden", 0.25);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "min_angriffsgeschwindigkeit", 0.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (200, "max_angriffsgeschwindigkeit", 0.75);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('202', '3', 'WOODEN_HOE', 'Stumpfe Hacke', 'Ja, diese Hacke könnte man\n mal wieder schleifen!', 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (202, 6, 5);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "kategorie_xp", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "freigeschlatet_item", 204);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "freigeschlatet_menge", 15);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "min_schaden", 0.15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "max_schaden", 0.4);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "min_angriffsgeschwindigkeit", 0.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (202, "max_angriffsgeschwindigkeit", 0.75);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('203', '3', 'WOODEN_HOE', 'Spielzeug Hacke 1', 'Kinder nutzen dieses Axt oft zum Spielen.\n Weil Sie so harmlos ist, geben Eltern \n Sie gerne ihren Kindern', 2, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (203, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (203, 15, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (3, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "min_schaden", 0.1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "max_schaden", 0.2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "min_angriffsgeschwindigkeit", 0.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (203, "max_angriffsgeschwindigkeit", 0.45);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('204', '3', 'WOODEN_HOE', 'Frosch Hacke', 'Diese Waffe, Lage lange an\n einem Froschteich begraben', 1, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (204, 6, 6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "kategorie_xp", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "freigeschlatet_item", 205);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "min_schaden", 0.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "max_schaden", 0.6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "min_angriffsgeschwindigkeit", 0.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (204, "max_angriffsgeschwindigkeit", 0.8);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('205', '3', 'WOODEN_HOE', 'Holzfäller Hilfe', 'Holzfäller nutzen dieses Werkzeug\n gerne, als Hilfe um im Wald voranzukommen!', 1, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (205, 6, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (205, 12, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "kategorie_xp", 5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "freigeschlatet_item", 206);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "freigeschlatet_menge", 20);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "min_schaden", 0.45);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "max_schaden", 0.8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "min_angriffsgeschwindigkeit", 0.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (205, "max_angriffsgeschwindigkeit", 1);

-- SEITE 2: Stein Hacke
    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('206', '3', 'FLINT', 'Kiesel Stein', 'Ob diese kleinen Steine weh tun können?', 1,  6);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (206, 7, 4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "freigeschlatet_item", 207);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "freigeschlatet_menge", 22);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "min_schaden", 0.85);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "max_schaden", 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "min_angriffsgeschwindigkeit", 0.8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (206, "max_angriffsgeschwindigkeit", 1.2);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('207', '3', 'STONE_HOE', 'Hacke aus Diorit', 'Diese Weiße Hacke glänzt schon fast!', 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (207, 6, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (207, 7, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (207, 14, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "kategorie_xp", 8);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "freigeschlatet_item", 208);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "freigeschlatet_menge", 23);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "min_schaden", 0.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "max_schaden", 1.25);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "min_angriffsgeschwindigkeit", 1.2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (207, "max_angriffsgeschwindigkeit", 1.5);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('208', '3', 'STONE_HOE', 'Trümmer Hacke', 'Diese Hacke besteht zu 99,87 % aus Trümmer Steinen!', 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (208, 6, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (208, 7, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (208, 13, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "kategorie_xp", 209);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "immer_freigeschlatet", "nein");


        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "freigeschlatet_item", 210);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "freigeschlatet_menge", 25);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "min_schaden", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "max_schaden", 1.4);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "min_angriffsgeschwindigkeit", 0.95);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (208, "max_angriffsgeschwindigkeit", 1.3);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('209', '3', 'STONE_HOE', 'Jakobs Hacke', 'Jakob soll eins, Kürbisse mit dieser Hacke angebaut haben!', 2, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (209, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (209, 7, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (209, 12, 2);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "kategorie_xp", 9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "freigeschlatet_item", ,0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "min_schaden", 1.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "max_schaden", 1.7);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "min_angriffsgeschwindigkeit", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (209, "max_angriffsgeschwindigkeit", 1.4);

    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('210', '3', 'STONE_HOE', 'Bergbau Hacke', 'Mit dieser Hacke wurde eins nicht nur Stein abgebaut.', 1, 10);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (210, 6, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (210, 7, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (210, 11, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (210, 13, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "kategorie_xp", 10);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "freigeschlatet_item", 212);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "freigeschlatet_menge", 30);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "min_schaden", 1.25);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "max_schaden", 1.8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "min_angriffsgeschwindigkeit", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (210, "max_angriffsgeschwindigkeit", 1.5);

-- SEITE 3: Eisen Hacke
    INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('211', '3', 'IRON_HOE', 'Gepitze Hacke', 'Der Stab der Hacke wurde angespitzt.', 1, 11);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (211, 6, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (211, 1, 9);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (211, 12, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (211, 24, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "kategorie_xp", 12);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211,1, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "freigeschlatet_typ", "ITEM");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "freigeschlatet_item", ,0);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "freigeschlatet_menge", 0);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "min_schaden", 1.9);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "max_schaden", 2.1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "min_angriffsgeschwindigkeit", 1.85);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (211, "max_angriffsgeschwindigkeit", 2.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('212', '3', 'IRON_HOE', 'Braune Hacke', 'Welches Feld wohl damit umgegraben wurde?', 1, 12);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (212, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (212, 1, 2);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (212, 14, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (212, 17, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "kategorie_xp", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "freigeschlatet_item", 213);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "freigeschlatet_menge", 34);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "min_schaden", 1.95);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "max_schaden", 2.3);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "min_angriffsgeschwindigkeit", 1.1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (212, "max_angriffsgeschwindigkeit", 1.7);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('213', '3', 'IRON_HOE', 'Spiegelnde Hacke', 'Diese Hacke wurde lange Puliert!', 1, 13);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (213, 6, 3);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (213, 1, 5);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (213, 20, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (213, 22, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "kategorie_xp", 14);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "freigeschlatet_item", 214);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "freigeschlatet_menge", 35);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "min_schaden", 2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "max_schaden", 2.8);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "min_angriffsgeschwindigkeit", 1.25);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (213, "max_angriffsgeschwindigkeit", 1.75);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('214', '3', 'IRON_HOE', 'Heulende Hacke', 'Diese Hacke Heult seinen Besitzer\n die ganze Zeit zu!', 1, 14);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (214, 6, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (214, 1, 7);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (214, 21, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (214, 24, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "freigeschlatet_item", 215);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "freigeschlatet_menge", 37);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "min_schaden", 2.3);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "max_schaden", 2.5);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "min_angriffsgeschwindigkeit", 1.45);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (214, "max_angriffsgeschwindigkeit", 1.85);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('215', '3', 'IRON_HOE', 'Stahl Hacke', 'Ist das noch Eisen oder schon Stahl ?', 1, 15);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (215, 6, 4);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (215, 1, 8);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (215, 19, 1);
        INSERT INTO item_kosten (item, ressource, menge) VALUES (215, 20, 1);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "shop_xp", 1);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "kategorie_xp", 15);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "meister_menge", 500);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "immer_freigeschlatet", "nein");

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "freigeschlatet_typ", "STANDART");
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "freigeschlatet_item", 216);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "freigeschlatet_menge", 40);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "min_schaden", 2.2);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "max_schaden", 2.7);

        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "min_angriffsgeschwindigkeit", 1.6);
        INSERT INTO item_werte (item, schlussel, inhalt) VALUES (215, "max_angriffsgeschwindigkeit", 2);

-- SEITE 4: Gold Hacken
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('216', '3', 'GOLDEN_HOE', 'Rest Hacke', 'Diese Hacke wurde aus den Goldresten\neines Mannes geschaffen?', 1, 16);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (216, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (216, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (216, 3, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (216, 16, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (216, 22, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "kategorie_xp", 15);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "freigeschlatet_item", 217);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "freigeschlatet_menge", 42);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "min_schaden", 2.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "max_schaden", 2.8);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "min_angriffsgeschwindigkeit", 1.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (216, "max_angriffsgeschwindigkeit", 2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('217', '3', 'GOLDEN_HOE', 'Hacke des Schweins', 'Schweine in der Hölle tragen diese Hacke', 2, 17);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (217, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (217, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (217, 3, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (217, 11, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (217, 20, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "freigeschlatet_item", 218);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "freigeschlatet_menge", 43);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "min_schaden", 2.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "max_schaden", 2.9);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "min_angriffsgeschwindigkeit", 1.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (217, "max_angriffsgeschwindigkeit", 2.15);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('218', '3', 'GOLDEN_HOE', 'Echtgold Hacke', 'Diese Hacke besteht aus echtem Gold!', 1, 18);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (218, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (218, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (218, 3, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (218, 16, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (218, 14, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "freigeschlatet_item", 219);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "freigeschlatet_menge", 45);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "min_schaden", 2.65);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "max_schaden", 3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "min_angriffsgeschwindigkeit", 2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (218, "max_angriffsgeschwindigkeit", 2);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('219', '3', 'GOLDEN_HOE', 'Hacke des Goblins', 'Diese Hacke wurde von einem\n Goblin geschmiedet!', 1, 19);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (219, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (219, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (219, 3, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (219, 18, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (219, 23, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "freigeschlatet_item", 220);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "freigeschlatet_menge", 47);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "min_schaden", 1.3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "max_schaden", 1.55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "min_angriffsgeschwindigkeit", 2.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (219, "max_angriffsgeschwindigkeit", 3.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('220', '3', 'GOLDEN_HOE', 'Hacke des Königs', 'Mit dieser Hacke wurde eins das Gemüse\n für einen König angebaut!', 1, 20);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 3, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 21, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 15, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (220, 19, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "kategorie_xp", 17);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "freigeschlatet_item", 221);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "freigeschlatet_menge", 50);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "min_schaden", 2.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "max_schaden", 3.3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "min_angriffsgeschwindigkeit", 2.4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (220, "max_angriffsgeschwindigkeit", 2.5);

-- SEITE 5: Diamanten Hacke
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('221', '3', 'DIAMOND_HOE', 'Hacke der Sternenbild', 'Der Legende nach spiegeln sich\n die Sternbilder in dieser Hacke.', 1, 21);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 4, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 12, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 20, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (221, 25, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "kategorie_xp", 18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "freigeschlatet_item", 222);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "freigeschlatet_menge", 52);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "min_schaden", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "max_schaden", 3.55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "min_angriffsgeschwindigkeit", 2.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (221, "max_angriffsgeschwindigkeit", 2.6);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('222', '3', 'DIAMOND_HOE', 'Merkus Hacke', 'Diese Hacke war Merkus einziger Besitzt', 1, 22);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 4, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 16, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 26, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (222, 19, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "kategorie_xp", 18);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "freigeschlatet_item", 224);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "freigeschlatet_menge", 55);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "min_schaden", 3.05);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "max_schaden", 3.7);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "min_angriffsgeschwindigkeit", 2.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (222, "max_angriffsgeschwindigkeit", 2.9);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('223', '3', 'DIAMOND_HOE', 'Obsidian Brecher', 'Diese Hacke kann sogar Obsidian brechen.', 2, 23);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 4, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 14, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (223, 20, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (223, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "min_schaden", 3.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "max_schaden", 4);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "min_angriffsgeschwindigkeit", 2.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "max_angriffsgeschwindigkeit", 3.6);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('224', '3', 'DIAMOND_HOE', 'Minen Hacke', 'Diese Hacke wurde in einer\n Verlassen Mine gefunden.', 1, 24);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 4, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 12, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (224, 11, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "kategorie_xp", 22);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "freigeschlatet_item", 225);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "freigeschlatet_menge", 57);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "min_schaden", 3.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "max_schaden", 3.9);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "min_angriffsgeschwindigkeit", 3);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (224, "max_angriffsgeschwindigkeit", 3.25);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('225', '3', 'DIAMOND_HOE', 'Echt Holz Hacke', 'Der Stab der Hacke ist aus massiven Holz!', 1, 25);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 4, 5);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 15, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 25, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (225, 17, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "freigeschlatet_item", 225);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "freigeschlatet_menge", 60);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "min_schaden", 3.6);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "max_schaden", 4.1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "min_angriffsgeschwindigkeit", 2.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (225, "max_angriffsgeschwindigkeit", 3.5);

-- SEITE 6: Nether Hacke
INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('226', '3', 'NETHERITE_HOE', 'Hacke der Schweine Muskeln', 'Diese Hacke besteht aus\n Schweinemuskeln aus der Hölle!', 1, 26);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 6, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 5, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 29, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 19, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (226, 11, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "kategorie_xp", 20);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "freigeschlatet_item", 228);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "freigeschlatet_menge", 62);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "min_schaden", 3.9);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "max_schaden", 4.15);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "min_angriffsgeschwindigkeit", 3.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (226, "max_angriffsgeschwindigkeit", 3.75);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('227', '3', 'NETHERITE_HOE', 'Hacke des Todeslied', 'Diese Hacke singt das Lied des Todes!', 2, 27);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 1, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 4, 4);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 5, 7);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 18, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 17, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 13, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (227, 17, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "kategorie_xp", 30);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "min_schaden", 4.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "max_schaden", 4.7);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "min_angriffsgeschwindigkeit", 3.8);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (227, "max_angriffsgeschwindigkeit", 4.1);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('228', '3', 'NETHERITE_INGOT', 'Unfertige Hacke', 'Dieser Stein wurde aus einer\n mächtigen Festung geklaut!', 1, 28);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 6, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 4, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 5, 6);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 13, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 24, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (228, 19, 1);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "kategorie_xp", 28);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "freigeschlatet_item", 230);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "freigeschlatet_menge", 65);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "min_schaden", 3.7);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "max_schaden", 4.3);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "min_angriffsgeschwindigkeit", 3.5);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (228, "max_angriffsgeschwindigkeit", 3.85);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('229', '3', 'NETHERITE_HOE', 'Hacke des Sensenmannes', 'Die Hacke schwingt so schnell\n und bringt dabei den Tod!', 2, 29);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 6, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 1, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 4, 3);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 5, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 17, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 11, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 19, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (229, 29, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "kategorie_xp", 30);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "freigeschlatet_typ", "ITEM");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "min_schaden", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "max_schaden", 4.6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "min_angriffsgeschwindigkeit", 4);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (229, "max_angriffsgeschwindigkeit", 4.5);

INSERT INTO item (id, item_kategorie, icon, name, beschreibung, item_seltenheit, reinfolge) VALUES ('230', '3', 'NETHERITE_HOE', 'Schwarze Hacke', 'Von der ganzen Feldarbeit ist\n diese Hacke pechschwarz', 1, 30);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 6, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 1, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 4, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 5, 8);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 12, 1);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 16, 2);
    INSERT INTO item_kosten (item, ressource, menge) VALUES (230, 25, 2);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "shop_xp", 1);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "kategorie_xp", 28);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "meister_menge", 500);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "immer_freigeschlatet", "nein");

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "freigeschlatet_typ", "STANDART");
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "freigeschlatet_item", 0);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "freigeschlatet_menge", 0);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "min_schaden", 4.2);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "max_schaden", 4.6);

    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "min_angriffsgeschwindigkeit", 3.7);
    INSERT INTO item_werte (item, schlussel, inhalt) VALUES (230, "max_angriffsgeschwindigkeit", 4);