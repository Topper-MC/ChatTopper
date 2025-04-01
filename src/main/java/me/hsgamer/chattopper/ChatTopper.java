package me.hsgamer.chattopper;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import io.github.projectunified.minelib.plugin.command.CommandComponent;
import me.hsgamer.chattopper.command.GetWordTopCommand;
import me.hsgamer.chattopper.hook.PlaceholderHook;
import me.hsgamer.chattopper.listener.ChatListener;
import me.hsgamer.chattopper.manager.HolderManager;
import me.hsgamer.chattopper.manager.StorageManager;
import me.hsgamer.chattopper.manager.WordQueryForward;
import me.hsgamer.chattopper.manager.WordQueryManager;

import java.util.Arrays;
import java.util.List;

public final class ChatTopper extends BasePlugin {
    @Override
    protected List<Object> getComponents() {
        return Arrays.asList(
                new StorageManager(this),
                new HolderManager(this),

                new ChatListener(this),
                new CommandComponent(this, new GetWordTopCommand(this)),

                new WordQueryManager(this),
                new WordQueryForward(this),

                new PlaceholderHook(this)
        );
    }
}
