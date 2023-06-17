package cubeshine.freetrial.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.HashMap;

public class TimeLeftCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            HashMap<String, String> trial_players = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    trial_players.put(line.split(" ")[0], line.split(" ")[1]);
                }
            }
            reader.close();

            if (trial_players.get(commandSender.getName()) != null) {
                Calendar now = Calendar.getInstance();
                long difference = Long.parseLong(trial_players.get(commandSender.getName())) - now.getTimeInMillis();
                long hours = difference / 3600000;
                long minutes = (difference % 3600000) / 60000;
                long seconds = (difference % 60000) / 1000;
                commandSender.sendMessage("§aДо конца пробного периода осталось " + hours + " часов " + minutes
                        + " минут " + seconds + " секунд");
            } else {
                commandSender.sendMessage("§aУ вас нет пробного периода");
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
