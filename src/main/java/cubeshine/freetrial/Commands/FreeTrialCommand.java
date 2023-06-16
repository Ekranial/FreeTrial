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

public class FreeTrialCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("freetrial.use")) {
            return false;
        }

        try {
            Player player = Bukkit.getPlayer(strings[0]);
            commandSender.sendMessage("§aНайден игрок " + player.getName());
            HashMap<String, String> trial_players = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    trial_players.put(line.split(" ")[0], line.split(" ")[1]);
                }
            }
            reader.close();
            commandSender.sendMessage("§aПолучен список игроков");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 24);
            trial_players.put(strings[0], String.valueOf(calendar.getTimeInMillis()));
            commandSender.sendMessage("§aУказанный игрок добавлен в список");
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
            for (String name : trial_players.keySet()) {
                bw.write(name + " " + trial_players.get(name));
                bw.newLine();
            }
            bw.close();
            player.sendMessage("§aАдминистратор " + commandSender.getName() + " выдал вам пробный период на 24 часа");

        } catch (Exception e) {
            commandSender.sendMessage("§cИспользование: /trial [ник]");
            System.out.println(e);
        }

        return true;
    }
}
