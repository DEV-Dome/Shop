package de.dome.shopy.utils.shop;

public class ShopItemKategorieLevel {

    int level;
    int xpZumNachstenLevel;
    int aktuelleXP;

    public ShopItemKategorieLevel(int level, int xpZumNachstenLevel, int aktuelleXP) {
        this.level = level;
        this.xpZumNachstenLevel = xpZumNachstenLevel;
        this.aktuelleXP = aktuelleXP;
    }

    public int getLevel() {
        return level;
    }

    public int getXpZumNachstenLevel() {
        return xpZumNachstenLevel;
    }

    public int getAkeulleXP() {
        return aktuelleXP;
    }
}
