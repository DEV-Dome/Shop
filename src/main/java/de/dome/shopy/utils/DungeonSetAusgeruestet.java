package de.dome.shopy.utils;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.items.ItemVerzauberungSet;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItem;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DungeonSetAusgeruestet {

    ItemVerzauberungSet itemVerzauberungSet;
    boolean bonus1;
    boolean bonus2;

    /* nur für bestimmte Set*/
    static HashMap<UUID, ArrayList<Fox>> spielerFuechse = new HashMap<>();


    public DungeonSetAusgeruestet(ItemVerzauberungSet itemVerzauberungSet, boolean bonus1, boolean bonus2) {
        this.itemVerzauberungSet = itemVerzauberungSet;
        this.bonus1 = bonus1;
        this.bonus2 = bonus2;
    }

    public static void ladeSets(Player p) {
        PlayerInventory inv = p.getInventory();
        Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());
        Dungeon spielerDungeon = Shopy.getInstance().getSpielerDungeon().get(p.getUniqueId());

        spielerDungeon.getDungeonSetAusgeruestet().clear();
        ArrayList<ItemStack> armorContent = new ArrayList<>();
        armorContent.add(inv.getHelmet());
        armorContent.add(inv.getChestplate());
        armorContent.add(inv.getLeggings());
        armorContent.add(inv.getBoots());

        if(spielerFuechse.containsKey(p.getUniqueId())){
            for (Fox fox : spielerFuechse.get(p.getUniqueId())){
                fox.damage(500f);
                spielerFuechse.remove(fox);
            }
        }

        for (ItemStack item : armorContent) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
                ShopItem baseItem = spielerShop.getShopItemById(Integer.parseInt(item.getLore().get(0).split(":")[1].substring(1)));

                /* Prüfe ob, das Item ein Set Item ist */
                if (baseItem.getItemVerzauberungenSet() != null) {
                    ItemVerzauberungSet baseSet = baseItem.getItemVerzauberungenSet();
                    int setCount = 1;

                    if (inv.getHelmet() != null && inv.getHelmet().getType().getEquipmentSlot() != item.getType().getEquipmentSlot()) {
                        ShopItem helmItem = spielerShop.getShopItemById(Integer.parseInt(inv.getHelmet().getLore().get(0).split(":")[1].substring(1)));
                        if (helmItem.getItemVerzauberungenSet() != null && helmItem.getItemVerzauberungenSet().getId() == baseSet.getId())
                            setCount++;
                    }

                    if (inv.getChestplate() != null && inv.getChestplate().getType().getEquipmentSlot() != item.getType().getEquipmentSlot()) {
                        ShopItem ruestungsItem = spielerShop.getShopItemById(Integer.parseInt(inv.getChestplate().getLore().get(0).split(":")[1].substring(1)));
                        if (ruestungsItem.getItemVerzauberungenSet() != null && ruestungsItem.getItemVerzauberungenSet().getId() == baseSet.getId())
                            setCount++;
                    }

                    if (inv.getLeggings() != null && inv.getLeggings().getType().getEquipmentSlot() != item.getType().getEquipmentSlot()) {
                        ShopItem hosenItem = spielerShop.getShopItemById(Integer.parseInt(inv.getLeggings().getLore().get(0).split(":")[1].substring(1)));
                        if (hosenItem.getItemVerzauberungenSet() != null && hosenItem.getItemVerzauberungenSet().getId() == baseSet.getId())
                            setCount++;
                    }

                    if (inv.getBoots() != null && inv.getBoots().getType().getEquipmentSlot() != item.getType().getEquipmentSlot()) {
                        ShopItem schuheItem = spielerShop.getShopItemById(Integer.parseInt(inv.getBoots().getLore().get(0).split(":")[1].substring(1)));
                        if (schuheItem.getItemVerzauberungenSet() != null && schuheItem.getItemVerzauberungenSet().getId() == baseSet.getId())
                            setCount++;
                    }


                    if (setCount >= 4) {
                        if (spielerDungeon.getDungeonSetAusgeruestet().containsKey(baseSet.getName()))
                            spielerDungeon.getDungeonSetAusgeruestet().remove(baseSet.getName());

                        DungeonSetAusgeruestet newDungeonSetAusgeruestet = new DungeonSetAusgeruestet(baseSet, true, true);
                        spielerDungeon.getDungeonSetAusgeruestet().put(baseSet.getName(), newDungeonSetAusgeruestet);
                    } else if (setCount >= 2) {
                        if (spielerDungeon.getDungeonSetAusgeruestet().containsKey(baseSet.getName()))
                            spielerDungeon.getDungeonSetAusgeruestet().remove(baseSet.getName());

                        DungeonSetAusgeruestet newDungeonSetAusgeruestet = new DungeonSetAusgeruestet(baseSet, true, false);
                        spielerDungeon.getDungeonSetAusgeruestet().put(baseSet.getName(), newDungeonSetAusgeruestet);
                    }
                }
            }
        }

        /* Setbouns beim anlegen*/
        if (spielerDungeon.getDungeonSetAusgeruestet().containsKey("Fox Returns") && spielerDungeon.getDungeonSetAusgeruestet().get("Fox Returns").isBonus1()) {
            Shopy.getInstance().spawnFox(p);
            Shopy.getInstance().spawnFox(p);

            if (spielerDungeon.getDungeonSetAusgeruestet().containsKey("Fox Returns") && spielerDungeon.getDungeonSetAusgeruestet().get("Fox Returns").isBonus2()) {
                Shopy.getInstance().spawnFox(p);
            }
        }
    }


    public ItemVerzauberungSet getItemVerzauberungSet() {
        return itemVerzauberungSet;
    }

    public boolean isBonus1() {
        return bonus1;
    }

    public boolean isBonus2() {
        return bonus2;
    }

    public static HashMap<UUID, ArrayList<Fox>> getSpielerFuechse() {
        return spielerFuechse;
    }
}
