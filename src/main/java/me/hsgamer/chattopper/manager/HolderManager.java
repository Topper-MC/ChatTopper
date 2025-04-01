package me.hsgamer.chattopper.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.holder.WordHolder;

public class HolderManager implements Loadable {
    private final ChatTopper plugin;
    private WordHolder holder;

    public HolderManager(ChatTopper plugin) {
        this.plugin = plugin;
    }

    public WordHolder getHolder() {
        return holder;
    }

    @Override
    public void enable() {
        this.holder = new WordHolder(plugin);
        this.holder.register();
    }

    @Override
    public void disable() {
        if (holder != null) {
            holder.unregister();
        }
    }
}
