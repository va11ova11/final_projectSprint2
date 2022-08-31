package reports;

import model.MonthlyRecord;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
<<<<<<< HEAD
    public final HashMap<Integer, ArrayList<MonthlyRecord>> months = new HashMap<>();
=======
    HashMap<Integer, ArrayList<MonthlyRecord>> months = new HashMap<>();
>>>>>>> origin/master

    public void createReport() {
        for (int number = 1; number <= 3; number++) {
            ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();
            String contentFile = Util.readFileContentsOrNull("resources/m.20210" + number + ".csv");
            String[] lines = contentFile.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String[] content = lines[i].split(",");
                String itemName = content[0];
                boolean isExpense = Boolean.parseBoolean(content[1]);
                int quantity = Integer.parseInt(content[2]);
                int sumOfOne = Integer.parseInt(content[3]);
                MonthlyRecord oneRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
                monthlyRecords.add(oneRecord);
            }
            months.put(number, monthlyRecords);
        }
    }

    public int getExpensesForMonth(int monthNumber, HashMap<Integer, ArrayList<MonthlyRecord>> monthlyRecordHashMap) {
        int expenses = 0;
        for (MonthlyRecord monthlyRecord : monthlyRecordHashMap.get(monthNumber)) {
            expenses += monthlyRecord.getExpenses();
        }
        return expenses;
    }

    public int getProfitForMonth(int monthNumber, HashMap<Integer, ArrayList<MonthlyRecord>> monthlyRecordHashMap) {
        int income = 0;
        for (MonthlyRecord monthlyRecord : monthlyRecordHashMap.get(monthNumber)) {
            income += monthlyRecord.getIncome();
        }
        return income;
    }

    public void infoAboutAllMonths() {
        if (!months.isEmpty()) {
            for (int i = 1; i < months.size() + 1; i++) {
                MonthlyRecord maxProfitRecord = getTheMostProfitableItemOfTheMonths(i);
                MonthlyRecord maxExpenseRecord = getMaxExpenseInMonths(i);
                System.out.println("Месяц " + (i));
                System.out.println("Самый прибыльный товар - " + maxProfitRecord.getItemName() +
                        " на сумму: " + maxProfitRecord.getIncome());
                System.out.println("Самая больша трата - " + maxExpenseRecord.getItemName() +
                        " на сумму: " + maxExpenseRecord.getExpenses());
            }
        } else {
            System.out.println("Сначала загрузите месячные отчёты!");
        }
    }

    MonthlyRecord getMaxExpenseInMonths(int monthNumber) {
        MonthlyRecord maxExpenseRecord = months.get(monthNumber).get(0);
        for (int i = 1; i < months.get(monthNumber).size(); i++) {
            if (months.get(monthNumber).get(i).getExpenses() > maxExpenseRecord.getExpenses()) {
                maxExpenseRecord = months.get(monthNumber).get(i);
            }
        }
        return maxExpenseRecord;
    }


    MonthlyRecord getTheMostProfitableItemOfTheMonths(int monthNumber) {
            MonthlyRecord mostProfitableItem = months.get(monthNumber).get(0);
            for (int i = 1; i < months.get(monthNumber).size(); i++) {
                if (months.get(monthNumber).get(i).getIncome() > mostProfitableItem.getIncome()) {
                    mostProfitableItem = months.get(monthNumber).get(i);
                }
            }
            return mostProfitableItem;
        }
    }
