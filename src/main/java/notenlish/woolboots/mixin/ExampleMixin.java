package notenlish.woolboots.mixin;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.world.phys.Vec3;
import notenlish.woolboots.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Entity.class)
@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
public abstract class ExampleMixin {

	@Inject(
			method = "checkFallDamage",
			at = @At(value = "HEAD"),
			cancellable = true
	)
	private void checkFallDamage(
			double p_19911_,
			boolean p_19912_,
			BlockState p_19913_,
			BlockPos p_19914_,
			CallbackInfo ci
	) {
		Entity entity = (Entity) (Object) this;

		if (entity instanceof Player) {
			Player p = (Player) entity;

			/*
			public abstract Iterable<ItemStack> getArmorSlots();

    		public abstract ItemStack getItemBySlot(EquipmentSlot var1);
			*/
			Iterable<ItemStack> slots = p.getArmorSlots();
			List<ItemStack> slotList = Lists.newArrayList(slots);
			ItemStack boots_stack = p.getItemBySlot(EquipmentSlot.FEET);

			Item i = boots_stack.getItem();
			if(boots_stack.is(ModItems.WHITE_WOOL_BOOTS)) {
				ci.cancel();
				// idk if required
				entity.fallDistance = 0.0F;
			}
		}
	}

	@Inject(
			method="vibrationAndSoundEffectsFromBlock",
			at = @At(value="HEAD"),
			cancellable = true
	)
	private void vibrationAndSoundEffectsFromBlock(BlockPos blockPos, BlockState blockState, boolean bl, boolean bl2, Vec3 vec3, CallbackInfoReturnable<Boolean> cir) {
		if ((Object) this instanceof Player player) {
			Iterable<ItemStack> slots = player.getArmorSlots();
			List<ItemStack> slotList = Lists.newArrayList(slots);
			ItemStack boots_stack = player.getItemBySlot(EquipmentSlot.FEET);
			if (boots_stack.is(ModItems.WHITE_WOOL_BOOTS)) {
				// Cancel the step event by returning false
				cir.setReturnValue(false);
			}
		}
	}

}


