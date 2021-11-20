/*
 * Copyright 2020-2021 Siphalor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.github.jupiterio.volcanolib.registry.mixin;

import io.github.jupiterio.volcanolib.registry.RegistrySyncBlacklist;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

// Compared to Siphalor's, I just made it generic
// RegistrySyncBlacklist manages all the actual blacklisting
@Mixin(value = RegistrySyncManager.class)
public class MixinRegistrySyncManager {
    private static Identifier currentRegistryId;

    @Inject(method = "toTag", at = @At(value = "NEW", target = "net/minecraft/nbt/NbtCompound", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void onRegistry(boolean isClientSync, NbtCompound activeTag, CallbackInfoReturnable<?> callbackInfoReturnable, NbtCompound mainTag, Iterator<?> iterator, Identifier registryId, Registry<?> registry) {
        MutableRegistry<MutableRegistry<?>> mutableRegistry = MutableRegistry.class.cast(Registry.REGISTRIES);

        currentRegistryId = mutableRegistry.getId((MutableRegistry)registry);
    }

    @ModifyVariable(method = "toTag", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/registry/Registry;getId(Ljava/lang/Object;)Lnet/minecraft/util/Identifier;", ordinal = 1), ordinal = 1)
    private static Identifier cancelSync(Identifier oldId) {
        if (RegistrySyncBlacklist.isBlacklisted(currentRegistryId, oldId)) {
            return null;
        }
        return oldId;
    }
}
