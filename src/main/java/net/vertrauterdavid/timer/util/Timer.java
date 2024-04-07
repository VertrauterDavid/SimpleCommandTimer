package net.vertrauterdavid.timer.util;

import lombok.Getter;
import net.vertrauterdavid.timer.SimpleCommandTimer;
import org.bukkit.Bukkit;

@Getter
public class Timer {

    private final long seconds;
    private long currentSeconds;

    public Timer(SimpleCommandTimer simpleCommandTimer, long seconds, String command) {
        this.seconds = seconds;
        this.currentSeconds = seconds;

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(simpleCommandTimer, () -> {
            currentSeconds--;
            if (currentSeconds == 0) {
                Bukkit.getScheduler().runTask(simpleCommandTimer, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
                currentSeconds = seconds;
            }
        }, 20, 20);
    }

}
