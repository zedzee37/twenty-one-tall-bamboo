package io.github.zedzee37.totbamboo.mixin;

import com.simibubi.create.AllRecipeTypes;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.bus.api.IEventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AllRecipeTypes.class)
public class AllRecipeTypesMixin {
    @Inject(method = "register", at = @At("TAIL"))
    private static void changeMaxGrid(IEventBus modEventBus, CallbackInfo ci) {
        ShapedRecipePattern.setCraftingSize(21, 21);
    }
}
