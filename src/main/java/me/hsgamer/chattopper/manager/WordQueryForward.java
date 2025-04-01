package me.hsgamer.chattopper.manager;

import io.github.projectunified.minelib.plugin.base.Loadable;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.topper.query.core.QueryResult;
import me.hsgamer.topper.query.forward.QueryForward;
import me.hsgamer.topper.query.forward.QueryForwardContext;
import me.hsgamer.topper.spigot.query.forward.plugin.PluginContext;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class WordQueryForward extends QueryForward<OfflinePlayer, WordQueryForward.Context> implements Loadable {
    private final ChatTopper plugin;

    public WordQueryForward(ChatTopper plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        addContext(new Context() {
            @Override
            public Plugin getPlugin() {
                return plugin;
            }

            @Override
            public String getName() {
                return "chattopper";
            }

            @Override
            public BiFunction<@Nullable OfflinePlayer, @NotNull String, @NotNull QueryResult> getQuery() {
                return plugin.get(WordQueryManager.class);
            }
        });
    }

    @Override
    public void disable() {
        clearForwarders();
        clearContexts();
    }

    public interface Context extends QueryForwardContext<OfflinePlayer>, PluginContext {
    }
}
