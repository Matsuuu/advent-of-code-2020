package org.matsu.DayEight;

import org.matsu.Util;
import static org.matsu.Util.log;

import java.util.ArrayList;
import java.util.List;

public class DayEight {

    private int accReg = 0;
    private int pointer = 0;
    private List<Integer> history = new ArrayList<>();

    public void solve() {
        List<String> lines = Util.readInputToList("inputs/day-eight-input.txt");

        checkForFirstRepeat(lines);
        log("");
        fixBootloop(lines);
    }

    private void reset() {
        history = new ArrayList<>();
        accReg = 0;
        pointer = 0;
    }

    private void checkForFirstRepeat(List<String> lines) {
        boolean repeat = false;
        while (!repeat) {
            String opt = lines.get(pointer);
            String instruction = getInstruction(opt);
            int arg = getArg(opt);

            if (history.contains(pointer)) {
                repeat = true;
                log("Repeat at line: ", pointer, ": ", opt);
                log("Dumping acc register: ", accReg);
                break;
            }
            history.add(pointer);
            handleInstruction(instruction, arg);
        }
    }

    private void fixBootloop(List<String> lines) {
        int attemptedPointer = 0;
        while (true) {
            try {
                reset();
                List<String> linesCopy = new ArrayList<>();
                linesCopy.addAll(lines);
                for (int i = attemptedPointer; i < linesCopy.size(); i++) {
                    String line = linesCopy.get(i);
                    String instruction = getInstruction(line);

                    if (instruction.equals("nop") || instruction.equals("jmp")) {
                        attemptedPointer = i;
                        String newInst = (instruction.equals("nop") ? "jmp" : "nop") + line.substring(3);
                        log("Changing option ", line, " at line ", i);
                        linesCopy.set(i, newInst);
                        break;
                    }
                }
                if (attemptedPointer > linesCopy.size())
                    break;
                log("Checking. Attempted pointer: ", attemptedPointer, ", linesCopy size: ", linesCopy.size());
                log("Line ", attemptedPointer, " is now ", linesCopy.get(attemptedPointer));
                runGame(linesCopy);

                attemptedPointer++;
                log("");
            } catch (Exception e) {
                e.printStackTrace();
                log("Crash due to out of bounds!");
                log("Pointer: ", pointer);
                log("Dumping acc register: ", accReg);
                break;
            }
        }
    }

    private void runGame(List<String> lines) {
        int instructionCount = 0;
        while (instructionCount < 10000) {
            String opt = lines.get(pointer);
            String instruction = getInstruction(opt);
            int arg = getArg(opt);

            handleInstruction(instruction, arg);
            instructionCount++;
        }
    }

    private String getInstruction(String opt) {
        return opt.substring(0, 3);
    }

    private int getArg(String opt) {
        return Integer.parseInt(opt.substring(4).replace("+", ""));
    }

    private void handleInstruction(String instruction, int arg) {
        switch (instruction) {
            case "jmp":
                pointer += arg;
                break;
            case "acc":
                accReg += arg;
            default:
                pointer++;
        }
    }
}
