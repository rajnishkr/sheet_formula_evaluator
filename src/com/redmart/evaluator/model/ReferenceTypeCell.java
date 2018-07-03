package com.redmart.evaluator.model;

import com.redmart.evaluator.service.IndexUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferenceTypeCell extends CellType {

    private int refRow;
    private int refCol;

    public ReferenceTypeCell(String data) {
        setData(data);
        String regex = "([a-zA-Z]+)(\\d+)";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(data);
        if (matcher.matches()) {
            refRow = IndexUtility.getRowIndex(matcher.group(1));
            refCol = IndexUtility.getColIndex(matcher.group(2));
        } else {
            throw new RuntimeException("Unable to parse reference: " + data);
        }

    }


    public static boolean isValid(String data) {
        String regex = "([a-zA-Z]+)(\\d+)";
            Pattern regexPattern = Pattern.compile(regex);
            Matcher matcher = regexPattern.matcher(data);
            return matcher.matches();
    }
}
