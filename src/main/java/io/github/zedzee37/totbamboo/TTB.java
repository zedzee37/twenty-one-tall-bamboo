package io.github.zedzee37.totbamboo;

import com.mojang.logging.LogUtils;
import io.github.zedzee37.totbamboo.blocks.TwentyOneTallBambooBlock;
import io.github.zedzee37.totbamboo.datagen.TTBBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(TTB.MODID)
public class TTB {
    public static final String MODID = "totbamboo";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> TWENTY_ONE_TALL_BAMBOO_BLOCK = BLOCKS.registerBlock(
            "twenty_one_tall_bamboo",
            (registryName) -> new TwentyOneTallBambooBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
            ));
    public static final DeferredItem<BlockItem> TWENTY_ONE_TALL_BAMBOO_BLOCK_ITEM = ITEMS
            .registerSimpleBlockItem(
                    "twenty_one_tall_bamboo",
                    TWENTY_ONE_TALL_BAMBOO_BLOCK);

    public TTB(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::gatherData);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(
                event.includeClient(),
                new TTBBlockStateProvider(output, existingFileHelper)
        );
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(TWENTY_ONE_TALL_BAMBOO_BLOCK_ITEM);
    }
}
