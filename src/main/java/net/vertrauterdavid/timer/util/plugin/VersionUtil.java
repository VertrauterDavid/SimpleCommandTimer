package net.vertrauterdavid.timer.util.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class VersionUtil {

    private double newestVersion = -1;
    private double currentVersion = -1;

    public VersionUtil(JavaPlugin javaPlugin) {
        newestVersion = getNewestVersion();
        currentVersion = getCurrentVersion(javaPlugin);

        Bukkit.getScheduler().runTaskLaterAsynchronously(javaPlugin, () -> {
            if (newestVersion == -1 || currentVersion == -1) {
                javaPlugin.getLogger().log(Level.WARNING, " ");
                javaPlugin.getLogger().log(Level.WARNING, "§cVersion check failed! If you want to report this error you can do so under:");
                javaPlugin.getLogger().log(Level.WARNING, "§chttps://github.com/VertrauterDavid");
                javaPlugin.getLogger().log(Level.WARNING, " ");
                return;
            }

            if (newestVersion > currentVersion) {
                javaPlugin.getLogger().log(Level.WARNING, " ");
                javaPlugin.getLogger().log(Level.WARNING, "§aSimpleCommandTimer started!");
                javaPlugin.getLogger().log(Level.WARNING, " ");
                javaPlugin.getLogger().log(Level.WARNING, "§fCurrent version: §c" + currentVersion + " §f| Latest version: §c" + newestVersion);
                javaPlugin.getLogger().log(Level.WARNING, " ");
                javaPlugin.getLogger().log(Level.WARNING, "§cYou can download the latest version here:");
                javaPlugin.getLogger().log(Level.WARNING, "§chttps://github.com/VertrauterDavid");
                javaPlugin.getLogger().log(Level.WARNING, " ");
                return;
            }

            javaPlugin.getLogger().log(Level.INFO, " ");
            javaPlugin.getLogger().log(Level.INFO, "§aSimpleCommandTimer successfully started!");
            javaPlugin.getLogger().log(Level.INFO, " ");
            javaPlugin.getLogger().log(Level.INFO, "§fCurrent version: §a" + currentVersion + " §f| Latest version: §a" + newestVersion);
            javaPlugin.getLogger().log(Level.INFO, "§aYou are up to date!");
            javaPlugin.getLogger().log(Level.INFO, " ");
        }, 20L);
    }

    public double getNewestVersion() {
        if (this.newestVersion != -1) {
            return this.newestVersion;
        }

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.vertrauterdavid.net/plugins/checkVersionFree.php").openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            byte[] input = ("product=SimpleCommandTimer").getBytes(StandardCharsets.UTF_8);
            httpURLConnection.getOutputStream().write(input, 0, input.length);
            httpURLConnection.getResponseCode();
            newestVersion = Double.parseDouble(new String(httpURLConnection.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
            return newestVersion;
        } catch (Exception ignored) { }

        return 0;
    }

    public double getCurrentVersion(JavaPlugin javaPlugin) {
        if (this.currentVersion != -1) {
            return this.currentVersion;
        }

        double currentVersion = 0;

        try {
            currentVersion = Double.parseDouble(javaPlugin.getDescription().getVersion());
        } catch (NumberFormatException ignored) { }

        return currentVersion;
    }

}