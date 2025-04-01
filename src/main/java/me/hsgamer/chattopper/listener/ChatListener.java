package me.hsgamer.chattopper.listener;

import io.github.projectunified.minelib.plugin.listener.ListenerComponent;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.manager.HolderManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements ListenerComponent {
    private final ChatTopper plugin;

    public ChatListener(ChatTopper plugin) {
        this.plugin = plugin;
    }

    @Override
    public ChatTopper getPlugin() {
        return plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        String chat = event.getMessage();
        plugin.get(HolderManager.class).getHolder().addChat(chat);
    }
}
