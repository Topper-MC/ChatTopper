package me.hsgamer.chattopper.holder.agent;

import me.hsgamer.chattopper.holder.WordHolder;
import me.hsgamer.topper.agent.core.Agent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UpdateAgent implements Agent, Runnable {
    private final WordHolder holder;
    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    public UpdateAgent(WordHolder holder) {
        this.holder = holder;
    }

    private static String[] split(String chat) {
        return chat.split("\\s+");
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

            String[] split = split(chat);
            for (String word : split) {
                holder.getOrCreateEntry(word).setValue(value -> value + 1);
            }
        }
    }
}
