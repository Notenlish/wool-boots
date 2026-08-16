package notenlish.woolboots.mixin;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.world.phys.Vec3;
import notenlish.woolboots.ModItems;
import notenlish.woolboots.ModTemplate;
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


			//public abstract Iterable<ItemStack> getArmorSlots();
    		//public abstract ItemStack getItemBySlot(EquipmentSlot var1);

			Iterable<ItemStack> slots = p.getArmorSlots();
			List<ItemStack> slotList = Lists.newArrayList(slots);
			ItemStack boots_stack = p.getItemBySlot(EquipmentSlot.FEET);

			// ModTemplate.LOGGER.info(String.format("falldistance %.2f", entity.fallDistance));

			// Item i = boots_stack.getItem();
			if (p_19913_.is(Blocks.AIR)) {
				// dont do anything
			}
			else if(boots_stack.is(ModItems.WHITE_WOOL_BOOTS)) {
				boots_stack.hurtAndBreak(Math.round((float)(entity.fallDistance * 0.55)), p, EquipmentSlot.FEET);
				if (entity.fallDistance <= 5.0F) {
					entity.fallDistance = 0.0f;
				} else { // take less damage if youre gonna take damage
					entity.fallDistance *= 0.8; // roughly worse than feather falling 2
				}

				// ci.cancel();
				// idk if required
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


