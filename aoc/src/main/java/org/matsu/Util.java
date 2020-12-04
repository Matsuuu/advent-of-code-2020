package org.matsu;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Util {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final List<String> COLORS = Arrays.asList(ANSI_GREEN, ANSI_RED, ANSI_BLUE, ANSI_YELLOW, ANSI_PURPLE,
            ANSI_CYAN);

    public static void log(Object... args) {
        int i = 0;
        for (Object a : args) {
            System.out.print(COLORS.get(i) + a + ANSI_RESET);
            i++;

            if (i >= COLORS.size())
                i = 0;
        }
        System.out.print("\n");
    }

    public static List<String> readInputToList(String fileName) {
        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }

    public static String readInputToString(String fileName) {
        String fileString = "";
        try {
            fileString = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }
}
