package com.redmart.evaluator;

import com.redmart.evaluator.exception.CyclicDependencyException;
import com.redmart.evaluator.exception.ParsingException;
import com.redmart.evaluator.service.SheetEvaluator;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        SheetEvaluator sheetEvaluator;
        Scanner sc = new Scanner(System.in);
        int row, col;

        col = sc.nextInt();
        row = sc.nextInt();
        sc.nextLine();
        List<String> inputData = new ArrayList<>();
        for (int itr = 0; itr < row * col; itr++) {
            inputData.add(sc.nextLine());
        }
        try {
            sheetEvaluator = new SheetEvaluator(row, col, inputData);
        } catch (IllegalArgumentException | ParsingException | InsufficientResourcesException e) {
            System.out.println(e.getMessage());
        } catch (CyclicDependencyException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
