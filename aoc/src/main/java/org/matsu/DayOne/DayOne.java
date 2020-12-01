package org.matsu.DayOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.matsu.Util.log;

public class DayOne {

    public void solve() {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of("inputs/day-one-input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Integer> lineNumbers = new ArrayList<>();

        for (String line : allLines) {
            int lineNumber = Integer.parseInt(line);
            lineNumbers.add(lineNumber);
        }

        int firstResult = solveFirst(lineNumbers);
        log("First result is ", firstResult);
        log("");
        int secondResult = solveSecond(lineNumbers);
        log("Second result is ", secondResult);
    }

    private int solveFirst(List<Integer> lineNumbers) {
        log("Solving ", "the ", "first ", "one");
        for (int i = 0; i < lineNumbers.size(); i++) {
            int current = lineNumbers.get(i);
            int k = i;
            while (k >= 0) {
                int currentComparison = lineNumbers.get(k);
                int sum = current + currentComparison;
                if (sum == 2020) {
                    log("Match found: ", current, " & ", currentComparison);
                    return current * currentComparison;
                }
                k--;
            }
        }
        return 0;
    }

    private int solveSecond(List<Integer> lineNumbers) {
        log("Solving ", "the ", "second ", "one");
        for (int i = 0; i < lineNumbers.size(); i++) {
            int current = lineNumbers.get(i);
            int k = i;
            while (k >= 0) {
                int second = lineNumbers.get(k);
                int sum = current + second;
                if (sum > 2020) {
                    k--;
                    continue;
                }
                int j = i;
                while (j >= 0) {
                    int third = lineNumbers.get(j);
                    int secondSum = sum + third;
                    if (secondSum == 2020) {
                        log("Match found: ", current, ", ", second, " & ", third);
                        return current * second * third;
                    }
                    j--;
                }
                k--;
            }
        }
        return 0;
    }
}
