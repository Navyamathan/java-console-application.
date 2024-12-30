import java.util.ArrayList;
import java.util.Scanner;

class CustomerActions {

    public void customerAction(Scanner s, Customer customer)throws CloneNotSupportedException {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true) {
            System.out.println();
            System.out.println("1. CHECK BALANCE\n2. DEPOSIT CASH\n3. WITHDRAW CASH\n4. CHANGE PIN\n5. VIEW TRANSACTIONS\n6. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt();
            s.nextLine();
            System.out.println();
            if (choice == 1) {
                checkBalance(customer);
            } else if (choice == 2) {
                depositCash(s, customer);
            } else if (choice == 3) {
                widthdrawlCash(s, customer);
            } else if (choice == 4) {
                changePIN(s, customer);
            } else if (choice == 5) {
                transactions(customer);
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Enter The Valid Choice!");
                System.out.println();
            }
        }
    }


    public static Customer customerLogin(Scanner s) {
        int attempts = 0;
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");

        for (Customer tempAccount : Atm.getCustomerArrayList())
        {
            while (attempts < 3)
            {
                System.out.println();
                System.out.print("Enter Account Number: ");
                String accNo = s.nextLine();
                if (accNo.equals(tempAccount.getAccountNumber())) {
                    System.out.print("Enter PIN Code: ");
                    String pin = s.nextLine();
                    if (pin.equals(tempAccount.getAccountPin())) {
                        return tempAccount;
                    } else {
                        attempts++;
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3) {
                            Customer ob = new Customer(null, null, 0, null);
                            return ob;
                        }
                    }
                }
                else
                {
                    return null;
                }

            }
        }
        return null;
    }


    public void checkBalance(Customer customer) {

        System.out.println("Your Current Account Balance Is: ₹ " +String.format("%,.2f",customer.getAccountBalance()));
        System.out.println();
    }


    public void depositCash(Scanner s, Customer customer) {

        System.out.print("Enter the Amount To Deposit: ");
        double amount = s.nextDouble();
        s.nextLine();
        System.out.println();

        if(amount <= 0)
        {
            System.out.println("Enter a Valid Amount");
        }

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

        int calculate = (twoThousand * 2000) + (fiveHundred * 500) + (twoHundred * 200) + (oneHundred * 100);

        if (calculate == amount)
        {
            int oldCount = 0;
            int newCount = 0;
            for(Notes tempNotes : Atm.getNotesArrayList())
            {
                if(tempNotes.getNote() == 2000)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + twoThousand;
                    tempNotes.setCount(newCount);
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
            customer.setAccountBalance(customer.getAccountBalance()+amount);
            Atm.setBalance(Atm.getBalance()+amount);
            System.out.println();
            System.out.println("After Deposit ₹ " + String.format("%,.2f",amount) + " , Your Account Balance Is: ₹ " + String.format("%,.2f", customer.getAccountBalance()));
            System.out.println("Deposit Successfully!");
            Customer.getTransactionArrayList().add(new Transaction(customer.getAccountNumber(), "Deposit", amount));
            System.out.println();
        }
        else
        {
            System.out.println();
            System.out.println("The Total Amount Does Not Match The Entered Denominations");
        }

    }



    public void widthdrawlCash(Scanner s,Customer customer)throws CloneNotSupportedException
    {
        ArrayList<String> denomination = new ArrayList<>();
        ArrayList<Notes> copy = new ArrayList<>();
        double remainder = 0.0;

        for (Notes tempNotes : Atm.getNotesArrayList())
        {
            copy.add(tempNotes.clone());
        }

        System.out.println("Remainder: Enter the Amount Should Be Divide by these Denominations ₹2000, ₹500, ₹200, ₹100");
        System.out.print("Enter the Amount To Withdraw: ");
        double withdrawAmount = s.nextLong();
        double amount = withdrawAmount;
        s.nextLine();
        System.out.println();

        if(amount > customer.getAccountBalance())
        {
            System.out.println("The Amount You Want To Withdraw Is Out Of Your Account Balance");
        }
        else if(Atm.getBalance() < amount)
        {
            System.out.println("The Amount You Want To Withdraw Is Out Of The ATM Balance");
        }
        else if(amount<= 0)
        {
            System.out.println("Enter The Valid Amount To Withdraw");
        }
        else
        {
            for (Notes tempNotes : copy)
            {
                if (tempNotes.getNote() == 2000)
                {
                    remainder = widthdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
                else if (tempNotes.getNote() == 500)
                {
                    remainder = widthdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
                else if (tempNotes.getNote() == 200)
                {
                    remainder = widthdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
                else if (tempNotes.getNote() == 100)
                {
                    remainder = widthdrawCalculation(amount, tempNotes, denomination);
                    if (remainder <= amount)
                    {
                        amount = remainder;
                    }
                }
            }
            if(remainder == 0)
            {
                for(String tempDenomination : denomination)
                {
                    System.out.println(tempDenomination);
                }
                System.out.println();
                System.out.println("Withdraw Successfully!");
                customer.setAccountBalance(customer.getAccountBalance() - withdrawAmount);
                Atm.setBalance(Atm.getBalance() - withdrawAmount);
                Customer.getTransactionArrayList().add(new Transaction(customer.getAccountNumber(), "Withdraw", withdrawAmount));
                Atm.setNotesList(copy);
            }
            else
            {
                System.out.println();
                System.out.println("The Amount You Want To Withdraw Cannot Be Dispensed By The ATM Due To a Shortage Of This Specific Denomination. ");
            }

        }
    }



    public double widthdrawCalculation(double amount,Notes notes,ArrayList<String> denomination)
    {
        int atmNote = notes.getNote();
        int count = (int) amount/atmNote;
        if(count<=notes.getCount() && atmNote<=amount)
        {
            int tempAmount = atmNote * count;
            amount -= tempAmount;
            notes.setCount(notes.getCount()-count);
            denomination.add("₹ "+atmNote+" × "+count+" = ₹ "+tempAmount);
            return amount;
        }
        return amount;
    }



    public void changePIN(Scanner s,Customer customer)
    {
        System.out.println("To Change The Password You Have To Complete The Following Steps...");
        System.out.println();
        System.out.print("Enter the new Password: ");
        String newpassword = s.nextLine();
        System.out.print("Enter the Confirm Password: ");
        String confirmpassword = s.nextLine();
        if (newpassword.equals(confirmpassword))
        {
            customer.setAccountPin(newpassword);
            System.out.println();
            System.out.println("The New Password Has Been Changed!");
        }
        else
        {
            System.out.println();
            System.out.println("The New Password Do Not Matched!");
        }
        System.out.println();
    }



    public void transactions(Customer customer)
    {
        if(Customer.getTransactionArrayList().isEmpty())
        {
            System.out.println("The Transaction History Is Empty!");
        }
        else
        {
            for (Transaction transaction : Customer.getTransactionArrayList())
            {
                if (customer.getAccountNumber().equals(transaction.getAccountNumber()))
                {
                    System.out.println("Transaction [Account: " + transaction.getAccountNumber() + ", Type: " + transaction.getType() + ", Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }


}
