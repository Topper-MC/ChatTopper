package me.hsgamer.chattopper.holder;

import io.github.projectunified.minelib.scheduler.async.AsyncScheduler;
import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.holder.agent.UpdateAgent;
import me.hsgamer.chattopper.manager.StorageManager;
import me.hsgamer.topper.agent.holder.AgentDataHolder;
import me.hsgamer.topper.agent.storage.StorageAgent;
import me.hsgamer.topper.spigot.agent.runnable.SpigotRunnableAgent;
import me.hsgamer.topper.storage.core.DataStorage;

public class WordHolder extends AgentDataHolder<String, Long> {
    private final UpdateAgent updateAgent;

    public WordHolder(ChatTopper plugin) {
        super("word");

        DataStorage<String, Long> dataStorage = plugin.get(StorageManager.class).buildStorage(getName());
        StorageAgent<String, Long> storageAgent = new StorageAgent<>(this, dataStorage);
        addAgent(storageAgent);
        addEntryAgent(storageAgent);
        addAgent(new SpigotRunnableAgent(storageAgent, AsyncScheduler.get(plugin), 20L));

        updateAgent = new UpdateAgent(this);
        addAgent(updateAgent);
        addAgent(new SpigotRunnableAgent(updateAgent, AsyncScheduler.get(plugin), 0L));
    }

    @Override
    public Long getDefaultValue() {
        return 0L;
    }

    public void addChat(String chat) {
        updateAgent.addChat(chat);
    }
}
