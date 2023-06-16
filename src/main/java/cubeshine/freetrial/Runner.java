package cubeshine.freetrial;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;

public class Runner extends BukkitRunnable {
    @Override
    public void run() {
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
            Calendar now = Calendar.getInstance();
            for (String name : trial_players.keySet()) {
                long player_time = Long.parseLong(trial_players.get(name));
                if (now.getTimeInMillis() > player_time) {
                    Bukkit.getOfflinePlayer(name).setWhitelisted(false);
                    try {
                        Bukkit.getPlayer(name).kickPlayer("Ваш пробный период истек!");
                    } catch (Exception ignored) {
                    }
                    trial_players.remove(name);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt")));
                    for (String key : trial_players.keySet()) {
                        bw.write(key + " " + trial_players.get(key));
                        bw.newLine();
                    }
                    bw.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
