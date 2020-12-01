package org.matsu.DayOne;

import org.junit.Test;
import static org.matsu.Util.log;

public class TestDayOne {

    @Test
    public void runTest() {
        log("");
        log("🎄 🎄 Running testing for Day One 🎄 🎄");
        log("");

        DayOne d = new DayOne();
        d.solve();

        log("");
    }
}
