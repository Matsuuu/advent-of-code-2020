package org.matsu.DaySix;

import static org.matsu.Util.log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.matsu.Util;

public class DaySix {

    private class Group {
        public int yesAnswerCount;

        public Group(String groupInput) {
            Map<String, Boolean> seen = new HashMap<>();
            for (String c : groupInput.replaceAll("\n", "").split("")) {
                if (!seen.containsKey(c)) {
                    seen.put(c, true);
                }
            }
            yesAnswerCount = seen.size();
        }
    }

    public void solve() {
        String input = Util.readInputToString("inputs/day-six-input.txt");

        List<Group> groups = Arrays.asList(input.split("\n\n")).stream().map(Group::new).collect(Collectors.toList());
        int total = groups.stream().collect(Collectors.summingInt(g -> g.yesAnswerCount));
        log("Total: ", total);
    }
}
