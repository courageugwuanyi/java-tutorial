package com.cloudtruss.calculator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public float add(float num1, float num2) {
        return  (num1 + num2);
    }

    public String calcAnnuity(String periodPaymentAmount, int time, String rate, int compoundingTerm) {
        BigDecimal rOverN = new BigDecimal(rate).divide(new BigDecimal(compoundingTerm));
        BigDecimal onePlusROverN = BigDecimal.ONE.add(rOverN);
        BigDecimal raiseToPowerNT = onePlusROverN.pow(compoundingTerm * time);
        BigDecimal numerator = raiseToPowerNT.subtract(BigDecimal.ONE);
        BigDecimal mainEquation = numerator.divide(rOverN);
        BigDecimal anwser = mainEquation.multiply(new BigDecimal(periodPaymentAmount));

        NumberFormat answerToLocale = NumberFormat.getCurrencyInstance(Locale.US);
        return answerToLocale.format(anwser);
    }
}
