package com.redmart.evaluator.service;

public class IndexUtility {

    public static int getRowIndex(String row) {
        int index = 0;
        for (int i = 0; i < row.length(); i++) {
            index = index * 26 + row.charAt(i) - 'A' + 1;
        }
        return (--index);
    }

    public static int getColIndex(String col) {
        return Integer.valueOf(col) - 1;
    }

    public static String getRowName(int row) {
        int num = row % 26;
        String ch = String.valueOf((char) ('A' + num));
        row = (row) / 26;
        if (row > 0)
            return getRowName(row - 1) + ch;
        else
            return ch;
    }
}
