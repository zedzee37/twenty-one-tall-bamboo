package io.github.zedzee37.totbamboo.datagen;

import io.github.zedzee37.totbamboo.TTB;
import io.github.zedzee37.totbamboo.blocks.TwentyOneTallBambooBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TTBBlockStateProvider extends BlockStateProvider {
    public TTBBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TTB.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        final ModelFile bambooModel = models().getExistingFile(this.mcLoc("block/bamboo4_age1"));
        final ModelFile leavesFile = models().getExistingFile(this.mcLoc("block/bamboo_large_leaves"));

        final Block block = TTB.TWENTY_ONE_TALL_BAMBOO_BLOCK.get();
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        builder.part()
                .modelFile(bambooModel).addModel().end();
        builder.part()
                .modelFile(leavesFile)
                .addModel()
                .condition(TwentyOneTallBambooBlock.IS_TOP_BAMBOO, true)
                .end();
    }
}
