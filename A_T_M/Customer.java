import java.util.ArrayList;

class Customer extends Account
{
    // create a variables to store customer balance and name (I don't initialize the variable because it is an instance variable)
    // I also put the variable in private for a security purpose (by put variables in private the other classes can't aware of this variable existence)
    private double balance;
    private String name;
    private static ArrayList<Transaction> transactionList = new ArrayList<>(); //  creating an arraylist to store customer transactions


    //  create a constructor to assign a value to the variable by passing a parameter
    Customer(String accNumber, String accPin, double accBalance, String accName)
    {
        // using super keyword to call the super class
        super(accNumber,accPin);
        // Initialize the instance variable to the value passed by the parameter
        balance = accBalance;
        name = accName;
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



