package com.redmart.evaluator;

import com.redmart.evaluator.service.SheetEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SheetEvaluator sheetEvaluator;
        Scanner sc = new Scanner(System.in);
       int n,m;

            n = sc.nextInt();
            m = sc.nextInt();
            sc.nextLine();
            List<String> inputData= new ArrayList<>();
            for(int itr=0;itr<n*m;itr++) {
                inputData.add(sc.nextLine());
            }
            sheetEvaluator = new SheetEvaluator(n,m,inputData);

        }
    }
}
