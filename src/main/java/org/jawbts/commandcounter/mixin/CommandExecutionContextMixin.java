package org.jawbts.commandcounter.mixin;

import net.minecraft.command.CommandExecutionContext;
import org.jawbts.commandcounter.Counter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandExecutionContext.class)
public class CommandExecutionContextMixin {
    @Final
    @Shadow
    private int maxCommandChainLength;

    @Shadow
    private int commandsRemaining;

    @Inject(method = "run", at = @At("RETURN"))
    private void run(CallbackInfo ci) {
        Counter.getInstance().increase(maxCommandChainLength - commandsRemaining);
    }
}
