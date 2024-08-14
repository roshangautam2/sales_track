package com.example.task_treeleaf.utils;


import java.text.DecimalFormat;

public class AmountToWordsConverter {
    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String convertAmountToWords(double amount) {
        if (amount == 0) {
            return "Zero Rupees Only";
        }

        long rupees = (long) amount;
        int paise = (int) Math.round((amount - rupees) * 100);

        String words = convertToWords(rupees) + " Rupees";
        if (paise > 0) {
            words += " and " + convertToWords(paise) + " Paisa";
        }
        words += " Only";
        return words;
    }

    private static String convertToWords(long num) {
        if (num < 10) {
            return units[(int) num];
        } else if (num < 20) {
            return teens[(int) (num - 10)];
        } else if (num < 100) {
            return tens[(int) (num / 10)] + ((num % 10 != 0) ? " " + convertToWords(num % 10) : "");
        } else if (num < 1000) {
            return units[(int) (num / 100)] + " Hundred" + ((num % 100 != 0) ? " " + convertToWords(num % 100) : "");
        } else if (num < 100000) {
            return convertToWords(num / 1000) + " Thousand" + ((num % 1000 != 0) ? " " + convertToWords(num % 1000) : "");
        } else if (num < 10000000) {
            return convertToWords(num / 100000) + " Lakh" + ((num % 100000 != 0) ? " " + convertToWords(num % 100000) : "");
        } else {
            return convertToWords(num / 10000000) + " Crore" + ((num % 10000000 != 0) ? " " + convertToWords(num % 10000000) : "");
        }
    }

}
