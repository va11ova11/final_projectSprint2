package model;

public class MonthlyRecord {
    private final String item_name;
    private final boolean is_expense;
    private final int quantity;
    private final int sum_of_one;

    public MonthlyRecord(String item_name, boolean is_expense, int quantity, int sum_of_one) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    public String getItemName() {
        return item_name;
    }

    public int getExpenses(){
        int expense = 0;
        if (is_expense){
            expense += quantity * sum_of_one;
        }
        return expense;
    }

    public int getIncome(){
        int income = 0;
        if (!is_expense) {
            income += quantity * sum_of_one;
        }
        return income;
    }
}