package net.vertrauterdavid.timer;

import lombok.Getter;
import net.vertrauterdavid.timer.util.PlaceholderAPIHook;
import net.vertrauterdavid.timer.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

@Getter
public class SimpleCommandTimer extends JavaPlugin {

    private final HashMap<String, Timer> timers = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Objects.requireNonNull(getConfig().getConfigurationSection("Timers")).getKeys(false).forEach(identifier -> {
            String s = getConfig().getString("Timers." + identifier);
            try {
                long seconds = Long.parseLong(s.split(";")[0]);
                timers.put(identifier, new Timer(this, seconds, s.replaceAll(seconds + ";", "")));
            } catch (NumberFormatException ignored) { }
        });

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook(this).register();
        }
    }

}
