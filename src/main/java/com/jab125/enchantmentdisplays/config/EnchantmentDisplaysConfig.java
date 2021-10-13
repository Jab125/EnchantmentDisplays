package com.jab125.enchantmentdisplays.config;

import com.jab125.enchantmentdisplays.EnchantmentDisplays;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = EnchantmentDisplays.MODID)
public class EnchantmentDisplaysConfig implements ConfigData {
    //Settings
    public EnchantmentNumerals ENCHANTMENT_NUMERALS = EnchantmentNumerals.ROMAN;
    public boolean COLORFUL_ENCHANTMENT_TEXT = true;
}
