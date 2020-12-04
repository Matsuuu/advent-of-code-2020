package org.matsu.DayFour;

import org.matsu.Util;
import static org.matsu.Util.log;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DayFour {

    Pattern hclPattern = Pattern.compile("#[a-f0-9]{6}");
    Pattern pidPattern = Pattern.compile("\\d{9}");
    List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    private class Passport {
        public String byr;
        public String iyr;
        public String eyr;
        public String hgt;
        public String hcl;
        public String ecl;
        public String pid;
        public String cid;

        public Passport(String passString) {
            passString = passString.replace("\n", " ");
            for (String entry : passString.split(" ")) {
                String[] eKeyVal = entry.split(":");
                switch (eKeyVal[0]) {
                    case "byr":
                        this.byr = eKeyVal[1];
                        break;
                    case "iyr":
                        this.iyr = eKeyVal[1];
                        break;
                    case "eyr":
                        this.eyr = eKeyVal[1];
                        break;
                    case "hgt":
                        this.hgt = eKeyVal[1];
                        break;
                    case "hcl":
                        this.hcl = eKeyVal[1];
                        break;
                    case "ecl":
                        this.ecl = eKeyVal[1];
                        break;
                    case "pid":
                        this.pid = eKeyVal[1];
                        break;
                    case "cid":
                        this.cid = eKeyVal[1];
                        break;
                }
            }
        }

        public boolean isValid() {
            return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null
                    && pid != null;
        }

        public boolean isValidSecond() {
            int byr = Integer.parseInt(this.byr);
            int iyr = Integer.parseInt(this.iyr);
            int eyr = Integer.parseInt(this.eyr);
            String hgtUnit = this.hgt.replaceAll("\\d+", "");
            int hgt = Integer.parseInt(this.hgt.replaceAll("\\D+", ""));
            if (byr < 1920 || byr > 2002)
                return false;
            if (iyr < 2010 || iyr > 2020)
                return false;
            if (eyr < 2020 || eyr > 2030)
                return false;
            if (!hgtUnit.equals("in") && !hgtUnit.equals("cm"))
                return false;
            if (hgtUnit.equals("in") && (hgt < 59 || hgt > 76))
                return false;
            if (hgtUnit.equals("cm") && (hgt < 150 || hgt > 193))
                return false;
            if (!hclPattern.matcher(hcl).find())
                return false;
            if (!eyeColors.contains(ecl))
                return false;
            if (!pidPattern.matcher(pid).find() || pid.length() != 9)
                return false;
            return true;
        }
    }

    public void solve() {
        String input = Util.readInputToString("inputs/day-four-input.txt");

        List<String> list = Arrays.asList(input.split("\n\n"));
        List<Passport> passports = list.stream().map(l -> new Passport(l)).collect(Collectors.toList());

        List<Passport> validPassports = passports.stream().filter(Passport::isValid).collect(Collectors.toList());
        long firstValidCount = validPassports.size();
        log("First valid count: ", firstValidCount);

        long secondValidCount = validPassports.stream().filter(Passport::isValidSecond).count();
        log("Second valid count: ", secondValidCount);
    }
}
