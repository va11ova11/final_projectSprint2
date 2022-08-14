import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();

    void createPort() {
        for (int i = 1; i < 4; i++) {
            buildReport(i);
        }
    }

    void buildReport(int monthNumber) {
        String content = Util.readFileContentsOrNull("resources/m.20210" + monthNumber + ".csv");
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] line = lines[i].split(",");

            int sum_of_one = Integer.parseInt(line[3]);
            int quantity = Integer.parseInt(line[2]);
            boolean is_expense = Boolean.parseBoolean(line[1]);
            String item_name = line[0];

            MonthlyRecord monthlyRecord = new MonthlyRecord(item_name, is_expense, quantity, sum_of_one, monthNumber);
            monthlyRecords.add(monthlyRecord);
        }


    }

    int checkExpense(int monthNumber, ArrayList<MonthlyRecord> monthlyRecords) {
        int expenses = 0;
        if (!monthlyRecords.isEmpty()) {
            for (MonthlyRecord monthlyRecord : monthlyRecords) {
                if (monthlyRecord.month_number == monthNumber) {
                    expenses += monthlyRecord.getExpenses();
                }
            }
        }
        return expenses;
    }

    int checkIncome(int monthNumber, ArrayList<MonthlyRecord> monthlyRecords) {
        int income = 0;
        if (!monthlyRecords.isEmpty()) {
            for (MonthlyRecord monthlyRecord : monthlyRecords) {
                if (monthlyRecord.month_number == monthNumber) {
                    income += monthlyRecord.getIncome();
                }
            }
        }
        return income;
    }

    void infoAboutAllMonths(ArrayList<MonthlyRecord> monthlyRecords) {
        if (!monthlyRecords.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Месяц " + (i + 1));
                System.out.println("Самый прибыльный товар - " + getTheMostProfitableItemOfTheMonths(monthlyRecords).get(i).item_name + " на сумму: " + getTheMostProfitableItemOfTheMonths(monthlyRecords).get(i).getIncome());
                System.out.println("Самая больша трата - " + getMaxExpensesOfTheMonths(monthlyRecords).get(i).item_name + " на сумму: " + getMaxExpensesOfTheMonths(monthlyRecords).get(i).getExpenses());
            }
        } else {
            System.out.println("Сначала загрузите отчёты");
        }
    }

    HashMap<Integer, MonthlyRecord> getTheMostProfitableItemOfTheMonths(ArrayList<MonthlyRecord> monthlyRecords) {
        HashMap<Integer, MonthlyRecord> mostProfitableItemsOfTheMonths = new HashMap<>();
        int maxFirstIncome = 0;
        int maxSecondIncome = 0;
        int maxThirdIncome = 0;
        for (MonthlyRecord monthlyRecord : monthlyRecords) {
            if (monthlyRecord.month_number == 1) {
                if (!(monthlyRecord.is_expense) && monthlyRecord.getIncome() > maxFirstIncome) {
                    mostProfitableItemsOfTheMonths.put(0, monthlyRecord);
                    maxFirstIncome = monthlyRecord.getIncome();
                }
            }
            if (monthlyRecord.month_number == 2) {
                if (!(monthlyRecord.is_expense) && monthlyRecord.getIncome() > maxSecondIncome) {
                    mostProfitableItemsOfTheMonths.put(1, monthlyRecord);
                    maxSecondIncome = monthlyRecord.getIncome();
                }
            }
            if (monthlyRecord.month_number == 3) {
                if (!(monthlyRecord.is_expense) && monthlyRecord.getIncome() > maxThirdIncome) {
                    mostProfitableItemsOfTheMonths.put(2, monthlyRecord);
                    maxThirdIncome = monthlyRecord.getIncome();
                }
            }
        }
        return mostProfitableItemsOfTheMonths;
    }

    HashMap<Integer, MonthlyRecord> getMaxExpensesOfTheMonths(ArrayList<MonthlyRecord> monthlyRecords) {
        HashMap<Integer, MonthlyRecord> maxExpenseOfTheMonths = new HashMap<>();
        int maxFirstExpense = 0;
        int maxSecondExpense = 0;
        int maxThirdExpense = 0;
        for (MonthlyRecord monthlyRecord : monthlyRecords) {
            if (monthlyRecord.month_number == 1) {
                if (monthlyRecord.is_expense && monthlyRecord.getExpenses() > maxFirstExpense) {
                    maxExpenseOfTheMonths.put(0, monthlyRecord);
                    maxFirstExpense = monthlyRecord.getExpenses();
                }
            }
            if (monthlyRecord.month_number == 2) {
                if (monthlyRecord.is_expense && monthlyRecord.getExpenses() > maxSecondExpense) {
                    maxExpenseOfTheMonths.put(1, monthlyRecord);
                    maxSecondExpense = monthlyRecord.getExpenses();
                }
            }
            if (monthlyRecord.month_number == 3) {
                if (monthlyRecord.is_expense && monthlyRecord.getExpenses() > maxThirdExpense) {
                    maxExpenseOfTheMonths.put(2, monthlyRecord);
                    maxThirdExpense = monthlyRecord.getExpenses();
                }
            }
        }
        return maxExpenseOfTheMonths;
    }
}
