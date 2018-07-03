package com.redmart.evaluator;
import com.redmart.evaluator.service.SheetEvaluator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EvaluatorTestCases {

    private List<String> input;
    private  SheetEvaluator sheetEvaluator;
    private int row;
    private int col;


    @Before
    public void initialize() {
        input= new ArrayList<String>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWithOutCycle() {
        row = 2;
        col = 3;
        input.add("A2");
        input.add("4 5 *");
        input.add("A1");
        input.add("A1 B2 / 2 +");
        input.add("3");
        input.add("39 B1 B2 * /");
        try {
            sheetEvaluator = new SheetEvaluator(row, col, input);
            Assert.assertFalse(sheetEvaluator.isCyclicDependency());
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "");
        }
    }

        @Test
        @SuppressWarnings("unchecked")
        public void testWithCycle() {
            row =2;
            col =3;
            input.add("A2");
            input.add("A1");
            input.add("4 5 *");
            input.add("A1 B2 / 2 +");
            input.add("3");
            input.add("39 B2 *");
            try {
                System.out.println("\n\nTestWithCycle :\n");
                sheetEvaluator = new SheetEvaluator(row, col, input);
                Assert.assertTrue(sheetEvaluator.isCyclicDependency());
                Assert.assertFalse(
                        sheetEvaluator.getSheetDao().getSheet().getCell(0,0).isEvaluated());
                Assert.assertFalse(
                        sheetEvaluator.getSheetDao().getSheet().getCell(0,1).isEvaluated());
                Assert.assertFalse(
                        sheetEvaluator.getSheetDao().getSheet().getCell(1,0).isEvaluated());
            }catch (Exception e){
                Assert.assertEquals(e.getMessage(),"");
            }
    }
}
