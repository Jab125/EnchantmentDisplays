package com.jab125.enchantmentdisplays.client.mixins;

import com.jab125.enchantmentdisplays.EnchantmentDisplays;
import com.jab125.enchantmentdisplays.config.EnchantmentNumerals;
import com.jab125.enchantmentdisplays.util.RomanNumerals;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Enchantment.class, priority = Integer.MAX_VALUE)
public class EnchantmentMixin {
    int level;
    net.minecraft.enchantment.Enchantment enchantment;

    @Inject(method = "getName", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void append(int level, CallbackInfoReturnable<Text> cir) {
        this.level = level;
        this.enchantment = (net.minecraft.enchantment.Enchantment) (Object) this;
    }

    /**
     * @author Jab125
     */
    @Overwrite
    public Text getName(int level) {
        MutableText mutableText = new TranslatableText(this.enchantment.getTranslationKey());
        if (this.enchantment.isCursed()) {
            mutableText.formatted(Formatting.RED);
        } else {
            mutableText.formatted(Formatting.GRAY);
        }

        if (level != 1 || this.enchantment.getMaxLevel() != 1) {
            if (EnchantmentDisplays.config.ENCHANTMENT_NUMERALS.equals(EnchantmentNumerals.ROMAN)) {
                mutableText.append(" ").append((Text)(new TranslatableText(RomanNumerals.integerToRoman(level))));
            } else if (EnchantmentDisplays.config.ENCHANTMENT_NUMERALS.equals(EnchantmentNumerals.ARABIC)){
                mutableText.append(" ").append((Text)(new TranslatableText(Integer.toString(level))));
            } else {
                mutableText.append(" ").append((Text)(new TranslatableText("enchantment.level." + level)));
            }

        }

        return mutableText;
    }
    @ModifyVariable(method = "getName", at = @At(value = "RETURN"), index = 2, ordinal = 0)
    private MutableText append(MutableText mutabletext) {
        if (EnchantmentDisplays.config.COLORFUL_ENCHANTMENT_TEXT) {
            if (this.level >= this.enchantment.getMaxLevel() && !this.enchantment.isCursed()) {
                mutabletext.formatted(Formatting.LIGHT_PURPLE);
            }
            if (this.level >= this.enchantment.getMaxLevel() + 3 && !this.enchantment.isCursed()) {
                mutabletext.formatted(Formatting.YELLOW);
            }
            if (this.level >= this.enchantment.getMaxLevel() + 20 && !this.enchantment.isCursed()) {
                mutabletext.formatted(Formatting.AQUA);
            }
            if (this.level >= 255 && !this.enchantment.isCursed()) {
                mutabletext.formatted(Formatting.GOLD);
            }
            if (this.level <= 0 && !this.enchantment.isCursed()) {
                mutabletext.formatted(Formatting.RED);
            }
        }
        return mutabletext;
    }
}
