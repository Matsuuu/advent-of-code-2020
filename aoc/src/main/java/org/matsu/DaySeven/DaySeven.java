package org.matsu.DaySeven;

import org.matsu.Util;
import static org.matsu.Util.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaySeven {

    public void solve() {
        List<String> lines = Util.readInputToList("inputs/day-seven-input.txt");

        List<String> canContainGoldBag = lines.stream().filter(l -> l.split("contain")[1].contains("shiny gold bag"))
                .collect(Collectors.toList());

        List<String> allThatCanContainMyGoldBag = getBagsThatCanContainBagsThatCanContainMyBag(lines,
                canContainGoldBag);

        log("Input 1: ", allThatCanContainMyGoldBag.size());

        String shinyGoldBagRequirements = lines.stream().filter(l -> l.split("contain")[0].contains("shiny gold bag"))
                .findFirst().orElse(null);
        int bagCount = getRequiredBags(lines, shinyGoldBagRequirements);
        log("");
        log("We need ", bagCount, " bags");
    }

    private List<String> getBagsThatCanContainBagsThatCanContainMyBag(List<String> lines,
            List<String> canContainGoldBag) {

        int i = 0;
        while (i < canContainGoldBag.size()) {
            String bagName = canContainGoldBag.get(i).split("s contain")[0];
            for (String l : lines) {
                String containString = l.split("contain")[1];
                if (containString.contains(bagName) && !canContainGoldBag.contains(l)) {
                    canContainGoldBag.add(l);
                }
            }
            i++;
        }

        canContainGoldBag.sort((a, b) -> a.compareTo(b));
        return canContainGoldBag;
    }

    private int getRequiredBags(List<String> lines, String shinyGoldBagReq) {

        String req = shinyGoldBagReq;
        int reqCount = 1;
        Map<String, Integer> bagsToCheck = new HashMap<>();
        int count = 0;

        while (true) {
            List<String> bagsNeeded = Arrays.asList(req.split("contain ")[1].split("[\\.,]"));

            for (String b : bagsNeeded) {
                if (req.length() <= 0)
                    continue;

                String c = b.replaceAll("\\D", "");
                if (c.length() <= 0)
                    continue;

                String bagName = b.split(c + " ")[1].trim();
                bagName = bagName.substring(0, bagName.length() - 1);
                int cInt = Integer.parseInt(c);
                if (bagsToCheck.containsKey(bagName)) {
                    bagsToCheck.put(bagName, bagsToCheck.get(bagName) + cInt * reqCount);
                } else {
                    bagsToCheck.put(bagName, cInt * reqCount);
                }
                count += cInt * reqCount;
            }
            if (bagsToCheck.size() <= 0) {
                break;
            }
            String bName = bagsToCheck.keySet().stream().findFirst().orElse("");
            reqCount = bagsToCheck.get(bName);

            bagsToCheck.remove(bName);
            req = lines.stream().filter(l -> l.split("contain")[0].contains(bName)).findFirst().orElse("");
            if (req.length() <= 1) {
                throw new Error("Could not find " + bName);
            }
        }

        return count;
    }
}
