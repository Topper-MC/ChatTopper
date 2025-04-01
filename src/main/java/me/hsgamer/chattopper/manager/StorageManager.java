package me.hsgamer.chattopper.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.config.MainConfig;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.topper.storage.core.DataStorage;
import me.hsgamer.topper.storage.simple.builder.DataStorageBuilder;
import me.hsgamer.topper.storage.simple.config.DatabaseConfig;
import me.hsgamer.topper.storage.simple.converter.NumberConverter;
import me.hsgamer.topper.storage.simple.converter.StringConverter;
import me.hsgamer.topper.storage.simple.setting.DataStorageSetting;
import me.hsgamer.topper.storage.simple.setting.DatabaseSetting;
import me.hsgamer.topper.storage.simple.supplier.DataStorageSupplier;

import java.io.File;

public class StorageManager implements Loadable {
    private final ChatTopper plugin;
    private DataStorageSupplier dataStorageSupplier;

    public StorageManager(ChatTopper plugin) {
        this.plugin = plugin;
    }

    public DataStorage<String, Long> buildStorage(String name) {
        return dataStorageSupplier.getStorage(name,
                new StringConverter("word", true, 256),
                new NumberConverter<>("count", false, Number::longValue)
        );
    }

    @Override
    public void load() {
        MainConfig mainConfig = plugin.get(MainConfig.class);
        String storageType = mainConfig.getStorageType();

        DataStorageBuilder builder = new DataStorageBuilder();
        this.dataStorageSupplier = builder.buildSupplier(storageType, new DataStorageSetting() {
            @Override
            public DatabaseSetting getDatabaseSetting() {
                return new DatabaseConfig("word", new BukkitConfig(plugin, "database.yml"));
            }

            @Override
            public File getBaseFolder() {
                return new File(plugin.getDataFolder(), "data");
            }
        });
    }

    @Override
    public void enable() {
        dataStorageSupplier.enable();
    }

    @Override
    public void disable() {
        dataStorageSupplier.disable();
    }
}
