package net.vertrauterdavid.timer.util;

import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.vertrauterdavid.timer.SimpleCommandTimer;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final SimpleCommandTimer simpleCommandTimer;

    @Override
    public @NotNull String getIdentifier() {
        return "timer";
    }

    @Override
    public @NotNull String getAuthor() {
        return "VertrauterDavid";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (simpleCommandTimer.getTimers().containsKey(params)) {
            long seconds = simpleCommandTimer.getTimers().get(params).getCurrentSeconds();
            long minutes = seconds / 60;
            seconds %= 60;
            return String.format("%02dm %02ds", minutes, seconds);
        }

        return "";
    }

}
