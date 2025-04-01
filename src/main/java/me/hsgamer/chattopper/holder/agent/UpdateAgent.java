package me.hsgamer.chattopper.holder.agent;

import me.hsgamer.chattopper.holder.WordHolder;
import me.hsgamer.topper.agent.core.Agent;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class UpdateAgent implements Agent, Runnable {
    private final WordHolder holder;
    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    public UpdateAgent(WordHolder holder) {
        this.holder = holder;
    }

    private static List<String> split(String chat) {
        return Arrays.stream(chat.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    public void addChat(String chat) {
        queue.add(chat);
    }

    @Override
    public void run() {
        while (true) {
            String chat = queue.poll();
            if (chat == null) {
                break;
            }

            for (String word : split(chat)) {
                holder.getOrCreateEntry(word).setValue(value -> value + 1);
            }
        }
    }
}
