/* STANDART Ressource */

INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('IRON_INGOT', 'Eisen', 'Ein grundlegendes Material', 'STANDART', 1, '12', '22');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('COPPER_INGOT', 'Kupfer', 'Ein grundlegendes Material', 'STANDART', 2, '15', '30');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('GOLD_INGOT', 'Gold', 'Ein grundlegendes Material', 'STANDART', 3, '17', '35');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('DIAMOND', 'Diamanten', 'Ein grundlegendes Material', 'STANDART', 4, '30', '50');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('NETHERITE_INGOT', 'Netherite', 'Ein grundlegendes Material', 'STANDART', 5, '45', '70');

INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('OAK_WOOD', 'Holz', 'Ein grundlegendes Material', 'STANDART', 6, '5', '15');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('STONE', 'Stein', 'Ein grundlegendes Material', 'STANDART', 7, '8', '18');

INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('LEATHER', 'Leder', 'Ein grundlegendes Material', 'STANDART', 8, '8', '18');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('RABBIT_HIDE', 'Besonders Leder', 'Ein grundlegendes Material', 'STANDART', 9, '12', '24');

INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('NETHER_STAR', 'Geld', 'Geld, zum Handeln auf der ganzen Welt', 'WAHRUNG', 100, '0', '0');


/* DUNGEON-LOOT Ressource: Standart Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('PHANTOM_MEMBRANE', 'Monsterhaut', 'Diese Haut hat mal ein Monster geschützt,\n wofür wollt ihr sie jetzt verwenden?\n\n(Kann von allen Monstern fallen gelassen werden)', 'DUNGEON-LOOT', 1, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('SWEET_BERRIES', 'Monsterbeeren', 'Diese Beeren dienen als Grundnahrungsmittel für\n viele Monster. Der Saft soll mythische Kräfte haben.\n\n(Kann von allen Monstern fallen gelassen werden)', 'DUNGEON-LOOT', 2, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('STRING', 'Monsterfaden', 'Dieser Faden wurde direkt aus dem Gewebe\n eines Monsters entnommen. Und ist Äußeres\n nützlich darin, Waffen zusammenzuhalten.\n\n(Kann von allen Monstern fallen gelassen werden)', 'DUNGEON-LOOT', 3, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('ENDER_EYE', 'Monsteraugen', 'Dieses Auge wurde säuberlich aus\n dem Kopf eines Monsters getrennt.\n\n(Kann von allen Monstern fallen gelassen werden)', 'DUNGEON-LOOT', 4, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('ROTTEN_FLESH', 'Monsterfleisch', 'Das Fleisch eines Monsters ist\n keine geeignete Mahlzeit.\n\n(Kann von allen Monstern fallen gelassen werden)', 'DUNGEON-LOOT', 5, '0', '0');

/* DUNGEON-LOOT Ressource: Zombie Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('SLIME_BALL', 'Zombiesleim', 'Ein ätzender Schleim der alles zersetzten kann.\n(Kann von Zombies fallen gelassen werden)', 'DUNGEON-LOOT', 6, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('CHARCOAL', 'Zombiedreck', 'Die Hinterlassenschaften eines Zombies.\n(Kann von Zombies fallen gelassen werden)', 'DUNGEON-LOOT', 7, '0', '0');

/* DUNGEON-LOOT Ressource: Skelett Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('BONE', 'Skelett Arm', 'Wenn dieser Arm wohl mal gehört hat ?\n(Kann von Skeletten fallen gelassen werden)', 'DUNGEON-LOOT', 8, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('DISC_FRAGMENT_5', 'Pfeilreste', 'Das war mal ein Pfeil.\n(Kann von Skeletten fallen gelassen werden)', 'DUNGEON-LOOT', 9, '0', '0');

/* DUNGEON-LOOT Ressource: Spinnen Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('SPIDER_EYE', 'Spinnenauge', 'Die 8 Augen starren immer noch Leute an.\n(Kann von Spinnen fallen gelassen werden)', 'DUNGEON-LOOT', 10, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('RABBIT_HIDE', 'Hasenfell', 'Ob der Rest des Hasen gefressen wurde?\n(Kann von Spinnen fallen gelassen werden)', 'DUNGEON-LOOT', 11, '0', '0');

/* DUNGEON-LOOT Ressource: Ertrunken Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('STICK', 'Stab eines Dreistacks', 'Dieser Stab hat eines zu einem\n Mächtigen Dreistacks gehört.\n\n(Kann von Ertrunkenen fallen\n gelassen werden)', 'DUNGEON-LOOT', 12, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('PRISMARINE_CRYSTALS', 'Sauerstoffkristall', 'In diesem Stein wird Sauerstoff gespeichert.\n(Kann von Ertrunkenen fallen gelassen werden)', 'DUNGEON-LOOT', 13, '0', '0');

/* DUNGEON-LOOT Ressource: Vindicator Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('EGG', 'Ei', 'Dieses Ei wollte sein Meister frühstücken\n(Kann von Dienern fallen gelassen werden)', 'DUNGEON-LOOT', 14, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('CLAY_BALL', 'Tonklumpen', 'Ein gewöhnlicher Ton klumpen,\n was sich daraus nur machen lässt?\n\n(Kann von Dienern fallen gelassen werden)', 'DUNGEON-LOOT',15, '0', '0');

/* DUNGEON-LOOT Ressource: Balze Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('BLAZE_ROD', 'Feuerstab', 'Dieser Stab scheint noch zu brennen!\n(Kann von Blazes fallen gelassen werden)', 'DUNGEON-LOOT', 16, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('COPPER_INGOT', 'Verbranntest Eisen','Ein Stück Erz welches,\n offensichtlich noch brennt\n\n(Kann von Blazes fallen gelassen werden)', 'DUNGEON-LOOT',17, '0', '0');

/* DUNGEON-LOOT Ressource: EVOKER Loot */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('GLOW_INK_SAC', 'Zauberstaub', 'Ein Magisterstaub, wer weiß,\n was man damit alles machen kann!\n\n(Kann von Magierinnen fallen gelassen werden)', 'DUNGEON-LOOT', 18, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('DRAGON_BREATH', 'Drachenatem','Dieser Atem glüht immer noch\n(Kann von Magierinnen fallen gelassen werden)', 'DUNGEON-LOOT',19, '0', '0');



/* SPECIAL Ressource */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('ECHO_SHARD', 'Mondkristall', 'Ein mächtiger Kristall, welcher Bei Mona gegen starke Geschenke eingetauscht werden kann.', 'SPECIAL', 1, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('TRIPWIRE_HOOK', 'Dungeon Schüssel der Stufe 1', 'Mit diesem Schüssel kannst du einen Dungeon der Stufe 1 Betreten', 'SPECIAL', 2, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('TRIPWIRE_HOOK', 'Dungeon Schüssel der Stufe 2', 'Mit diesem Schüssel kannst du einen Dungeon der Stufe 2 Betreten', 'SPECIAL', 3, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('TRIPWIRE_HOOK', 'Dungeon Schüssel der Stufe 3', 'Mit diesem Schüssel kannst du einen Dungeon der Stufe 3 Betreten', 'SPECIAL', 4, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('TRIPWIRE_HOOK', 'Dungeon Schüssel der Stufe 4', 'Mit diesem Schüssel kannst du einen Dungeon der Stufe 4 Betreten', 'SPECIAL', 5, '0', '0');


/* AUFWERTER Ressource */
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('LIME_DYE', 'Aufwärtspulver Stufe 1', 'Mit diesem Pulver können Gegenstände aufgewertet werden', 'AUFWERTER', 1, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('BLUE_DYE', 'Aufwärtspulver Stufe 2', 'Mit diesem Pulver können Gegenstände aufgewertet werden', 'AUFWERTER', 2, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('MAGENTA_DYE', 'Aufwärtspulver Stufe 3', 'Mit diesem Pulver können Gegenstände aufgewertet werden', 'AUFWERTER', 3, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('ORANGE_DYE', 'Aufwärtspulver Stufe 4', 'Mit diesem Pulver können Gegenstände aufgewertet werden', 'AUFWERTER', 4, '0', '0');
INSERT INTO ressource (icon, name, beschreibung, typ, reinfolge, minimale_kosten, maximale_kosten) VALUES ('PAPER', 'Schriftrollenpapier', 'Auf dieses Papier können neue Entwürfe gezeichnet werden', 'AUFWERTER', 5, '0', '0');


