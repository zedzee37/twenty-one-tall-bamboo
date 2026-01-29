package io.github.zedzee37.totbamboo.datagen;

import io.github.zedzee37.totbamboo.TTB;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collections;
import java.util.stream.Collectors;

public class TTBBlockLootSubProvider extends BlockLootSubProvider {
    public TTBBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(TTB.TWENTY_ONE_TALL_BAMBOO_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TTB.BLOCKS.getEntries()
                .stream()
                .map(DeferredHolder::get).collect(Collectors.toSet());
    }
}
