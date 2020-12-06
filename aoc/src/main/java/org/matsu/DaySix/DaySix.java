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
        public long yesAnswerCount;
        public long allAgreeCount;

        public Group(String groupInput) {
            List<String> passengers = Arrays.asList(groupInput.split("\n"));
            Map<String, Integer> answers = new HashMap<>();

            passengers.forEach(p -> {
                Arrays.asList(p.split("")).forEach(c -> {
                    answers.putIfAbsent(c, 0);
                    answers.put(c, answers.get(c) + 1);
                });
            });

            yesAnswerCount = answers.size();
            allAgreeCount = answers.entrySet().stream().filter(e -> e.getValue() == passengers.size()).count();
        }
    }

    public void solve() {
        String input = Util.readInputToString("inputs/day-six-input.txt");

        List<Group> groups = Arrays.asList(input.split("\n\n")).stream().map(Group::new).collect(Collectors.toList());
        long total = groups.stream().collect(Collectors.summingLong(g -> g.yesAnswerCount));
        long allAgree = groups.stream().collect(Collectors.summingLong(g -> g.allAgreeCount));
        log("Total: ", total);
        log("All agree count: ", allAgree);
    }
}
