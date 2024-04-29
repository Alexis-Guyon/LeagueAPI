package fr.bxcchus.utils;

import java.io.File;
import java.util.Scanner;

public class LockfileParser {

    private static String path = "C:\\Riot Games\\League of Legends\\lockfile";
    private static LockfileParser INSTANCE;
    private String port;
    private String password;

    public static LockfileParser getInstance() {
        if (INSTANCE == null) return INSTANCE = new LockfileParser(path);
        return null;
    }

    public LockfileParser(String path)  {
        LockfileParser.path = path;
        parse();
    }

    private void parse() {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String content = scanner.nextLine();
            String[] parts = content.split(":");

            setPort(parts[2]);
            setPassword(parts[3]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPath() {
        return path;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
