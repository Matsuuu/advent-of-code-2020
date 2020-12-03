package org.matsu.DayThree;

import static org.matsu.Util.log;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.matsu.Util;

public class DayThree {

    public void solve() {
        List<String> lines = Util.readInputToList("inputs/day-three-input.txt");

        solveFirst(lines);
        long treesTotal = solveSecond(lines);
        log("Trees hit total multiplied: ", treesTotal);
    }

    private long solveFirst(List<String> lines) {
        return slope(lines, 3, 1);
    }

    private long solveSecond(List<String> lines) {
        List<List<Integer>> slopes = Arrays.asList(Arrays.asList(1, 1), Arrays.asList(3, 1), Arrays.asList(5, 1),
                Arrays.asList(7, 1), Arrays.asList(1, 2));

        // Bcuz java is poopoo and reducing a list of longs doesn't work.
        List<Long> longs = slopes.stream().map(s -> slope(lines, s.get(0), s.get(1))).collect(Collectors.toList());
        long trees = 1;
        for (Long l : longs)
            trees *= l;
        return trees;
    }

    private long slope(List<String> lines, int right, int down) {

        log("Sloping with right: ", right, " and down: ", down);
        int trees = 0;
        int x = 0;
        int y = 0;
        int slopeHeight = lines.size() - 1;
        int slopeWidth = lines.get(0).length();

        while (y < slopeHeight) {
            y += down;
            x += right;
            // Rotate around to first parts of width
            if (slopeWidth <= x) {
                x -= slopeWidth;
            }

            char hitChar = lines.get(y).charAt(x);
            if (hitChar == '#') {
                trees++;
            }
        }

        log("Trees hit: ", trees);
        return trees;
    }
}
