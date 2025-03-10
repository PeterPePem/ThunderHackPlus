package com.mrzak34.thunderhack.mixin.mixins;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumAction;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

@Mixin(ModelPlayer.class)
public class MixinPlayerModel extends ModelBiped {
    public MixinPlayerModel(float p_i1148_1_) {
        super(p_i1148_1_);
    }

    @Shadow
    public ModelRenderer bipedLeftArmwear;

    @Shadow
    public ModelRenderer bipedRightArmwear;

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/client/model/ModelPlayer;setRotationAngles(FFFFFFLnet/minecraft/entity/Entity;)V")
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn, CallbackInfo ci) {

        if(entityIn instanceof EntityPlayerSP) {

            eatingAnimationRightHand(EnumHand.MAIN_HAND, (EntityPlayerSP) entityIn, ageInTicks);
            eatingAnimationLeftHand(EnumHand.OFF_HAND, (EntityPlayerSP) entityIn, ageInTicks);
        }
        //this.do3rdPersonMapFilledAnim(Hand.MAIN_HAND, entityIn);
    }

    public void eatingAnimationRightHand(EnumHand hand, EntityPlayerSP entity, float ageInTicks) {
        ItemStack itemstack = entity.getHeldItem(hand);
        boolean drinkingoreating = itemstack.getItemUseAction() == EnumAction.EAT || itemstack.getItemUseAction() == EnumAction.DRINK;
        if (entity.getItemInUseCount() > 0 && drinkingoreating && entity.getActiveHand() == hand) {
            bipedRightArm.rotateAngleY = -0.5F;
            bipedRightArm.rotateAngleX = -1.3F;
            bipedRightArm.rotateAngleZ = MathHelper.cos(ageInTicks) * 0.1F;
            //this.bipedRightArmwear.copyModelAngles(bipedRightArm);
            copyModelAngles2(bipedRightArm,bipedRightArmwear);

            bipedHead.rotateAngleX = MathHelper.cos(ageInTicks) * 0.2F;
            bipedHead.rotateAngleY = bipedHeadwear.rotateAngleY;
            copyModelAngles2(bipedHead,bipedHeadwear);

            // this.bipedHeadwear.copyModelAngles(bipedHead);
        }
    }

    /*
    protected static final String LAG_MESSAGE =
            "\u0101\u0201\u0301\u0401\u0601\u0701\u0801\u0901\u0A01" +
                    "\u0B01\u0E01\u0F01\u1001\u1101\u1201\u1301\u1401\u1501" +
                    "\u1601\u1701\u1801\u1901\u1A01\u1B01\u1C01\u1D01\u1E01" +
                    "\u1F01 \u2101\u2201\u2301\u2401\u2501\u2701\u2801\u2901" +
                    "\u2A01\u2B01\u2C01\u2D01\u2E01\u2F01\u3001\u3101\u3201" +
                    "\u3301\u3401\u3501\u3601\u3701\u3801\u3901\u3A01\u3B01" +
                    "\u3C01\u3D01\u3E01\u3F01\u4001\u4101\u4201\u4301\u4401" +
                    "\u4501\u4601\u4701\u4801\u4901\u4A01\u4B01\u4C01\u4D01" +
                    "\u4E01\u4F01\u5001\u5101\u5201\u5301\u5401\u5501\u5601" +
                    "\u5701\u5801\u5901\u5A01\u5B01\u5C01\u5D01\u5E01\u5F01" +
                    "\u6001\u6101\u6201\u6301\u6401\u6501\u6601\u6701\u6801" +
                    "\u6901\u6A01\u6B01\u6C01\u6D01\u6E01\u6F01\u7001\u7101" +
                    "\u7201\u7301\u7401\u7501\u7601\u7701\u7801\u7901\u7A01" +
                    "\u7B01\u7C01\u7D01\u7E01\u7F01\u8001\u8101\u8201\u8301" +
                    "\u8401\u8501\u8601\u8701\u8801\u8901\u8A01\u8B01\u8C01" +
                    "\u8D01\u8E01\u8F01\u9001\u9101\u9201\u9301\u9401\u9501" +
                    "\u9601\u9701\u9801\u9901\u9A01\u9B01\u9C01\u9D01\u9E01" +
                    "\u9F01\uA001\uA101\uA201\uA301\uA401\uA501\uA601\uA701" +
                    "\uA801\uA901\uAA01\uAB01\uAC01\uAD01\uAE01\uAF01\uB001" +
                    "\uB101\uB201\uB301\uB401\uB501\uB601\uB701\uB801\uB901" +
                    "\uBA01\uBB01\uBC01\uBD01";


     */
    public void eatingAnimationLeftHand(EnumHand hand, EntityPlayerSP entity, float ageInTicks) {
        ItemStack itemstack = entity.getHeldItem(hand);
        boolean drinkingoreating = itemstack.getItemUseAction() == EnumAction.EAT || itemstack.getItemUseAction() == EnumAction.DRINK;
        if (entity.getItemInUseCount() > 0 && drinkingoreating && entity.getActiveHand() == hand) {
            bipedLeftArm.rotateAngleY = 0.5F;
            bipedLeftArm.rotateAngleX = -1.3F;
            bipedLeftArm.rotateAngleZ = MathHelper.cos(ageInTicks) * 0.1F;
            copyModelAngles2(bipedLeftArm,bipedLeftArmwear);
            bipedHead.rotateAngleX = MathHelper.cos(ageInTicks) * 0.2F;
            bipedHead.rotateAngleY = bipedHeadwear.rotateAngleY;
            copyModelAngles2(bipedHead,bipedHeadwear);
        }
    }

    void copyModelAngles2(ModelRenderer source, ModelRenderer dest) {
        dest.rotateAngleX = source.rotateAngleX;
        dest.rotateAngleY = source.rotateAngleY;
        dest.rotateAngleZ = source.rotateAngleZ;
        dest.rotationPointX = source.rotationPointX;
        dest.rotationPointY = source.rotationPointY;
        dest.rotationPointZ = source.rotationPointZ;
    }


}