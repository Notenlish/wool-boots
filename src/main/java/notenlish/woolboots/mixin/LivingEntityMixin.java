package notenlish.woolboots.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import notenlish.woolboots.ModItems;
import notenlish.woolboots.WoolBoots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

//    @Shadow
//    public abstract Level level();
//
//    @Shadow
//    public abstract boolean isSpectator();
//
//    @Shadow
//    public boolean firstTick;
//
//    @Shadow
//    public abstract boolean isSilent();
//
//    @Shadow
//    public abstract double getX();
//
//    @Shadow
//    public abstract double getY();
//
//    @Shadow
//    public abstract double getZ();

    @Shadow
    protected abstract boolean doesEmitEquipEvent(EquipmentSlot equipmentSlot);

//    @Shadow
//    public abstract void gameEvent(Holder<GameEvent> holder);

    @Shadow
    protected abstract Holder<SoundEvent> getEquipSound(EquipmentSlot equipmentSlot, ItemStack itemStack, Equippable equippable);

//    @Shadow
//    public abstract SoundSource getSoundSource();
//
//    @Shadow
//    protected RandomSource random;

    /**
     * @author Notenlish
     * @reason because I dont fucking understand this
     */
    @Overwrite
    public void onEquipItem(
            EquipmentSlot equipmentSlot,
            ItemStack itemStack,
            ItemStack itemStack2
    ) {
        Entity e = (Entity)(Object) this;
        if (!e.level().isClientSide() && !e.isSpectator()) {
            if (!ItemStack.isSameItemSameComponents(itemStack, itemStack2) && !e.firstTick) {

                Equippable equippable = (Equippable)itemStack2.get(DataComponents.EQUIPPABLE);

                if (!e.isSilent() && equippable != null && equipmentSlot == equippable.slot()) {
                    e.level().playSeededSound((Entity)null, e.getX(), e.getY(), e.getZ(), this.getEquipSound(equipmentSlot, itemStack2, equippable), e.getSoundSource(), 1.0F, 1.0F, e.random.nextLong());
                }

                if ((Object) this instanceof Player) {
                    Player p = (Player) (Object) this;
                    if (itemStack2.is(ModItems.WOOL_BOOTS) || (itemStack.is(ModItems.WOOL_BOOTS) && itemStack2.is(Items.AIR))) {
                       // do nothing
                    }
                    else {
                        // fuck you
                        if (this.doesEmitEquipEvent(equipmentSlot)) {
                            e.gameEvent(equippable != null ? GameEvent.EQUIP : GameEvent.UNEQUIP);
                        }
                    }
                } else {
                    // fuck you
                    if (this.doesEmitEquipEvent(equipmentSlot)) {
                        e.gameEvent(equippable != null ? GameEvent.EQUIP : GameEvent.UNEQUIP);
                    }
                }

            }
        }
    }


//    @Inject(
//            method = "onEquipItem",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/level/Level;playSeededSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V",
//                    shift = At.Shift.AFTER
//            ),
//            cancellable = true)
//    private void onEquipItem(
//            EquipmentSlot equipmentSlot,
//            ItemStack itemStack,
//            ItemStack itemStack2,
//            CallbackInfo ci
//    ) {
//        WoolBoots.LOGGER.info("-- Equip sound played for slot: {}", equipmentSlot);
//        WoolBoots.LOGGER.info("old: {} - new: {}", itemStack, itemStack2);
//        if (itemStack2.is(ModItems.WOOL_BOOTS) || itemStack.is(ModItems.WOOL_BOOTS)) {
//            WoolBoots.LOGGER.info("Cancel the game event");
//            ci.cancel();
//        }
//    }
}
