package reports;

import model.YearlyRecord;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class YearlyReport {
    public final HashMap<Integer, ArrayList<YearlyRecord>> years;
    public final ArrayList<YearlyRecord> yearlyRecords;
    public final Scanner scanner;

    public YearlyReport() {
        years = new HashMap<>();
        yearlyRecords = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void createYearlyReport() {
        System.out.println("Введите год, за который хотите считать отчет: ");
        int year = scanner.nextInt();
        String contentYearlyReportFile = Util.readFileContentsOrNull("resources/y." + year + ".csv");
        String[] linesYearlyReportFile = contentYearlyReportFile.split("\n");
        for (int i = 1; i < linesYearlyReportFile.length; i++) {
            String[] contentYearlyReport = linesYearlyReportFile[i].split(",");
            int month = Integer.parseInt(contentYearlyReport[0]);
            int amount = Integer.parseInt(contentYearlyReport[1]);
            boolean isExpense = Boolean.parseBoolean(contentYearlyReport[2]);
            YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
            YearlyRecord.year = year;
            yearlyRecords.add(yearlyRecord);
        }
        this.years.put(year, yearlyRecords);
    }

    public int getExpensesForMonthInYear(int monthNumber) {
        int expense = 0;
        for (YearlyRecord yearlyRecord : years.get(YearlyRecord.year)) {
            if (yearlyRecord.getMonth() == monthNumber) {
                if (yearlyRecord.isExpense()) {
                    expense = yearlyRecord.getAmount();
                }
            }
        }
        return expense;
    }

    public int getIncomeMonthInYear(int monthNumber) {
        int income = 0;
        for (YearlyRecord yearlyRecord : years.get(YearlyRecord.year)) {
            if (yearlyRecord.getMonth() == monthNumber) {
                if (!yearlyRecord.isExpense()) {
                    income = yearlyRecord.getAmount();
                }
            }
        }
        return income;
    }

    public void infoAboutYear() {
        System.out.println("Рассматриваемый год: " + YearlyRecord.year);
        for (int i = 1; i < years.get(YearlyRecord.year).size() / 2 + 1; i++) {
            System.out.println("Прибыль за " + i + " месяц: " + getProfitMonthInYear(i));
        }
        System.out.println("Средний расход за все месяцы в году: " + getAverageExpense());
        System.out.println("Средний доход за все месяцы в году: " + getAverageIncome());
    }

    private int getAverageIncome() {
        int averageIncome = 0;
        for (YearlyRecord yearlyRecord : years.get(YearlyRecord.year)) {
            if (!yearlyRecord.isExpense()) {
                averageIncome += yearlyRecord.getAmount();
            }
        }
        return averageIncome / years.get(YearlyRecord.year).size() * 2;
    }


    public int getProfitMonthInYear(int monthNumber) {
        int profit = 0;
        for (YearlyRecord yearlyRecord : years.get(YearlyRecord.year)) {
            if (yearlyRecord.getMonth() == monthNumber) {
                if (!yearlyRecord.isExpense()) {
                    profit += yearlyRecord.getAmount();
                } else {
                    profit -= yearlyRecord.getAmount();
                }
            }
        }
        return profit;
    }

    public int getAverageExpense() {
        int averageExpense = 0;
        for (YearlyRecord yearlyRecord : years.get(YearlyRecord.year)) {
            if (yearlyRecord.isExpense()) {
                averageExpense += yearlyRecord.getAmount();
            }
        }
        return averageExpense / years.get(YearlyRecord.year).size() * 2;
    }
}
