import java.util.Scanner;

// creating a class for the Admin Actions

class AdminActions
{

    //this function display the choices and execute based on the choices tha user give
    public void  adminAction(Scanner s,Admin admin) // get the parameters values when this function call
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true) // this while loop runs until the user give choice as exit
        {
            System.out.println();
            System.out.println("1. CHECK ATM BALANCE \n2. DEPOSIT CASH \n3. ADD ACCOUNT \n4. DELETE ACCOUNT \n5. VIEW ALL ACCOUNT \n6. VIEW ALL TRANSACTIONS \n7. EXIT");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt(); // get the choice from the user
            s.nextLine();
            System.out.println();

            // using if,else if and else condition Statements to execute based on the user choices
            if (choice == 1)
            {
                checkAtmBalance(); //method call (we directly call the methods because they are in the same class)
            }
            else if (choice == 2)
            {
                adminDeposit(s,admin);//method call with the arguments
            }
            else if (choice == 3)
            {
                addCustomerAccount(s);//method call with the arguments
            }
            else if (choice == 4)
            {
                deleteCustomerAccount(s);//method call with the arguments
            }
            else if (choice == 5)
            {
                viewAllAccounts();//method call
            }
            else if (choice == 6)
            {
                viewAllTransaction(admin,s);//method call with the arguments
            }
            else if (choice == 7)
            {
                break; // if the user choice is 7 then this break statement end the while loop
            }
            else // else this block will be executed
            {
                System.out.println("Enter The Valid Choice!");
                System.out.println();
            }
        }
    }


    // this method for Admin login
    public static Admin adminLogin(Scanner s)
    {
        int attempts = 0;
        System.out.println("WELCOME TO ADMIN LOGIN SYSTEM...");

        for (Admin tempAccount : Atm.getAdminArrayList()) // for loop for checking the username and password one by one in the Admin arraylist
        {
            while (attempts < 3) // this while maintain for managing the attempt of the login process
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String username = s.nextLine(); //get the username from the admin
                if (username.equals(tempAccount.getAdminName())) //checks that the admin given username is equal to the data in the arrayList
                {
                    System.out.print("Enter Password: ");
                    String password = s.nextLine(); //get the password from the admin
                    if (password.equals(tempAccount.getAdminPassword())) //checks that the user given password is equal to the data in the arrayList
                    {
                        return tempAccount; //return the current checking data in the for loop
                    }
                    else
                    {
                        attempts++; //attempt variable is incremented for each iteration
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3) // if attempt is 3 then it means the user reaches all the 3 attempts
                        {
                            Admin ob=new Admin(null,null); // create an empty Admin object
                            return ob;//return the created admin object
                        }
                    }
                }
                else
                {
                    return null; // in else part it return null (the default value for reference variables)
                }

            }

        }
        return null; //if the execution not enter into the for loop then it returns null
    }


    //method for check Atm balance
    public void checkAtmBalance()
    {
        double atmBalance = Atm.getBalance(); // call the method and store the value in the variable that the function returns
        System.out.println("The Current ATM Balance is ₹ "+String.format("%,.2f",atmBalance)); // using String format function to format the double variable
        System.out.println();
        for(Notes tempNotes : Atm.getNotesArrayList()) // this for loop to print the atm balance denomations
        {
            System.out.println("₹ "+tempNotes.getNote()+" × "+tempNotes.getCount()+" = ₹ "+tempNotes.getNote()*tempNotes.getCount());
        }
    }


    //method for adding coustomer data
    public void addCustomerAccount(Scanner s)
    {
        String accNo;
        String pin;
        double balance;
        String name;
        System.out.println("To Add Account Enter The Following Detials...");
        System.out.println();
        // get the information of the customer to add to Customer arraylist from the admin
        System.out.print("Enter Customer Account Number: ");
        accNo = s.nextLine();
        System.out.print("Enter Customer PIN Code: ");
        pin = s.nextLine();
        System.out.print("Enter Customer Balance: ");
        balance = s.nextDouble();
        s.nextLine();
        System.out.print("Enter Customer Name: ");
        name = s.nextLine();
        Atm.getCustomerArrayList().add(new Customer(accNo, pin, balance, name)); // add the data that get from the admin into the customer arraylist
        System.out.println();
        System.out.println("Account Added Successfully!");
    }


   // method to delete the account of the customer
    public void deleteCustomerAccount(Scanner s) {
        String accNo = null; // initialize the default value to the variable because it is a local variable
        System.out.println("To Delete Account Enter The Following Detials...");
        System.out.print("Enter Customer Account Number: ");
        accNo = s.nextLine();// get the account number of the customer to delete
        boolean accountFound = false;
        Customer accountToRemove =null;

        for (Customer tempAccount : Atm.getCustomerArrayList())// for loop give the values in the customer array list one by one
        {
            if (tempAccount.getAccountNumber().equals(accNo)) // checks that the given account number is equal to the data in the Customer array list
            {
                accountToRemove = tempAccount; //initialize the current value to the variable
                accountFound = true; //initialize the bolean variable as true if the contion is true to identify that the given account number is presnt or not
                break;//ends the for loop
            }
        }

        if(accountFound) //if the accountFound is true the above block will be executed
        {
            Atm.getCustomerArrayList().remove(accountToRemove); //remove the given value from the Customer arraylist
            System.out.println();
            System.out.println("Account Deleted Successfully!");
        }
        else //if the condition is not true then else block will be executed
        {
            System.out.println("The Account you Provide Is Not Exist...");
        }

    }


    // method for admin Deposit process
    public void adminDeposit(Scanner s, Admin admin)
    {
        System.out.print("Enter the Amount To Deposit: ");
        double amount = s.nextInt();
        s.nextLine(); // get the amount to deposite from the admin
        System.out.println();

        // and also get the denomination for each note they were going to deposit
        System.out.print("Enter the number of 2000 notes:");
        int twoThousand = s.nextInt();
        s.nextLine();

        System.out.print("Enter the number of 500 notes:");
        int fiveHundred = s.nextInt();
        s.nextLine();

        System.out.print("Enter the number of 200 notes:");
        int twoHundred = s.nextInt();
        s.nextLine();

        System.out.print("Enter the number of 100 notes:");
        int oneHundred = s.nextInt();
        s.nextLine();

        int calculate = (twoThousand * 2000) + (fiveHundred * 500) + (twoHundred * 200) + (oneHundred * 100);//calculate the total amount by the denomination they give

        if (calculate == amount) // checks is the given amount and denomination for amount is equal
        {
            int oldCount = 0;
            int newCount = 0;
            for(Notes tempNotes : Atm.getNotesArrayList()) // for loop to iterate the notes in the notes arraylist one by one
            {
                // using if,else if and else to execute based on the values comes from the for loop
                if (tempNotes.getNote() == 2000)
                {
                    oldCount = tempNotes.getCount(); //call the getCount method and store the value in the variable that the function returns
                    newCount = oldCount + twoThousand; //add count get from the admin and oldCount and store it in the newCount variable
                    tempNotes.setCount(newCount); // set the newCount value by calling the setCount method
                }
                else if (tempNotes.getNote() == 500)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + fiveHundred;
                    tempNotes.setCount(newCount);
                }
                else if (tempNotes.getNote() == 200)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + twoHundred;
                    tempNotes.setCount(newCount);
                }
                else if (tempNotes.getNote() == 100)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + oneHundred;
                    tempNotes.setCount(newCount);
                }
            }

            Atm.setBalance(Atm.getBalance()+amount); // finally update the money in the atm by calling setBalance method
            System.out.println();
            System.out.println("After Deposit ₹ " +String.format("%,.2f",amount) + ", ATM Balance Is: ₹ " + String.format("%,.2f", Atm.getBalance()));
            System.out.println("Deposit Successfully!");
            Admin.getTransactionArrayList().add(new Transaction(admin.getAdminName(), "Deposit", amount)); // add the deposited information to the transaction arraylist
            System.out.println();

        }
        else // if condition is false then else block will be executed
        {
            System.out.println();
            System.out.println("The Total Amount Does Not Match The Entered Denominations");
        }

    }


    //method to view all customer accounts
    public void viewAllAccounts()
    {
        System.out.println();
        System.out.println("The List Of All Accounts...");
        System.out.println();
        if(Atm.getCustomerArrayList().isEmpty()) // checks if the customer arraylist is empty
        {
            System.out.println("Empty!");
        }
        else // if customer arraylist is not empty then else will be executed
        {
            for (Customer tempAccount : Atm.getCustomerArrayList()) // for loop for print all the data in customer array list one by one
            {
                System.out.println("[ Name: " + tempAccount.getAccountName()+" , Account Number: " + tempAccount.getAccountNumber()+" , Balance: ₹" +String.format("%,.2f",tempAccount.getAccountBalance())+" ]");
                System.out.println();

            }
        }

    }


    //method to manage the transactions
    public  void viewAllTransaction(Admin admin,Scanner s)
    {
        while(true) //this while loop execute util the user give the choice as exit
        {
            System.out.println();
            System.out.println("TRANSACTIONS...");
            System.out.println();
            // print the transaction choices
            System.out.println("1. View Admin Transaction \n2. View All Customer Transaction \n3. View Specific Customer Transaction \n4. Exit  ");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt(); // get the choice from the admin
            s.nextLine();
            // using if,else if and else condition to execute based on the choice that admin give
            if (choice == 1) {
                viewAdminTransactions(admin);
            } else if (choice == 2) {
                viewAllCustomerTransactions();
            } else if (choice == 3) {
                viewCustomerTransactions(s);
            } else if (choice == 4) {
                break;//if the admin give 4 as a choice then it breaks the while loop
            }else {
                System.out.println("Enter the Valid Choice!");
            }
        }

    }


    // method to view Admin transactions
    public void viewAdminTransactions(Admin admin)
    {
        if(Admin.getTransactionArrayList().isEmpty()) // checks if the Transaction array list empty
        {
            System.out.println();
            System.out.println("The Transaction History Is Empty!");
        }
        else // if the Transaction arraylist is not empty then else block will be executed
        {
            System.out.println();
            for (Transaction transaction : Admin.getTransactionArrayList())// for loop iterates admin transaction array list one by one
            {
                if (admin.getAdminName().equals(transaction.accountNumber))//checks the given admin name equals to the data in the transaction array list
                {
                    System.out.println("Transaction [Name: " + transaction.getAccountNumber() + " , Type: " + transaction.getType() + " , Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }


    //method to view all customer transactions
    public void viewAllCustomerTransactions()
    {
        System.out.println();
        System.out.println("List Of All Customer Transactions...");
        System.out.println();
        if(Customer.getTransactionArrayList().isEmpty()) // checks if the Transaction array list empty
        {
            System.out.println("Empty!");
        }
        else // if the Transaction arraylist is not empty then else block will be executed
        {
            for (Transaction transaction : Customer.getTransactionArrayList()) // for loop for print all the data in customer transaction array list one by one
            {
                System.out.println("Transaction [Account: " + transaction.getAccountNumber() + " , Type: " + transaction.getType() + " , Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
            }
        }
    }


    //method to view specific customer transactions
    public void viewCustomerTransactions(Scanner s)
    {
        System.out.print("Enter The Customer Account Number: ");
        String tempAccNo = s.nextLine();
        System.out.println();
        if(Customer.getTransactionArrayList().isEmpty()) // checks if the Transaction array list empty
        {
            System.out.println("The Transaction History Is Empty!");
        }
        else // if the Transaction arraylist is not empty then else block will be executed
        {
            for (Transaction transaction : Customer.getTransactionArrayList()) // for loop iterates customer transaction array list one by one
            {
                if (tempAccNo.equals(transaction.getAccountNumber())) //checks the given customer account number equals to the data in the transaction array list
                {
                    System.out.println("Transaction [Account: " + transaction.getAccountNumber() + ", Type: " + transaction.getType() + ", Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }

}
