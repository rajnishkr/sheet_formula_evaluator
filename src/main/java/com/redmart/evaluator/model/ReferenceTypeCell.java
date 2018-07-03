package com.redmart.evaluator.model;

import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.service.IndexUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferenceTypeCell extends CellType {

    private int refRow;
    private int refCol;

    public ReferenceTypeCell(int refRow, int refCol) {
        this.refCol = refCol;
        this.refRow = refRow;
    }

    public ReferenceTypeCell(String data) {
        setData(data);
        String regex = "([a-zA-Z]+)(\\d+)";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(data);
        if (matcher.matches()) {
            refRow = IndexUtility.getRowIndex(matcher.group(1));
            refCol = IndexUtility.getColIndex(matcher.group(2));
        } else {
            throw new ParsingException("Unable to parse reference: " + data, 400);
        }

    }


    public static boolean isValid(String data) {
        String regex = "([a-zA-Z]+)(\\d+)";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(data);
        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenceTypeCell)) return false;

        ReferenceTypeCell that = (ReferenceTypeCell) o;

        return refRow == that.refRow && refCol == that.refCol;
    }

    @Override
    public int hashCode() {
        int result = refRow + 1;
        result = 31 * result + refCol + 1;
        return result;
    }

    public int getRefRow() {
        return refRow;
    }

    public int getRefCol() {
        return refCol;
    }
}
