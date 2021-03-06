package dev.vatuu.kyoob.client.mixin;

import dev.vatuu.kyoob.client.cutscene.CutsceneHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "handleInputEvents", at = @At("HEAD"), cancellable = true)
    public void tickMovement(CallbackInfo callbackInfo) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (CutsceneHandler.isCutsceneRunning() && !(!player.getMainHandStack().isEmpty() && player.getMainHandStack().getItem() == Items.ACACIA_SAPLING))
            callbackInfo.cancel();
    }
}
