package net.vertrauterdavid.timer;

import lombok.Getter;
import net.vertrauterdavid.timer.util.PlaceholderAPIHook;
import net.vertrauterdavid.timer.util.Timer;
import net.vertrauterdavid.timer.util.plugin.StatisticUtil;
import net.vertrauterdavid.timer.util.plugin.VersionUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

@Getter
public class SimpleCommandTimer extends JavaPlugin {

    private final HashMap<String, Timer> timers = new HashMap<>();

    @Override
    public void onEnable() {
        new StatisticUtil(this);
        new VersionUtil(this);

        saveDefaultConfig();

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            loadTimers();
            loadStartup();
        });

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook(this).register();
        }
    }

    private void loadTimers() {
        Objects.requireNonNull(getConfig().getConfigurationSection("Timers")).getKeys(false).forEach(identifier -> {
            String s = getConfig().getString("Timers." + identifier);
            try {
                long seconds = Long.parseLong(s.split(";")[0]);
                timers.put(identifier, new Timer(this, seconds, s.replaceAll(seconds + ";", "")));
            } catch (NumberFormatException ignored) { }
        });
    }

    private void loadStartup() {
        Objects.requireNonNull(getConfig().getConfigurationSection("Startup")).getKeys(false).forEach(identifier -> {
            String s = getConfig().getString("Startup." + identifier);
            try {
                long seconds = Long.parseLong(s.split(";")[0]);
                Bukkit.getScheduler().runTaskLater(this, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replaceAll(seconds + ";", "")), 20 * seconds);
            } catch (NumberFormatException ignored) { }
        });
    }

}
