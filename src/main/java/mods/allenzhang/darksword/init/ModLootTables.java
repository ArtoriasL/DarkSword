package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.HashSet;
import java.util.Set;

import static mods.allenzhang.darksword.init.ModLootTables.RegistrationHandler.create;

public class ModLootTables {

    public static final ResourceLocation ENTITIES_MRQUINFAKE = create("entities/mrquinfake");
    public static final ResourceLocation CHESTS_SOULS = create("chests/souls");
    public static final ResourceLocation CHESTS_SHARDS = create("chests/shards");
    /**
     * Register this mod's {@link LootTable}s.
     */
    public static void registerLootTables() {
        RegistrationHandler.LOOT_TABLES.forEach(LootTableList::register);
    }

    public static class RegistrationHandler {

        private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();
        /**
         * Create a {@link LootTable} ID.
         *
         * @param id The ID of the LootTable without the testmod3: prefix
         * @return The ID of the LootTable
         */
        protected static ResourceLocation create(final String id) {
            final ResourceLocation lootTable = new ResourceLocation(Reference.MODID, id);
            RegistrationHandler.LOOT_TABLES.add(lootTable);
            return lootTable;
        }
    }

    public final static void CheckChest(LootTable lt,ResourceLocation cname){
        //souls
        if(
                cname.equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)||
                cname.equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)||
                cname.equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY)||
                cname.equals(LootTableList.CHESTS_SIMPLE_DUNGEON)||
                cname.equals(LootTableList.CHESTS_WOODLAND_MANSION)||
                cname.equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)||
                cname.equals(LootTableList.CHESTS_DESERT_PYRAMID)||
                cname.equals(LootTableList.CHESTS_END_CITY_TREASURE)||
                cname.equals(LootTableList.CHESTS_IGLOO_CHEST)||
                cname.equals(LootTableList.CHESTS_JUNGLE_TEMPLE)||
                cname.equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)||
                cname.equals(LootTableList.CHESTS_NETHER_BRIDGE)||
                cname.equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)
                ){
            lt.addPool(GetPoolByLoot(ModLootTables.CHESTS_SOULS,1));
        }

        //shards
        if(cname.equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)||
                cname.equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)||
                cname.equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY)||
                cname.equals(LootTableList.CHESTS_END_CITY_TREASURE)||
                cname.equals(LootTableList.CHESTS_NETHER_BRIDGE)
                ){
            lt.addPool(GetPoolByLoot(ModLootTables.CHESTS_SHARDS,1));
        }
    }

    private final static LootPool GetPoolByLoot(ResourceLocation loot,float random){
        final String name = loot.toString();
        final LootEntry entry = new LootEntryTable(loot,1,0,new LootCondition[0],name);
        final RandomValueRange rolls = new RandomValueRange(random,1);
        return new LootPool(new LootEntry[]{entry},new LootCondition[0],rolls,rolls,name);
    }
}
