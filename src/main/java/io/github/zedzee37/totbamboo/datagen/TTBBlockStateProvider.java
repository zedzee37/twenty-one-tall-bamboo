package io.github.zedzee37.totbamboo.datagen;

import io.github.zedzee37.totbamboo.TTB;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TTBBlockStateProvider extends BlockStateProvider {
    public TTBBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TTB.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        final ModelFile twentyOneTallBambooModel = models().withExistingParent("twenty_one_tall_bamboo",
                this.mcLoc("block/bamboo4_age1"));
        simpleBlock(TTB.TWENTY_ONE_TALL_BAMBOO_BLOCK.get(), twentyOneTallBambooModel);
    }
}
