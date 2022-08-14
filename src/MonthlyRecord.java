public class MonthlyRecord {
    String item_name;
    boolean is_expense;
    int quantity;
    int sum_of_one;
    int month_number;

    public MonthlyRecord(String item_name, boolean is_expense, int quantity, int sum_of_one, int month_number) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
        this.month_number = month_number;
    }

    int getExpenses(){
        int expense = 0;
        if (is_expense){
            expense += quantity * sum_of_one;
        }
        return expense;
    }

    int getIncome(){
        int income = 0;
        if (!is_expense) {
            income += quantity * sum_of_one;
        }
        return income;
    }
}
