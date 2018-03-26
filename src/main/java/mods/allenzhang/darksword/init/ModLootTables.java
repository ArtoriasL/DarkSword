package mods.allenzhang.darksword.init;

import mods.allenzhang.darksword.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.HashSet;
import java.util.Set;

import static mods.allenzhang.darksword.init.ModLootTables.RegistrationHandler.create;

public class ModLootTables {

    public static final ResourceLocation ENTITIES_MRQUINFAKE = create("entities/mrquinfake");
    public static final ResourceLocation CHESTS_STRONGHOLD = create("chests/stronghold");

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
}
