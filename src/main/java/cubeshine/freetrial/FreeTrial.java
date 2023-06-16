package cubeshine.freetrial;

import cubeshine.freetrial.Commands.FreeTrialCommand;
import cubeshine.freetrial.Utils.FolderUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreeTrial extends JavaPlugin {

    @Override
    public void onEnable() {
        FolderUtils.run();
        this.getCommand("trial").setExecutor(new FreeTrialCommand());
        Runner runner = new Runner();
        runner.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
