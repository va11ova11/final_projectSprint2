import java.util.ArrayList;

public class Reconciliation {
    YearlyReport yearlyReport = new YearlyReport();
    MonthlyReport monthlyReport = new MonthlyReport();

    void checkReports(ArrayList<MonthlyRecord> monthlyRecords, ArrayList<YearlyRecord> yearlyRecords) {
        if (!((monthlyRecords.isEmpty() || (yearlyRecords.isEmpty())))) {
            for (int i = 1; i < 4; i++) {
                boolean checkExpense = (monthlyReport.checkExpense(i, monthlyRecords) == yearlyReport.checkExpense(i, yearlyRecords));
                boolean checkIncome = (monthlyReport.checkIncome(i, monthlyRecords) == yearlyReport.checkIncome(i, yearlyRecords));
                if (checkExpense) {
                    System.out.println("Расходы в месяце " + i + " сошлись с расходами в годовом отчете");
                } else {
                    System.out.println("В " + i + " месяце не сходятся расходы");
                }
                if (checkIncome) {
                    System.out.println("Доходы в месяце " + i + " сошлись с доходами в годовом отчёте");
                } else {
                    System.out.println("В " + i + " месяце не сходятся доходы");
                }
            }
        } else {
            System.out.println("Сначала нужно получить отчёты");
        }
    }
}
