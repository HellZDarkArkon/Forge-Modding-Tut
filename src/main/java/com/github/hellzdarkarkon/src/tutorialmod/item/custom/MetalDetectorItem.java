package com.github.hellzdarkarkon.src.tutorialmod.item.custom;

import com.github.hellzdarkarkon.src.tutorialmod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProp) {
        super(pProp);

    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= posClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(posClicked.below(i));
                if(isValuableBlock(state)) {
                    outputValuableCoordinates(posClicked.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }
            if(!foundBlock) {
                player.sendSystemMessage(Component.literal("No valuables found."));
            }
        }
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE)
                || state.is(Blocks.DIAMOND_ORE)
                || state.is(Blocks.DEEPSLATE_IRON_ORE)
                || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)
                || state.is(Blocks.EMERALD_ORE)
                || state.is(Blocks.DEEPSLATE_EMERALD_ORE)
                || state.is(Blocks.COPPER_ORE)
                || state.is(Blocks.DEEPSLATE_COPPER_ORE)
                || state.is(Blocks.GOLD_ORE)
                || state.is(Blocks.DEEPSLATE_GOLD_ORE)
                || state.is(Blocks.NETHER_GOLD_ORE)
                || state.is(ModBlocks.SAPPHIRE_ORE.get())
                || state.is(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                || state.is(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                || state.is(ModBlocks.END_STONE_SAPPHIRE_ORE.get());
    }

    private void outputValuableCoordinates(BlockPos bp, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + bp.getX() + ", " + bp.getY() + ", " + bp.getZ() + ")"));
    }
}