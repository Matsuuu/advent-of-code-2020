package org.matsu.DayFive;

import static org.matsu.Util.log;

import java.util.List;
import java.util.stream.Collectors;

import org.matsu.Util;

public class DayFive {

    private class Seat {
        public long row;
        public long column;
        public long id;

        public Seat(long row, long column, long id) {
            this.row = row;
            this.column = column;
            this.id = id;
        }

    }

    public void solve() {
        List<String> lines = Util.readInputToList("inputs/day-five-input.txt");
        List<Seat> seats = lines.stream().map(l -> solveSeat(l)).collect(Collectors.toList());

        List<Seat> seatsSorted = seats.stream().sorted((a, b) -> Long.compare(b.id, a.id)).collect(Collectors.toList());
        long sanityCheck = seatsSorted.get(0).id;
        log("First input: ", sanityCheck);

        long max = seatsSorted.get(0).id;
        int i = 0;
        int size = seatsSorted.size();

        while (i < size) {
            long seatId = seatsSorted.get(i).id;

            if (seatId != max) {
                log("Your seat is: ", seatId + 1);
                break;
            }
            i++;
            max--;
        }
    }

    private Seat solveSeat(String line) {
        long rowMax = 127;
        long rowMin = 0;

        String rowString = line.substring(0, 7);
        for (String r : rowString.split("")) {
            if (r.equals("F"))
                rowMax = (long) (Math.floor(rowMax - (rowMax - rowMin) / 2.0));
            else
                rowMin = (long) (Math.ceil(rowMin + (rowMax - rowMin) / 2.0));
        }

        long colMin = 0;
        long colMax = 7;
        String colString = line.substring(7);
        for (String c : colString.split("")) {
            if (c.equals("L")) {
                colMax = (long) (Math.floor(colMax - (colMax - colMin) / 2.0));
            } else {
                colMin = (long) (Math.ceil(colMax - (colMax - colMin) / 2.0));
            }
        }
        return new Seat(rowMax, colMax, rowMax * 8 + colMax);
    }

}
