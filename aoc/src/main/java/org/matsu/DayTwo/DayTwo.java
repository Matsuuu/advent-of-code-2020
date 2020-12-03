package org.matsu.DayTwo;

import static org.matsu.Util.log;

import java.util.List;

import org.matsu.Util;

public class DayTwo {

    public class SolvingObject {
        public int min;
        public int max;
        public char requiredChar;
        public String password;

        public SolvingObject(int min, int max, char requiredChar, String password) {
            this.min = min;
            this.max = max;
            this.requiredChar = requiredChar;
            this.password = password;
        }
    }

    private SolvingObject createSolvingObject(String line) {
        String[] splitLine = line.split(" ");
        String[] policyCountLimits = splitLine[0].split("-");

        char requiredChar = splitLine[1].charAt(0);
        int min = Integer.parseInt(policyCountLimits[0]);
        int max = Integer.parseInt(policyCountLimits[1]);
        String password = splitLine[2];
        return new SolvingObject(min, max, requiredChar, password);
    }

    public void solve() {
        List<String> allLines = Util.readInputToList("inputs/day-two-input.txt");

        int validCount = solveFirst(allLines);
        log("Valid passwords: ", validCount);

        log("");
        int validCountTwo = solveSecond(allLines);
        log("Valid passwords for number two: ", validCountTwo);
    }

    /**
     * Return the number of valid passwords
     */
    private int solveFirst(List<String> lines) {
        int validCount = 0;
        for (String line : lines) {
            SolvingObject solvObj = createSolvingObject(line);
            if (isValidPasswordOne(solvObj)) {
                validCount++;
            }
        }
        return validCount;
    }

    private int solveSecond(List<String> lines) {
        int validCount = 0;
        for (String line : lines) {
            SolvingObject solvObj = createSolvingObject(line);
            if (isValidPasswordTwo(solvObj)) {
                validCount++;
            }
        }
        return validCount;
    }

    private boolean isValidPasswordOne(SolvingObject solvObj) {
        long occurances = solvObj.password.chars().filter(ch -> ch == solvObj.requiredChar).count();
        return occurances >= solvObj.min && occurances <= solvObj.max;
    }

    private boolean isValidPasswordTwo(SolvingObject solvObj) {
        return solvObj.password.charAt(solvObj.min - 1) == solvObj.requiredChar
                ^ solvObj.password.charAt(solvObj.max - 1) == solvObj.requiredChar;
    }

}
