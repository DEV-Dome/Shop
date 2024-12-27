package de.dome.shopy.listener.lobby.clicklistener;

import de.dome.shopy.Shopy;
import de.dome.shopy.utils.Ressource;
import de.dome.shopy.utils.manger.NpcManger;
import de.dome.shopy.utils.shop.Shop;
import de.dome.shopy.utils.shop.ShopItemVorlage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListenerSiegfried implements Listener {

    public InventoryClickListenerSiegfried() {
        Shopy.getInstance().getServer().getPluginManager().registerEvents(this, Shopy.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getCurrentItem() == null) return;
        if (!e.getCurrentItem().hasItemMeta()) return;
        if(!Shopy.getInstance().getSpielerShops().containsKey(e.getWhoClicked().getUniqueId())) return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Shop spielerShop = Shopy.getInstance().getSpielerShops().get(p.getUniqueId());

        if (e.getView().getTitle().equals(NpcManger.INSTANCE().getSiegfried().getFullName())) {
            if(item.getItemMeta().getDisplayName().startsWith("§9Dungeon Schlüssel Stufe")){
                p.sendMessage(Shopy.getInstance().getPrefix() + "Diese Funktion wird später hinzugefügt!");
                return;
            }
            if(item.getItemMeta().getDisplayName().contains("§9Unbekanntes Herstellungsrezept freischalten")) {
                int spielerSchriftrollenpapier = spielerShop.getRessourenShopManger().getRessourceValue(Ressource.getRessoureByName("Schriftrollenpapier"));
                if(spielerSchriftrollenpapier <= 0){
                    int zuWenig = 1 - spielerSchriftrollenpapier;
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Leider reichen dein Schriftrollenpapier dafür nicht aus! Dir fehlt noch §e" + zuWenig + " Schriftrollenpapier.");
                    return;
                }

                spielerShop.getRessourenShopManger().setRessourcenValue(Ressource.getRessoureByName("Schriftrollenpapier"), (spielerSchriftrollenpapier - 1));
                ShopItemVorlage neuesItem =  spielerShop.schalteNeuesSchriftrollenItemFrei();

                if(neuesItem != null){
                    p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP,  1,1);
                    p.sendMessage(Shopy.getInstance().getPrefix() + "Du hast das Herstellungsrezept: §e" + neuesItem.getItem().getName() + " §7freigeschaltet");
                }else {
                    p.sendMessage(Shopy.getInstance().getPrefix() + "§cHierbei ist ein Fehler aufgetreten, versuche es bitte später noch einmal oder wende dich an den Support!");
                }
            }
        }
    }
}
