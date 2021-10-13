package com.jab125.enchantmentdisplays;

import com.jab125.enchantmentdisplays.config.EnchantmentDisplaysConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class EnchantmentDisplays implements ModInitializer {
    public static final String MODID = "enchantment-displays";
    public static EnchantmentDisplaysConfig config;
    @Override
    public void onInitialize() {
        AutoConfig.register(EnchantmentDisplaysConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(EnchantmentDisplaysConfig.class).getConfig();

    }

}
