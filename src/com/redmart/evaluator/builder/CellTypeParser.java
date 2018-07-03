package com.redmart.evaluator.builder;

import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.model.*;

public class CellTypeParser {

    public CellType parseData(String data) throws ParsingException {
        if (OperatorTypeCell.isValid(data))
            return new OperatorTypeCell(Operator.valueOf(data));
        else if (ReferenceTypeCell.isValid(data))
            return new ReferenceTypeCell(data);
        else if (ValueTypeCell.isValid(data))
            return new ValueTypeCell(data);
        else
            throw new ParsingException("Invalid data: "+ data,400);
    }

}
