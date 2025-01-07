import java.util.ArrayList;
import java.util.Scanner;
import Notes.*;

// creating a class for the Customer Actions

class CustomerActions
{
    //this function display the choices and execute based on the choices that user give
    public void customerAction(Scanner s, Account customer)throws CloneNotSupportedException // this exception comes cause of we use Clone built-in function in the project
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true)  // this while loop runs until the user give choice as exit
        {
            System.out.println();
            System.out.println("1. CHECK BALANCE\n2. DEPOSIT CASH\n3. WITHDRAW CASH\n4. CHANGE PIN\n5. VIEW TRANSACTIONS\n6. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt(); // get the choice from the user
            s.nextLine();
            System.out.println();

            // using if,else if and else condition Statements to execute based on the user choices
            if (choice == 1)
            {
                checkBalance(customer); //method call (we directly call the methods because they are in the same class)
            }
            else if (choice == 2)
            {
                depositCash(s, customer); //method call with the arguments
            }
            else if (choice == 3)
            {
                withdrawCash(s, customer); //method call with the arguments
            }
            else if (choice == 4)
            {
                changePIN(s, customer); //method call with the arguments
            }
            else if (choice == 5)
            {
                transactions(customer); //method call with the arguments
            }
            else if (choice == 6)
            {
                break; // if the user choice is 6 then this break statement end the while loop
            }
            else // else this block will be executed
            {
                System.out.println("Enter The Valid Choice!");
                System.out.println();
            }
        }
    }

    // method for get the current customer who tries to log in
    public Customer currentCustomer(String accNo)
    {
        for(Account temp : Atm.getAccountArrayList()) // for loop for checking the given data is customer data one by one in the Account arraylist
        {
            if (accNo.equals(temp.getId())) // this if loop checks that the given Account number is in the Account arrayList
            {
                Customer currentUser = (Customer) temp; // stores the current customer data
                return currentUser; // return that current customer to do future operations
            }
        }
        return null;
    }



    // this method for Customer login
    public static Account customerLogin(Scanner s)
    {
        int attempts = 0;
        boolean flag = false;
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");


            while (attempts < 3) // this while maintain for managing the attempt of the login process
            {
                System.out.println();
                System.out.print("Enter Account Number: "); //get the account number from the customer
                String accNo = s.nextLine();
                for(Account temp : Atm.getAccountArrayList()) // for loop for checking the given data is customer data one by one in the Account arraylist
                {
                    if (accNo.equals(temp.getId())) // this if loop checks that the given account number is in the Account arrayList
                    {
                        flag = temp instanceof Customer; // flag store that the given data is Account subclass or in that class
                    }
                }
                if(flag) // if the flag is true if statement will be executed
                {
                    CustomerActions c= new CustomerActions();
                    Customer currentCustomer = c.currentCustomer(accNo);
                    if (accNo.equals(currentCustomer.getId())) //checks that the customer given account number is equal to the data in the customer arrayList
                    {
                        System.out.print("Enter PIN Code: ");
                        String pin = s.nextLine(); //get the pin from the customer
                        if (pin.equals(currentCustomer.getPassword()))
                        {
                            return currentCustomer; //return the current checking data in the for loop
                        }
                        else
                        {
                            attempts++; //attempt variable is incremented for each iteration
                            System.out.println();
                            System.out.println("Wrong Password!");
                            if (attempts == 3) // if attempt is 3 then it means the user reaches all the 3 attempts
                            {
                                Customer ob = new Customer(null, null, 0, null); // create an empty Customer object
                                return ob;//return the created customer object
                            }
                        }
                    }
                }

                else // in else part it return null (the default value for reference variables)
                {
                    return null;
                }

            }
        return null; //if the execution not enter into the for loop then it returns null
    }


    //method for check balance
    public void checkBalance(Account customer)
    {
        System.out.println("Your Current Account Balance Is: ₹ " +String.format("%,.2f",((Customer)customer).getAccountBalance())); // using String format function to format the double variable
        System.out.println();
    }


    // method for customer Deposit process
    public void depositCash(Scanner s, Account customer)
    {
        System.out.print("Enter the Amount To Deposit: ");
        double amount = s.nextDouble(); // get the amount to deposit from the customer
        s.nextLine();
        System.out.println();

        if(amount <= 0) // check the given amount not lesser than or equal to zero
        {
            System.out.println("Enter a Valid Amount");
        }

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
            int oldCount;
            int newCount;
            for(Notes tempNotes : Atm.getNotesArrayList()) // for loop to iterate the notes in the notes arraylist one by one
            {
                // using if,else if and else to execute based on the values comes from the for loop
                if(tempNotes.getNote() == 2000)
                {
                    oldCount = tempNotes.getCount(); //call the getCount method and store the value in the variable that the function returns
                    newCount = oldCount + twoThousand; //add count get from the admin and oldCount and store it in the newCount variable
                    tempNotes.setCount(newCount); // set the newCount value by calling the setCount method
                }
                else if(tempNotes.getNote() == 500)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + fiveHundred;
                    tempNotes.setCount(newCount);
                }
                else if(tempNotes.getNote() == 200)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + twoHundred;
                    tempNotes.setCount(newCount);
                }
                else if(tempNotes.getNote() == 100)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + oneHundred;
                    tempNotes.setCount(newCount);
                }
            }
            Customer customers = (Customer) customer; // here explicit typecast will be done to change the Account type to Customer type to access Customer data
            customers.setAccountBalance(customers.getAccountBalance()+amount); // update the money in the customer balance by calling setAccountBalance method
            Atm.setBalance(Atm.getBalance()+amount); // finally update the money in the atm by calling setBalance method
            System.out.println();
            System.out.println("After Deposit ₹ " + String.format("%,.2f",amount) + " , Your Account Balance Is: ₹ " + String.format("%,.2f", customers.getAccountBalance()));
            System.out.println("Deposit Successfully!");
            Customer.getTransactionArrayList().add(new Transaction(customer.getId(), "Deposit", amount)); // add the deposited information to the transaction arraylist
            System.out.println();
        }
        else // if condition is false then else block will be executed
        {
            System.out.println();
            System.out.println("The Total Amount Does Not Match The Entered Denominations");
        }

    }


    // method to withdraw a money
    public void withdrawCash(Scanner s,Account customer)throws CloneNotSupportedException // this exception comes cause of we use Clone built-in function in the project
    {
        ArrayList<String> denomination = new ArrayList<>(); // creating an arraylist to store the denomination that calculates
        ArrayList<Notes> copy = new ArrayList<>(); // creating a new arraylist to maintain the clone data of the notes
        double remainder = 0.0;

        for (Notes tempNotes : Atm.getNotesArrayList()) // for loop give the values in the notes array list one by one
        {
            copy.add(tempNotes.clone()); // add the notes data in the copy array list to implement a clone
        }

        System.out.println("Remainder: Enter the Amount Should Be Divide by these Denominations ₹2000, ₹500, ₹200, ₹100");
        System.out.print("Enter the Amount To Withdraw: ");
        double withdrawAmount = s.nextLong(); // get the amount to withdraw from the customer
        double amount = withdrawAmount; // withdrawalAmount will change in the further operation so here assign that variable to the another variable
        s.nextLine();
        System.out.println();

        if(amount > ((Customer)customer).getAccountBalance()) // checks if the withdrawal amount is lesser than the customer account balance
        {
            System.out.println("The Amount You Want To Withdraw Is Out Of Your Account Balance");
        }
        else if(Atm.getBalance() < amount) // checks if the withdrawal amount is lesser than the atm balance
        {
            System.out.println("The Amount You Want To Withdraw Is Out Of The ATM Balance");
        }
        else if(amount<= 0) // checks if the withdrawal amount is lesser than or equal to 0
        {
            System.out.println("Enter The Valid Amount To Withdraw");
        }
        else // if condition is false then else block will be executed
        {
            for (Notes tempNotes : copy) // for loop give the values in the copy array list one by one
            {
                // using if,else if and else to execute based on the values comes from the for loop
                if (tempNotes.getNote() == 2000)
                {
                    remainder = withdrawCalculation(amount, tempNotes, denomination); //call the function withdrawCalculation with the arguments and to the function return value to the variable
                    if (remainder <= amount) // checks that the remainder is lesser than or equal to the actual amount
                    {
                        amount = remainder; // initialize the remainder to the amount for the future calculation(as a name said remainder)
                    }
                }
                else if (tempNotes.getNote() == 500)
                {
                    remainder = withdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
                else if (tempNotes.getNote() == 200)
                {
                    remainder = withdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
                else if (tempNotes.getNote() == 100)
                {
                    remainder = withdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
            }

            if(remainder == 0) // finally check if the remainder is 0
            {
                for(String tempDenomination : denomination) // for loop to print the denomination one by one in the array list
                {
                    System.out.println(tempDenomination);
                }
                System.out.println();
                System.out.println("Withdraw Successfully!");
                Customer customers = (Customer) customer;  // here explicit typecast will be done to change the Account type to Customer type to access Customer data
                customers.setAccountBalance(customers.getAccountBalance() - withdrawAmount); // minus the withdrawal amount in the customer account balance and set that amount by calling setAccountBalance function
                Atm.setBalance(Atm.getBalance() - withdrawAmount);// minus the withdrawal amount in the ATM balance and set that amount by calling setBalance function
                Customer.getTransactionArrayList().add(new Transaction(customer.getId(), "Withdraw", withdrawAmount)); // add the withdrawal information to the transaction arraylist
                Atm.setNotesList(copy); //update the changes in copy Notes arraylist(clone) to the original Notes arraylist
            }
            else //if the condition is not true then else block will be executed
            {
                System.out.println();
                System.out.println("The Amount You Want To Withdraw Cannot Be Dispensed By The ATM Due To a Shortage Of This Specific Denomination. ");
            }

        }
    }


    //method to calculate the withdrawal
    public double withdrawCalculation(double amount,Notes notes,ArrayList<String> denomination)
    {
        int atmNote = notes.getNote();
        int count = (int) amount/atmNote;
        if(count<=notes.getCount() && atmNote<=amount) // checks the given counts is lesser than or equal to the count in the atm and the withdrawal amount should be greater than or equal to notes
        {
            int tempAmount = atmNote * count;
            amount -= tempAmount;
            notes.setCount(notes.getCount()-count); // minus the withdrawal note count in the actual count and set that count by calling setCount function
            denomination.add("₹ "+atmNote+" × "+count+" = ₹ "+tempAmount); // add data in the denomination array list
            return amount; // return the amount after filtration
        }
        return amount; // if execution not enters the if statement then return the same amount
    }


    // method to change pin
    public void changePIN(Scanner s,Account customer)
    {
        System.out.println("To Change The Password You Have To Complete The Following Steps...");
        System.out.println();
        System.out.print("Enter the new Password: ");
        String newPassword = s.nextLine(); // get the new password from the customer
        System.out.print("Enter the Confirm Password: ");
        String confirmPassword = s.nextLine(); // get the confirmPassword from the customer
        if (newPassword.equals(confirmPassword)) //checks the new password equals to confirmPassword
        {
            customer.setPassword(newPassword); //set the new password as a pin by call the method setAccountPin
            System.out.println();
            System.out.println("The New Password Has Been Changed!");
        }
        else //if the condition is not true then else block will be executed
        {
            System.out.println();
            System.out.println("The New Password Do Not Matched!");
        }
        System.out.println();
    }


    //method to view customer transactions
    public void transactions(Account customer)
    {
        if(Customer.getTransactionArrayList().isEmpty()) // checks if the Transaction array list empty
        {
            System.out.println("The Transaction History Is Empty!");
        }
        else // if the Transaction arraylist is not empty then else block will be executed
        {
            for (Transaction transaction : Customer.getTransactionArrayList()) // for loop iterates customer transaction array list one by one
            {
                if (customer.getId().equals(transaction.getAccountNumber())) //checks the given customer account number equals to the data in the transaction array list
                {
                    System.out.println("Transaction [Account: " + transaction.getAccountNumber() + ", Type: " + transaction.getType() + ", Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }


}
