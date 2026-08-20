package notenlish.woolboots.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import notenlish.woolboots.ModItems;
import notenlish.woolboots.WoolBoots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Entity.class)
public abstract class ExampleMixin {

	// use abstract if the shadowed thing is a method and you dont want to deal with the body
	@Shadow
	public abstract boolean isInWater();
	@Shadow
	private Vec3 position;
	@Shadow
	public abstract void resetFallDistance();
	@Shadow
	public Optional<BlockPos> mainSupportingBlockPos;
	@Shadow
	public double fallDistance;

	@Shadow
	public abstract Level level();


	/**
	 * @author Notenlish
	 * @reason because I needed to
	 */
	@Overwrite
	public void checkFallDamage(
			double d,
			boolean bl,
			BlockState blockState,
			BlockPos blockPos
	) {
		Entity self = (Entity) (Object) this;

		if (self instanceof Player) {
			Player p = (Player) self;
			ItemStack boots_stack = p.getItemBySlot(EquipmentSlot.FEET);
			if(!blockState.is(Blocks.AIR) && boots_stack.is(ModItems.WOOL_BOOTS)) {
				if (self.fallDistance >= 4.0F) {
					boots_stack.hurtAndBreak((int)Math.floor(self.fallDistance * 0.45), p, EquipmentSlot.FEET);
				}
				if (self.fallDistance <= 5.0F) {
					self.fallDistance = 0.0f;
				} else { // take less damage if you're gonna take damage
					self.fallDistance *= 0.8; // a bit worse than feather falling 2
				}

				return;
			}
		}

		// vanilla logic
		if (!this.isInWater() && d < 0.0) {
			this.fallDistance -= (float)d;
		}

		if (bl) {
			if (this.fallDistance > 0.0) {
				blockState.getBlock().fallOn(this.level(), blockState, blockPos, self, this.fallDistance);
				this.level()
						.gameEvent(
								GameEvent.HIT_GROUND,
								this.position,
								GameEvent.Context.of(
										self,
										this.mainSupportingBlockPos
												.<BlockState>map(blockPosx -> this.level().getBlockState(blockPosx))
												.orElse(blockState))
						);
			}

			this.resetFallDistance();
		}

	}

	@Inject(
			method="vibrationAndSoundEffectsFromBlock",
			at = @At(value="HEAD"),
			cancellable = true
	)
	private void vibrationAndSoundEffectsFromBlock(BlockPos blockPos, BlockState blockState, boolean bl, boolean bl2, Vec3 vec3, CallbackInfoReturnable<Boolean> cir) {
		if ((Object) this instanceof Player player) {
			ItemStack boots_stack = player.getItemBySlot(EquipmentSlot.FEET);
			if (boots_stack.is(ModItems.WOOL_BOOTS)) {
				// Cancel the step event by returning false
				cir.setReturnValue(false);
			}
		}
	}

	// ah yes lets add another mixin, surely this will fix it
//	@Inject(method = "gameEvent", at = @At("HEAD"), cancellable = true)
//	private void mixinGameEvent(
//			Holder<GameEvent> eventHolder,
//			Entity entity,
//			CallbackInfo ci
//	) {
//
//		WoolBoots.LOGGER.info("gameEvent entity: {}", entity);
//		WoolBoots.LOGGER.info("entity class: {}", entity.getClass().getName());
//		WoolBoots.LOGGER.info("Avatar assignable: {}", Avatar.class.isAssignableFrom(entity.getClass()));
//
//		if (entity instanceof Avatar) {
//			WoolBoots.LOGGER.info("AVATAR!");
//		}
//
//		if (entity instanceof LivingEntity) {
//			WoolBoots.LOGGER.info("LIVING ENTITY!");
//		}
//
//		if (entity instanceof Player) {
//			WoolBoots.LOGGER.info("PLAYER!");
//		}
//	}

}


