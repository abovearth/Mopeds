
package net.mcreator.moped.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.moped.world.inventory.MopedGUIMenu;
import net.mcreator.moped.procedures.CopperMopedRightClickedOnEntityProcedure;
import net.mcreator.moped.procedures.CopperMopedOnEntityTickUpdateProcedure;
import net.mcreator.moped.procedures.CopperMopedEntityDiesProcedure;
import net.mcreator.moped.init.MopedModEntities;
//extra import to find the key item
import net.mcreator.moped.init.MopedModItems;
//extra imports for seating leashed animals
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Shulker;
import com.google.common.base.Optional;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import io.netty.buffer.Unpooled;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.server.commands.SayCommand;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;


public class CopperMopedEntity extends PathfinderMob implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(CopperMopedEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(CopperMopedEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CopperMopedEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public CopperMopedEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MopedModEntities.COPPER_MOPED.get(), world);
	}

	public CopperMopedEntity(EntityType<CopperMopedEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "moped0");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}
	
	@Override
	public boolean canBeLeashed(Player p_21418_){
		return false;
	}


	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.sound.silence")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.hurt2"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("moped:moped.breaks2"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (source.is(DamageTypes.FALL))
			//We want to reduce fall damage
			return super.hurt(source, amount/5);
			//return false;
		if (source.is(DamageTypes.CACTUS))
			return false;
		if (source.is(DamageTypes.TRIDENT))
			return false;
		if (source.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		CopperMopedEntityDiesProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	private final ItemStackHandler inventory = new ItemStackHandler(4) {
		@Override
		public int getSlotLimit(int slot) {
			return 64;
		}
	};
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null)
			return LazyOptional.of(() -> combined).cast();
		return super.getCapability(capability, side);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
		if (itemstack.getItem() == MopedModItems.MOPED_KEY.get()) {
		//if (sourceentity.isSecondaryUseActive()) {
		// we change the condition to holding the key item.
			if (sourceentity instanceof ServerPlayer serverPlayer) {
				NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("Copper Moped");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(sourceentity.blockPosition());
						packetBuffer.writeByte(0);
						packetBuffer.writeVarInt(CopperMopedEntity.this.getId());
						return new MopedGUIMenu(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(sourceentity.blockPosition());
					buf.writeByte(0);
					buf.writeVarInt(this.getId());
				});
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		}
		super.mobInteract(sourceentity, hand);
		//sourceentity.startRiding(this);
		
		Level world = this.level();
		
		List<Entity> passengers = this.getPassengers();
		if (!passengers.isEmpty() && passengers.get(0) instanceof Player)
			return InteractionResult.PASS;
		if (!world.isClientSide) {
			this.ejectPassengers();
			//sourceentity.startRiding(this);
			//return InteractionResult.SUCCESS;	
		}
		sitDown(world, getLeashed(world, sourceentity).or(sourceentity)); //Removed pos,
 
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;


		return retval;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		CopperMopedOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	@Override
	public void travel(Vec3 dir) {
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
		if (this.isVehicle()) {
			this.setYRot(entity.getYRot());
			this.yRotO = this.getYRot();
			this.setXRot(entity.getXRot() * 0.5F);
			this.setRot(this.getYRot(), this.getXRot());
			this.yBodyRot = entity.getYRot();
			this.yHeadRot = entity.getYRot();
			this.setMaxUpStep(1.0F);
			if (entity instanceof LivingEntity passenger) {
				this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
				float forward = passenger.zza;
				float strafe = 0;
				super.travel(new Vec3(strafe, 0, forward));
			}
			double d1 = this.getX() - this.xo;
			double d0 = this.getZ() - this.zo;
			float f1 = (float) Math.sqrt(d1 * d1 + d0 * d0) * 4;
			if (f1 > 1.0F)
				f1 = 1.0F;
			this.walkAnimation.setSpeed(this.walkAnimation.speed() + (f1 - this.walkAnimation.speed()) * 0.4F);
			this.walkAnimation.position(this.walkAnimation.position() + this.walkAnimation.speed());
			this.calculateEntityAnimation(true);
			return;
		}
		this.setMaxUpStep(0.5F);
		super.travel(dir);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 6);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if (this.isVehicle() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.copper_moped.moving"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.copper_moped.idle"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		Entity entity = this;
		Level world = entity.level();
		boolean loop = false;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (!loop && this.lastloop) {
			this.lastloop = false;
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			event.getController().forceAnimationReset();
			return PlayState.STOP;
		}
		if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			if (!loop) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationprocedure = "empty";
					event.getController().forceAnimationReset();
				}
			} else {
				event.getController().setAnimation(RawAnimation.begin().thenLoop(this.animationprocedure));
				this.lastloop = true;
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(CopperMopedEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	//Extra code to make mobs on a lead able to be seated on a moped
	//This code is based on     Create/src/main/java/com/simibubi/create/content/contraptions/actors/seat/SeatBlock.java
	public static boolean canBePickedUp(Entity passenger) {
		if (passenger instanceof Shulker)
			return false;
		if (passenger instanceof Player)
			return false;
		/*if (AllEntityTags.IGNORE_SEAT.matches(passenger))
			return false;*/ //this depends on a create specific library
		/*if (!AllConfigs.server().logistics.seatHostileMobs.get() && !passenger.getType()
			.getCategory()
			.isFriendly())
			return false;*/ //i don't have the create config
		return passenger instanceof LivingEntity;
	}

	public static Optional<Entity> getLeashed(Level level, Player player) {
		List<Entity> entities = level.getEntities((Entity) null, player.getBoundingBox() // removed player. before level
			.inflate(10), e -> true);
		for (Entity e : entities)
			if (e instanceof Mob mob && mob.getLeashHolder() == player && CopperMopedEntity.canBePickedUp(e)) //changed SeeatBlock to CopperMopedEntity
				return Optional.of(mob);
		return Optional.absent();
	}

	public void sitDown(Level world, Entity entity) {//Removed  BlockPos pos,
		if (world.isClientSide)
			return;
		/*SeatEntity seat = new SeatEntity(world, pos);
		seat.setPos(pos.getX() + .5f, pos.getY(), pos.getZ() + .5f);
		world.addFreshEntity(seat);*/
		entity.startRiding(this, true); //replaced seat with this
		if (entity instanceof TamableAnimal ta)
			if (ta.isTame())
				ta.setInSittingPose(true);
		//Add goals from passenger to moped
		if (entity instanceof Mob mob){
			Set<WrappedGoal> goals = mob.goalSelector.getAvailableGoals();
			Iterator<WrappedGoal> goalsIterator = goals.iterator();
			while(goalsIterator.hasNext()) {
				WrappedGoal wrappedGoalToAdd = goalsIterator.next();
				//sayinchat(world, this.getX(), this.getY(), this.getZ(), wrappedGoalToAdd.getGoal().toString());
				if (wrappedGoalToAdd.getFlags().contains(Goal.Flag.MOVE))
	   				this.goalSelector.addGoal(wrappedGoalToAdd.getPriority(),wrappedGoalToAdd.getGoal());
			}
	   		Set<WrappedGoal> targetgoals = mob.targetSelector.getAvailableGoals();
			Iterator<WrappedGoal> targetgoalsIterator = targetgoals.iterator();
			while(targetgoalsIterator.hasNext()) {
				WrappedGoal targetwrappedGoalToAdd = targetgoalsIterator.next();				
				//sayinchat(world, this.getX(), this.getY(), this.getZ(), targetwrappedGoalToAdd.getGoal().toString());
				if (targetwrappedGoalToAdd.getFlags().contains(Goal.Flag.MOVE))
	   				this.goalSelector.addGoal(targetwrappedGoalToAdd.getPriority(),targetwrappedGoalToAdd.getGoal());
			}
		}
	}
	
	@Override
	protected void removePassenger(Entity entity) {
		super.removePassenger(entity);
		if (entity instanceof TamableAnimal ta)
			ta.setInSittingPose(false);
		//Remove goals from passenger to moped
		
		System.out.println("0");
		Set<WrappedGoal> goals = this.goalSelector.getAvailableGoals();
		System.out.println("1");
		Iterator<WrappedGoal> goalsIterator = goals.iterator();
		System.out.println("2");
		while(goalsIterator.hasNext()) {
			System.out.println("3");
   			this.goalSelector.removeGoal(goalsIterator.next().getGoal());
		}
		
		Set<WrappedGoal> targetgoals = this.targetSelector.getAvailableGoals();
			Iterator<WrappedGoal> targetgoalsIterator = targetgoals.iterator();
			while(targetgoalsIterator.hasNext()) {				
	   			this.targetSelector.removeGoal(targetgoalsIterator.next().getGoal());
			}
		
		
	}
	
	public static void sayinchat(LevelAccessor world, double x, double y, double z, String message) {
		if (message == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), ("say " + message));
	}

}
