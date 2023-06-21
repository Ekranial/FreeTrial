package cubeshine.freetrial.PlaceHolders;

import cubeshine.freetrial.FreeTrial;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.HashMap;

public class TimeLeftPlaceholder extends PlaceholderExpansion {

    private final FreeTrial plugin;

    public TimeLeftPlaceholder(FreeTrial plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "freetrial";
    }

    @Override
    public String getAuthor() {
        return "Ekran1al";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("timeleft")){
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

                Player user = Bukkit.getPlayer(player.getName());

                if (trial_players.get(user.getName()) != null) {
                    Calendar now = Calendar.getInstance();
                    long difference = Long.parseLong(trial_players.get(user.getName())) - now.getTimeInMillis();
                    long hours = difference / 3600000;
                    long minutes = (difference % 3600000) / 60000;
                    long seconds = (difference % 60000) / 1000;
                    return ("§aОсталось " + hours + " ч " + minutes
                            + " м " + seconds + " с");
                } else {
                    return ("§aПроходка куплена ☑");
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null; // Placeholder is unknown by the Expansion
    }

}

