// creating a pojo transaction class to maintain and manage the transaction data

class Transaction
{
    String accountNumber;
    String type;
    double amount;

    public Transaction(String accountNumber, String type, double amount) //  create a constructor to assign a value to the variable by passing a parameter
    {
        // using this keyword to move the courser to the variable in the same reference class(because it is a non static)
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getType()
    {
        return type;
    }

    public double getAmount()
    {
        return amount;
    }

}



