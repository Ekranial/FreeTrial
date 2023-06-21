package cubeshine.freetrial;

import cubeshine.freetrial.Commands.FreeTrialCommand;
import cubeshine.freetrial.Commands.TimeLeftCommand;
import cubeshine.freetrial.PlaceHolders.TimeLeftPlaceholder;
import cubeshine.freetrial.Utils.FolderUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreeTrial extends JavaPlugin {

    @Override
    public void onEnable() {
        FolderUtils.run();
        this.getCommand("trial").setExecutor(new FreeTrialCommand());
        this.getCommand("timeleft").setExecutor(new TimeLeftCommand());
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new TimeLeftPlaceholder(this).register();
        }
        Runner runner = new Runner();
        runner.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
    }
}
