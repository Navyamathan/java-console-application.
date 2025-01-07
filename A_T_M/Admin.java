import java.util.ArrayList;

// creating a pojo Admin class to maintain and manage the admin data

public class Admin extends Account
    {
        private static ArrayList<Transaction> transactionList = new ArrayList<>(); //  creating an arraylist to store admin transactions

        public Admin(String adminName, String adminPassword) //  create a constructor to assign a value to the variable by passing a parameter
        {
            // using super keyword to call the super class
            super(adminName,adminPassword);
        }

        public static ArrayList<Transaction> getTransactionArrayList()
        {
            return transactionList;
        }
    }




