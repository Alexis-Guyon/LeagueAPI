package fr.bxcchus.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Scanner;

@Setter
@Getter

public class LockfileParser {

    private static String path = "D:\\Riot Games\\League of Legends\\lockfile";
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

}
