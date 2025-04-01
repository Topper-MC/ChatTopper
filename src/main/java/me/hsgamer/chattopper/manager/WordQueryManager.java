package me.hsgamer.chattopper.manager;

import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.topper.agent.snapshot.SnapshotAgent;
import me.hsgamer.topper.query.simple.SimpleQueryDisplay;
import me.hsgamer.topper.query.snapshot.SnapshotQuery;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class WordQueryManager extends SnapshotQuery<String, Long, OfflinePlayer> {
    private final ChatTopper plugin;

    public WordQueryManager(ChatTopper plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isSingleAgent() {
        return true;
    }

    @Override
    protected Optional<String> getKey(@NotNull OfflinePlayer player, @NotNull Context<String, Long> context) {
        return Optional.empty();
    }

    @Override
    protected Optional<SnapshotAgent<String, Long>> getAgent(@NotNull String s) {
        return Optional.of(plugin.get(HolderManager.class).getHolder().getSnapshotAgent());
    }

    @Override
    protected Optional<SimpleQueryDisplay<String, Long>> getDisplay(@NotNull String s) {
        return Optional.of(plugin.get(HolderManager.class).getHolder().getQueryDisplay());
    }
}
