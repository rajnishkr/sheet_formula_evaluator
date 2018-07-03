package com.redmart.evaluator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueTypeCell extends CellType {

    public ValueTypeCell(String data) {
        setData(data);
    }

    public double getValue() {
        return Double.parseDouble(getData());
    }

    public static boolean isValid(String data) {
        String regex = "[+-]?\\d+";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(data);
        return matcher.matches();
    }
}
