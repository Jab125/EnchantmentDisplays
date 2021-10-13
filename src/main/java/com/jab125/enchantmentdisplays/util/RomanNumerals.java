package com.jab125.enchantmentdisplays.util;

import java.util.TreeMap;

public class RomanNumerals {
    private static final TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();
    static {
        treemap.put(1000, "M");
        treemap.put(900, "CM");
        treemap.put(500, "D");
        treemap.put(400, "CD");
        treemap.put(100, "C");
        treemap.put(90, "XC");
        treemap.put(50, "L");
        treemap.put(40, "XL");
        treemap.put(10, "X");
        treemap.put(9, "IX");
        treemap.put(5, "V");
        treemap.put(4, "IV");
        treemap.put(1, "I");

    }

    public static final String integerToRomanA(int number) {
        int l = treemap.floorKey(number);
        if (number == l) {
            return treemap.get(number);
        }
        return treemap.get(l) + integerToRomanA(number - l);
    }


    public static final String integerToRomanB(int number) {
        int i = number % 1000;
        int i2 = number / 1000;
        if (i2 > 3999) {
            int i3 = i2 % 1000;
            int i4 = i2 / 1000;
            if (i3 == 0 && i == 0) return "[[" + integerToRomanA(i4) + "]]";
            else if (i3 == 0) return "[[" + integerToRomanA(i4) + "]]" + integerToRomanB(i);
            return "[[" + integerToRomanA(i4) + "]" + integerToRomanA(i3) + "]" + integerToRomanA(i);
        }
        if (number <= 3999) return integerToRomanA(number);
        if (i == 0) return "[" + integerToRomanA(i2) + "]";
        return "[" + integerToRomanA(i2) + "]" + integerToRomanA(i);
    }


    public static final String integerToRoman(int number) {
        if (number == -2147483648) return "-[[MMCXLVII]CDLXXXIII]DCXLVIII";
        if (number > 0) return integerToRomanB(number);
        else if (number < 0)  return "-" + integerToRomanB(-number);
        else return Integer.toString(number);
    }
}
