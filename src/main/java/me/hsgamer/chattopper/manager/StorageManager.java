package me.hsgamer.chattopper.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.topper.storage.core.DataStorage;
import me.hsgamer.topper.storage.simple.converter.NumberConverter;
import me.hsgamer.topper.storage.simple.converter.StringConverter;
import me.hsgamer.topper.storage.simple.flat.FlatStorageSupplier;
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
        this.dataStorageSupplier = new FlatStorageSupplier(new File(plugin.getDataFolder(), "data"));
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
