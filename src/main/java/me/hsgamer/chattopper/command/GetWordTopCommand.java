package me.hsgamer.chattopper.command;

import me.hsgamer.chattopper.ChatTopper;
import me.hsgamer.chattopper.Permissions;
import me.hsgamer.chattopper.manager.HolderManager;
import me.hsgamer.topper.agent.snapshot.SnapshotAgent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetWordTopCommand extends Command {
    private final ChatTopper plugin;

    public GetWordTopCommand(ChatTopper plugin) {
        super("getwordtop", "Get the top words", "/getwordtop [from] [to]", Collections.emptyList());
        setPermission(Permissions.GET_TOP.getName());
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        int from = 1;
        int to = 10;
        if (args.length > 0) {
            try {
                from = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid from value");
                return false;
            }
        }

        if (args.length > 1) {
            try {
                to = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid to value");
                return false;
            }
        }

        if (from > to) {
            sender.sendMessage("Invalid range");
            return false;
        }

        SnapshotAgent<String, Long> agent = plugin.get(HolderManager.class).getHolder().getSnapshotAgent();
        List<Map.Entry<String, Long>> topWords = agent.getSnapshot();

        for (int i = from; i <= to; i++) {
            int index = i - 1;
            Map.Entry<String, Long> entry = null;
            if (index >= 0 && index < topWords.size()) {
                entry = topWords.get(index);
            }
            if (entry == null) {
                sender.sendMessage(i + ": No data");
            } else {
                sender.sendMessage(i + ": " + entry.getKey() + " - " + entry.getValue());
            }
        }

        return true;
    }
}
