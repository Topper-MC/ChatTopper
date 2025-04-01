package me.hsgamer.chattopper.holder;

import io.github.projectunified.minelib.scheduler.async.AsyncScheduler;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.holder.agent.UpdateAgent;
import me.hsgamer.chattopper.manager.StorageManager;
import me.hsgamer.topper.agent.holder.AgentDataHolder;
import me.hsgamer.topper.agent.snapshot.SnapshotAgent;
import me.hsgamer.topper.agent.storage.StorageAgent;
import me.hsgamer.topper.core.DataEntry;
import me.hsgamer.topper.query.simple.SimpleQueryDisplay;
import me.hsgamer.topper.spigot.agent.runnable.SpigotRunnableAgent;
import me.hsgamer.topper.storage.core.DataStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class WordHolder extends AgentDataHolder<String, Long> {
    private final UpdateAgent updateAgent;
    private final SnapshotAgent<String, Long> snapshotAgent;
    private final SimpleQueryDisplay<String, Long> queryDisplay;

    public WordHolder(ChatTopper plugin) {
        super("word");
        this.queryDisplay = new SimpleQueryDisplay<String, Long>() {
            @Override
            public @NotNull String getDisplayName(@Nullable String string) {
                return string == null ? "" : string;
            }

            @Override
            public @NotNull String getDisplayValue(@Nullable Long aLong, @NotNull String s) {
                return aLong == null ? "" : Long.toUnsignedString(aLong);
            }

            @Override
            public @NotNull String getDisplayKey(@Nullable String string) {
                return string == null ? "" : string;
            }
        };

        DataStorage<String, Long> dataStorage = plugin.get(StorageManager.class).buildStorage(getName());
        StorageAgent<String, Long> storageAgent = new StorageAgent<String, Long>(this, dataStorage) {
            @Override
            public void onUpdate(DataEntry<String, Long> entry, Long oldValue, Long newValue) {
                scheduleValue(entry.getKey(), newValue);
            }
        };
        addAgent(storageAgent);
        addEntryAgent(storageAgent);
        addAgent(new SpigotRunnableAgent(storageAgent, AsyncScheduler.get(plugin), 20L));

        updateAgent = new UpdateAgent(this);
        addAgent(updateAgent);
        addAgent(new SpigotRunnableAgent(updateAgent, AsyncScheduler.get(plugin), 0L));

        snapshotAgent = SnapshotAgent.create(this);
        snapshotAgent.setComparator(Comparator.reverseOrder());
        addAgent(snapshotAgent);
        addAgent(new SpigotRunnableAgent(snapshotAgent, AsyncScheduler.get(plugin), 20L));
    }

    @Override
    public Long getDefaultValue() {
        return 0L;
    }

    public void addChat(String chat) {
        updateAgent.addChat(chat);
    }

    public SnapshotAgent<String, Long> getSnapshotAgent() {
        return snapshotAgent;
    }

    public SimpleQueryDisplay<String, Long> getQueryDisplay() {
        return queryDisplay;
    }
}
