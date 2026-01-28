package io.github.zedzee37.totbamboo.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TwentyOneTallBambooBlock extends Block {
    private static final BooleanProperty IS_TOP_BAMBOO = BooleanProperty.create("is_top");
    private static final BlockState[] BLOCK_STATES = new BlockState[21];

    // when the leaves start rendering ig
    private static final int LEAVES_START_POS = 18;

    public TwentyOneTallBambooBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(defaultBlockState().setValue(IS_TOP_BAMBOO, false));
        for (int i = 0; i < BLOCK_STATES.length; i++) {
            BLOCK_STATES[i] = defaultBlockState();

            if (i >= LEAVES_START_POS) {
                BLOCK_STATES[i].setValue(IS_TOP_BAMBOO, true);
            }
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos curPos = context.getClickedPos();
        for (int i = 0; i < BLOCK_STATES.length; i++) {
            if (!level.getBlockState(curPos).canBeReplaced()) {
                return null;
            }
            curPos = curPos.above();
        }
        return defaultBlockState();
    }

    @Override
    public void setPlacedBy(@NotNull Level level,
                            @NotNull BlockPos pos,
                            @NotNull BlockState state,
                            @Nullable LivingEntity placer,
                            @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) {
            return;
        }

        BlockPos curPos = pos.above();
        for (int i = 1; i < BLOCK_STATES.length; i++) {
            BlockState blockState = BLOCK_STATES[i];
            level.setBlock(curPos, blockState, Block.UPDATE_ALL);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_TOP_BAMBOO);
        super.createBlockStateDefinition(builder);
    }
}

