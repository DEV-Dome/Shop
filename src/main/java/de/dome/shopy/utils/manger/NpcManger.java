package de.dome.shopy.utils.manger;

import de.dome.shopy.Shopy;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class NpcManger {
    private static NpcManger INSTANCE;
    NPC ersteller;
    NPC dungeonHaendler;
    NPC mona;
    NPC lara;
    NPC paul;

    private NpcManger(){}

    public void spawnNPC(){
        /*NPC Config*/
        File configFile = new File(Shopy.getInstance().getDataFolder(), "npc.yml");
        FileConfiguration npcConfig = YamlConfiguration.loadConfiguration(configFile);

        /*NPC Spawnen */
        if(npcConfig.contains("npc.ersteller.location")){
            ersteller = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§aShop Verwalter");
            ersteller.getOrAddTrait(SkinTrait.class).setSkinPersistent("ShopVerwalter", "kFvWhSu+ruqbkPUdekzeNTjFC6b/I7OwGiwZhvSu9N57cxmavieuMnKvOgMb5/B04f5ZbvsfOw8nM50HFZyYcNLIZBJKfTcr+N7hg1Co0sq106fgUi1cWI/5HfyoRKhHHw4owKhH/RcqgS2U6GElVgAdO/fyJMwq2U59Vqbo9rMN3eVBLulTjAXy/IfcefeOKvoDc/CSN/gLK4inw8cSD5R86uDuXCr1hUwNQUnt9t/tLXwiQ99ktRcR6nSKnu+L6u71hE7dzRFhokc7fXab4xSdZ2YXYQVilPlNZkgQJzn0CNIbgu8ZD1nn9lYHDFcsmhhnPKyCNlP8f70ZqkGUC8BM0WFedpn3cabOUHjRYnx9OrmKpDKHjktVn+VcH6Xat/8QFwbn6X8mCjfKPBMqhx1KxRhv73TgbWRu/EEVsEnO8ZQPZvaiKEjLVSrGgJblRhN76ojvBBv6QZ8u0bo7OAPBsAXsKQzjEBOria7zb03XNY+0CaPQ+nnKgOPzts6KOG83HQxSYazkztJ0JyBmmS+PCUtHZkgd660ni+thfHlFw44A1y0HcqJ3ZE1WaJdx8XVWnEw33ZX8FLj3s9JYFyZuYTk2U0avhR59yog2ZQHtsfRhHw2U7QBXXWZ/Z1GHRQeEp+Lql3+Y1onR2OQ9mY8aHA45IeW4ZaJJS8sWKJM=", "ewogICJ0aW1lc3RhbXAiIDogMTY5ODE1NjMwMzMxMCwKICAicHJvZmlsZUlkIiA6ICJlN2E3MzZhMjFlM2I0YzA2YmVhOGVmMjVmODg0MmJhZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWVwMDIwNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZjJmNGMxNmEwNjc3YzQ0NjI4NTBhZDQxODRjZDA5MGQ0NWM0YThmNGE5ZTk4ZWNhODMyZGU4Y2M3MWYyNmM0IgogICAgfQogIH0KfQ==");

            ersteller.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.MAP));

            ersteller.spawn(npcConfig.getLocation("npc.ersteller.location"));
        }

        if(npcConfig.contains("npc.dungeonhändler.location")){
            dungeonHaendler = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§5Dungeon Händler");
            dungeonHaendler.getOrAddTrait(SkinTrait.class).setSkinPersistent("DungeonHaendler","YXhhAM+MXF8y/ZhdrLf+TwhTY4Atl+himHLknAh+YB2aUNkP7yoWeYj1hmBEOxTJoKW1JRTwcsGm9MTuySiHTES0gkmC2zwuc2nQKhe/2ScROhbG6Z3uxqeZTI2ckCxJI03dnMjeck3TWOKRiwvQng1Pl2sKfdXPHA4nBIQDF71agD62ko5J5or/uc7xcKK9YaqJA0H+PoxAt344J/egyJQGoya+3U/xvrBooEKIMLuyvlTFWnuUyJ5QL+f2bhx7L+9ko+wrby8ei7XukSqLL9A0fJkRJnreGvwlNPx/mJ9kreh5yOe2FfhmWEFlG1L/q3iE1bLwhpS2GVi+OIdMQq+GvCWE8bZzshK0weIojFxEZ2chEPl5tloaEkqCvcux4k4Ub+hNKZPYeF3XFzGQh1XSYqEbJIKkgrVKxsRiqlTMQz/rQKRYlr/mocarV835sG97PB8Ur5N39ZuS/v7sVOYJuUv2U5YjOn8FMruzdpV8XN/UxCbqrIOaaGhwanfS3FN+qCS1LFJnieM8NUS6G2e+DijNoFUqk+Ml0msFwbiZZnc1dpU65oy/ldKEEJ0S7uSRd+s05DNdoDBe5QXsYP7rsJUXcYRRkvvfbek7ZAKuH3Jh9PZ5oxHpRmGH0Ys5Phbm4A2lMunCQhS5i5qM0iF6E1OvYFBSDnrjOG+a5v4=", "ewogICJ0aW1lc3RhbXAiIDogMTYxOTIwNDU2NzU3OSwKICAicHJvZmlsZUlkIiA6ICJiYzRlZGZiNWYzNmM0OGE3YWM5ZjFhMzlkYzIzZjRmOCIsCiAgInByb2ZpbGVOYW1lIiA6ICI4YWNhNjgwYjIyNDYxMzQwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZiMjBlMDBhMGE5ZGZmMjAwNWU0OWJkMjIxNWVmYWI5N2U5NjcyYTNkMTE1NzYxOGJkODY5MjVhNWJmNDFlMTIiCiAgICB9CiAgfQp9");

            dungeonHaendler.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
            dungeonHaendler.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.SHIELD));
            dungeonHaendler.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, new ItemStack(Material.LEATHER_CHESTPLATE));
            dungeonHaendler.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, new ItemStack(Material.LEATHER_LEGGINGS));
            dungeonHaendler.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, new ItemStack(Material.LEATHER_BOOTS));

            dungeonHaendler.spawn(npcConfig.getLocation("npc.dungeonhändler.location"));
        }
        if(npcConfig.contains("npc.mona.location")){
            mona = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§dEinhorn Prinessin Mona");
            mona.getOrAddTrait(SkinTrait.class).setSkinPersistent("EinhornPrinessinMona", "J75uKO2HmSjBtST9m50VmKlMntdltIuP+tC7tim+93moC3YIe20pPm/Seld6Du8UQ+0ThGMXq5HOCW3Tq5HQbA1d2bp5bH81HxWegxmwN8QeOiiNlub+mSf9AV3R6oxmPgX+7xntfQfFnylTwOwYWsPw8ASE1Jy8FpTGxFR9vhJnNSxk7uREtIlo1G1j+8D42Cw5Ho2GuG+tBCDng4Eq03tQQeDA+IQJoRV+McOcwzGHIsh+Ky3xIHumecAOzC8Yt4EBkB34eaFRjOykdqQEXDQEK9A85lHqqFOPDPprhGCW+rk3Zx+7l/duU2s//ROuRYW6f7lenzxSIfYmnaFf5NTDYsIsaQJ61hpPjep9ThUDJ3ntaMGGevaQpcAKo6B/UFLLN/AwCrxQmXO38X+6n5Lttkw/+5LERe0YUznBaJkkJAkbQVyiTuLAwbCLF0qctRYWUj+8cMb7isOdKvTT+vlwjnS2WcWS5a8+mwJvtcCwkSOWTl4E8cFgGWdg0I6QalxMRiHhSayu2rbuKk9d6yfl1UR9lecH7aAnFuOLZVv7UHJEN4lhRugd7DhfKIHx8j0p9weesCc8xe3ABGdNkKzj/aZszqGa0Mk2obnKm7BCmGdAoze7s2ClQM4ouHSljJZKJ0z/TokJ+5VTflpK+e2d8jsOJy+XS0G0ENJDzkE=", "eyJ0aW1lc3RhbXAiOjE1ODgwMTM1OTc3OTcsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84NzgyNTkwMWJlMjZkOWM4OWIxN2Q1MjZjYTI4NTUwMjZhMDg0NjZiYjRkNGFhMDAzZmJhMjIxMjZiNTk0NDM3IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=");

            mona.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.NETHER_STAR));
            mona.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.TOTEM_OF_UNDYING));
            mona.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, new ItemStack(Material.DIAMOND_CHESTPLATE));

            mona.spawn(npcConfig.getLocation("npc.mona.location"));
        }
        if(npcConfig.contains("npc.lara.location")){
            lara = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§bIngenieurin Lara");
            lara.getOrAddTrait(SkinTrait.class).setSkinPersistent("IngenieurinLara", "GuBNHMNv+87lWn6L9MFD4ChYzbZByBYxD9Qc5wIOQT/tGpT/6PSTt7xTPTLr+UfnjxfEW0iNB5MM9DIxJTymxKxyN7cGcTJhqxvoq1RNFlKlSxvjorjXLL3BwQMO4SFlLd4vNKYCZMHC0SG1lGg3lrX3EYE7zSMCiNqizn4/NDjntLTO4sORaLf2mvxznODUvIfayJs5gizTzGgtZ5yoibc4iUxX/zuvkxxASsedEAd5ePXkmIE3Ujo73v62c42Z0A7aDwFK+J5aqjBvhLxrmnroGovB7Eah3APoh8sv5z7b9SsOfZOD2GEA2SZSxmwSUYEbqNk1hDQp9AbttxCdbZcHnp8KODXZyDWFuzvs/F0ZDmdRF++8OnmVuU81J5qSUtGj+MBetwfasa/yqatqULJz8hkwf9FrS5/WYhzf0W5ZsiGrlZhfcy6RBV2YieXaSNP6zijY2Pltc0ft4K796gIjUcLWhveJRC4+4AG1SS35UeZScaf75hCD0hwZ8GUJI4Xeua1iA8/wyFn/yFTSLoYJBzFcdawOZ8N6w0dnS6ceD8AykdzYnWfvkAFXagAQ/mn9zmJE82lGR3/9zGSEAIve+c1Y1YJhua0C5NNxFS2NMma9vOpJhVkWAjKCzFOfM0tlgoU9m/oALAzqSBWGTExgzFqbg3gXJ7K0HvVZUjU=", "ewogICJ0aW1lc3RhbXAiIDogMTczMzc3OTA5ODI5MywKICAicHJvZmlsZUlkIiA6ICJkNzliZjAzNDE4YzU0MDUyOGEzZDM4ZTQ4NTAwYzBhOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJMYXJhXzE5MTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I1OTgxZTMwZWYxMDVlNzc3NDZkZWQ1NDI4MTE0YmNiYTI2NDRmNWE4NDk3MGY5OGIxZDA4OTMwMzhiOTk4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");
            lara.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.ENCHANTING_TABLE));

            lara.spawn(npcConfig.getLocation("npc.lara.location"));
        }
        if(npcConfig.contains("npc.paul.location")){
            paul = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§2Handwerksmeister Paul");
            paul.getOrAddTrait(SkinTrait.class).setSkinPersistent("HandwerksmeisterPaul", "LosKgGYp4uLP27HXOnLtqTGvGsgypLmjR0PAjwr7kgWw3jYLRORqU0nuLizYAPy3XHg1AnsHu608ATzNSqBoP9nneMVYjeX+ivrutqfaFJB4Wqr+bvFJ0WwERSorMJ52swqS29zFLilJcpB8zM1Qvrc0Jg1gW/D6RSSKUcA/dAQXgRqtTvX04iGcXKM0Bkp/vM/CshjCPAQOwt68pbZvT29bEdSiYgvV+xe8scV534oGMslwexGlPIiKcaXV0vEVO2BwsHP/pQcGBqh4btHNUBJRsOuj0SPTC3Bt9jBleeFCxYERLApDyObRAJUuUDAp+hSSjvwEdG1ZDvW84ozGDAw1Ic7aI+9wVbL5OPvthrteZqVGPBpI0HU1eIvG5o4YgTe1oCkq1hQaC3icLWh3VfK/ylAY6k5lszZJ1v0r0IwyT6YKYytusgibWb91kguKgX8YeVXX2HtK4CRtDiQb5qMZ7jFG+3BzhR51A3W9iNR4HBCS+GwndtSFKKcSitdWFp3hfvP3v/CzLZ20uXveGLGJDBX0hQ2Z4CrzV+qXDpYrpNagJqdC7BjpMs7/PhoQm1tkL96N8RHa1Z1UzG/n1NndWvd/JZ9PQ2xwZTq2Q7kZx93+Y2L+cpLWq4TEg6FmACUcNtb9ukgYYBvMGK9msqXo0PzQBduiVwyrzBOasMg=", "ewogICJ0aW1lc3RhbXAiIDogMTYyOTEwNDkzODk4NCwKICAicHJvZmlsZUlkIiA6ICIxYTc1ZTNiYmI1NTk0MTc2OTVjMmY4NTY1YzNlMDAzZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUZXJvZmFyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzk4ZWI5NGUwZWExMTM1NWVlZmViOTg0YTdkNmY2NDYzMTE1N2E1N2U0ZTE2YWRmM2Y1OTg5ZWZjNjkzN2ZiMWUiCiAgICB9CiAgfQp9");

            paul.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.DIAMOND));
            paul.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.OAK_WOOD));

            paul.spawn(npcConfig.getLocation("npc.paul.location"));
        }
    }

    public void loescheAlleNpc(){
        for (NPCRegistry npcRegistry : CitizensAPI.getNPCRegistries()){
            for(NPC npc : npcRegistry.sorted()){
                npc.despawn();
                npc.destroy();
            }
            npcRegistry.saveToStore();
        }
    }

    public static NpcManger INSTANCE() {
        if(INSTANCE == null) INSTANCE = new NpcManger();

        return INSTANCE;
    }

    public NPC getErsteller() {
        return ersteller;
    }

    public NPC getDungeonHaendler() {
        return dungeonHaendler;
    }

    public NPC getMona() {
        return mona;
    }

    public NPC getLara() {
        return lara;
    }

    public NPC getPaul() {
        return paul;
    }
}
