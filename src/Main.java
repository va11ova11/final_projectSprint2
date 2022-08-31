import reports.MonthlyReport;
import reports.YearlyReport;
import util.Reconciliation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Reconciliation reconciliation = new Reconciliation();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int inputMenuInput = scanner.nextInt();
            switch (inputMenuInput) {
                case 1:
                    monthlyReport.createReport();
                    break;
                case 2:
                    yearlyReport.createYearlyReport();
                    break;
                case 3:
                    reconciliation.checkReports(monthlyReport, yearlyReport);
                    break;
                case 4:
                    monthlyReport.infoAboutAllMonths();
                    break;
                case 5:
                    yearlyReport.infoAboutYear();
                    break;
                case 0:
                    System.out.println("Программа завершена");
                    return;
                default:
                    System.out.println("Введена неверная команда");
            }
        }
    }

    static void printMenu() {
        System.out.println("1. - Считать все месячные отчёты");
        System.out.println("2. - Считать годовой отчёт");
        System.out.println("3. - Сверить отчёты");
        System.out.println("4. - Вывести информацию о всех месячных отчётах");
        System.out.println("5. - Вывести информацию о годовом отчёте");
        System.out.println("0. - Завершить программу");
    }
}
