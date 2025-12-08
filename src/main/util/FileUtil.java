package main.util;

import java.io.*;


public class FileUtil {
    public static void log(String text) {
        try (FileWriter fw = new FileWriter("app_log.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Log failed: " + e.getMessage()); 
        }
    }
}
