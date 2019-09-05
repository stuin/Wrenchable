package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.wrench.WrenchableUtilities;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EndPortalFrameInstanceListener extends InstanceListener {
    public EndPortalFrameInstanceListener() {
        super(EndPortalFrameBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (ModKeys.isControlPressed(player) && state.get(Properties.EYE)) {
            world.setBlockState(pos, state.with(Properties.EYE, false));
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1, 0.8F);
            if (!player.isCreative())
                player.inventory.offerOrDrop(world, new ItemStack(Items.ENDER_EYE));

            return;
        }

        WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
    }
}
