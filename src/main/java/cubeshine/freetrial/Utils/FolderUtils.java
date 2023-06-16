package cubeshine.freetrial.Utils;

import java.io.File;
import java.io.IOException;

public class FolderUtils {
    public static void run() {
        File folder = new File(System.getProperty("user.dir") + "/plugins");
        File[] listOfFiles = folder.listFiles();
        boolean found_dir = false;
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                if (file.getName().equals("FreeTrial")) {
                    found_dir = true;
                    break;
                }
            }
        }
        if (!found_dir) {
            File new_dir = new File(System.getProperty("user.dir") + "/plugins/FreeTrial");
            new_dir.mkdir();
        }
        folder = new File(System.getProperty("user.dir") + "/plugins/FreeTrial");
        listOfFiles = folder.listFiles();
        boolean found_file = false;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().equals("SavedUsers.txt")) {
                    found_file = true;
                    break;
                }
            }
        }
        if (!found_file) {
            File new_dir = new File(System.getProperty("user.dir") + "/plugins/FreeTrial/SavedUsers.txt");
            try {
                new_dir.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
