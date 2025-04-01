package me.hsgamer.chattopper.hook;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.manager.WordQueryForward;
import me.hsgamer.topper.spigot.query.forward.placeholderapi.PlaceholderQueryForwarder;
import org.bukkit.Bukkit;

public class PlaceholderHook implements Loadable {
    private final ChatTopper plugin;
    private PlaceholderQueryForwarder<WordQueryForward.Context> forwarder;

    public PlaceholderHook(ChatTopper plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            forwarder = new PlaceholderQueryForwarder<>();
            plugin.get(WordQueryForward.class).addForwarder(forwarder);
        }
    }

    @Override
    public void disable() {
        if (forwarder != null) {
            plugin.get(WordQueryForward.class).removeForwarder(forwarder);
            forwarder.unregister();
            forwarder = null;
        }
    }
}
