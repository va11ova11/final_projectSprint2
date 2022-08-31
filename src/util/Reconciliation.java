package util;

import reports.MonthlyReport;
import reports.YearlyReport;

public class Reconciliation {

    public void checkReports(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        if (!((monthlyReport.months.isEmpty() || (yearlyReport.years.isEmpty())))) {

            for (int i = 1; i < 4; i++) {

                boolean checkExpense = monthlyReport.getExpensesForMonth(i, monthlyReport.months) ==
                        yearlyReport.getExpensesForMonthInYear(i);
                boolean checkIncome = monthlyReport.getProfitForMonth(i, monthlyReport.months) ==
                        yearlyReport.getIncomeMonthInYear(i);

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

