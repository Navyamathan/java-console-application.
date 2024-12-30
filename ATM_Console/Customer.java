import java.util.ArrayList;

class Customer
{
    private String accNo;
    private String pin;
    private double balance;
    private String name;
    private static ArrayList<Transaction> transactionList = new ArrayList<>();

    Customer(String accNumber, String accPin, double accBalance, String accName)
    {
        accNo = accNumber;
        pin = accPin;
        balance = accBalance;
        name = accName;
    }

    public String getAccountNumber()
    {
        return accNo;
    }

    public String getAccountPin()
    {
        return pin;
    }
    public void setAccountPin(String pin)
    {
        this.pin = pin;
    }

    public double getAccountBalance()
    {
        return balance;
    }
    public void setAccountBalance(double balance)
    {
        this.balance = balance;
    }

    public String getAccountName()
    {
        return name;
    }

    public static ArrayList<Transaction> getTransactionArrayList()
    {
        return transactionList;
    }

}



