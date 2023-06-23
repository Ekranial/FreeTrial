package cubeshine.freetrial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;

public class PassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("freetrial.use")) {
            return false;
        }

        try {
            Player player = Bukkit.getPlayer(strings[0]);
            HashMap<String, String> trial_players = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    trial_players.put(line.split(" ")[0], line.split(" ")[1]);
                }
            }
            reader.close();
            if (trial_players.get(player.getName()) == null) {
                commandSender.sendMessage("§aУ данного игрока уже есть проходка");
                return true;
            }
            trial_players.remove(player.getName());
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
            for (String name : trial_players.keySet()) {
                bw.write(name + " " + trial_players.get(name));
                bw.newLine();
            }
            bw.close();
            player.sendMessage("§aАдминистратор " + commandSender.getName() + " выдал вам проходку");
            commandSender.sendMessage("§aПроходка успешно выдана игроку " + player.getName());

        } catch (Exception e) {
            commandSender.sendMessage("§cИспользование: /pass [ник]");
            System.out.println(e);
        }

        return true;
    }
}
