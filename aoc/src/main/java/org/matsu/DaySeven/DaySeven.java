package org.matsu.DaySeven;

import org.matsu.Util;
import static org.matsu.Util.log;

import java.util.List;
import java.util.stream.Collectors;

public class DaySeven {

    public void solve() {
        List<String> lines = Util.readInputToList("inputs/day-seven-input.txt");

        List<String> canContainGoldBag = lines.stream().filter(l -> l.split("contain")[1].contains("shiny gold bag"))
                .collect(Collectors.toList());

        List<String> allThatCanContainMyGoldBag = getBagsThatCanContainBagsThatCanContainMyBag(lines,
                canContainGoldBag);

        allThatCanContainMyGoldBag.forEach(b -> log(b.split("s contain")[0]));
        log("Input 1: ", allThatCanContainMyGoldBag.size());
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

}
