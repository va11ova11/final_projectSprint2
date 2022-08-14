import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<YearlyRecord> yearlyRecords = new ArrayList<>();

    void createPort() {
        for (int i = 2021; i == 2021; i++) {
            buildReport();
        }
    }


    void buildReport() {
        String content = Util.readFileContentsOrNull("resources/y.2021.csv");

        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] line = lines[i].split(",");

            int month = Integer.parseInt(line[0]);
            int amount = Integer.parseInt(line[1]);
            boolean is_expense = Boolean.parseBoolean(line[2]);

            YearlyRecord yearlyRecord = new YearlyRecord(month, amount, is_expense);
            yearlyRecords.add(yearlyRecord);
        }
    }

    int checkExpense(int monthNumber, ArrayList<YearlyRecord> yearlyRecords) {
        int expenses = 0;
        if (!yearlyRecords.isEmpty()) {
            for (YearlyRecord yearlyRecord : yearlyRecords) {
                if (yearlyRecord.is_expense) {
                    if (yearlyRecord.month == monthNumber) {
                        expenses += yearlyRecord.amount;
                    }
                }
            }
        }
        return expenses;
    }

    int checkIncome(int monthNumber, ArrayList<YearlyRecord> yearlyRecords) {
        int income = 0;
        if (!yearlyRecords.isEmpty()) {
            for (YearlyRecord yearlyRecord : yearlyRecords) {
                if (!yearlyRecord.is_expense) {
                    if (yearlyRecord.month == monthNumber) {
                        income += yearlyRecord.amount;
                    }
                }
            }
        }
        return income;
    }

    void infoAboutYears(ArrayList<YearlyRecord> yearlyRecords) {
        if (!yearlyRecords.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Прибыль за " + (i + 1) + " месяц: " + getProfitForEachMonth(yearlyRecords).get(i));
            }
            System.out.println("Средний расход за все месяцы в году: " + averageExpenseAllMonths(yearlyRecords));
            System.out.println("Средний доход за все месяцы в году: " + averageIncomeAllMonths(yearlyRecords));
        } else {
            System.out.println("Сначала загрузите отчёты");
        }
    }

    HashMap<Integer, Integer> getProfitForEachMonth(ArrayList<YearlyRecord> yearlyRecords) {
        HashMap<Integer, Integer> getProfitForEachMonths = new HashMap<>();
        int incomeFirstMonths = 0;
        int incomeSecondMonths = 0;
        int incomeThirdMonths = 0;
        for (YearlyRecord yearlyRecord : yearlyRecords) {
            if (yearlyRecord.month == 1) {

                if (!yearlyRecord.is_expense) {
                    incomeFirstMonths += yearlyRecord.amount;
                } else {
                    incomeFirstMonths -= yearlyRecord.amount;
                }

            }
            if (yearlyRecord.month == 2) {
                if (!yearlyRecord.is_expense) {
                    incomeSecondMonths += yearlyRecord.amount;
                } else {
                    incomeSecondMonths -= yearlyRecord.amount;
                }
            }
            if (yearlyRecord.month == 3) {
                if (!yearlyRecord.is_expense) {
                    incomeThirdMonths += yearlyRecord.amount;
                } else {
                    incomeThirdMonths -= yearlyRecord.amount;
                }

            }
        }
        getProfitForEachMonths.put(0, incomeFirstMonths);
        getProfitForEachMonths.put(1, incomeSecondMonths);
        getProfitForEachMonths.put(2, incomeThirdMonths);
        return getProfitForEachMonths;
    }

    int averageExpenseAllMonths(ArrayList<YearlyRecord> yearlyRecords) {
        int averageExpense = 0;
        for (YearlyRecord yearlyRecord : yearlyRecords) {
            if (yearlyRecord.is_expense) {
                averageExpense += yearlyRecord.amount / yearlyRecords.size() / 2;
            }
        }
        return averageExpense;
    }

    int averageIncomeAllMonths(ArrayList<YearlyRecord> yearlyRecords) {
        int averageIncome = 0;
        for (YearlyRecord yearlyRecord : yearlyRecords) {
            if (!yearlyRecord.is_expense) {
                averageIncome += yearlyRecord.amount / yearlyRecords.size() / 2;
            }
        }
        return averageIncome;
    }
}