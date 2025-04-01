package me.hsgamer.chattopper;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import me.hsgamer.chattopper.config.MainConfig;
import me.hsgamer.chattopper.manager.StorageManager;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.proxy.ConfigGenerator;

import java.util.Arrays;
import java.util.List;

public final class ChatTopper extends BasePlugin {
    @Override
    protected List<Object> getComponents() {
        return Arrays.asList(
                ConfigGenerator.newInstance(MainConfig.class, new BukkitConfig(this)),

                new StorageManager(this)
        );
    }
}
