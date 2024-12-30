import java.util.Scanner;


class AdminActions
{

    public void  adminAction(Scanner s,Admin admin)
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true)
        {
            System.out.println();
            System.out.println("1. CHECK ATM BALANCE \n2. DEPOSIT CASH \n3. ADD ACCOUNT \n4. DELETE ACCOUNT \n5. VIEW ALL ACCOUNT \n6. VIEW ALL TRANSACTIONS \n7. EXIT");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt();
            s.nextLine();
            System.out.println();
            if (choice == 1)
            {
                checkAtmBalance();
            }
            else if (choice == 2)
            {
                adminDeposite(s,admin);
            }
            else if (choice == 3)
            {
                addCustomerAccount(s);
            }
            else if (choice == 4)
            {
                deleteCustomerAccount(s);
            }
            else if (choice == 5)
            {
                viewAllAccounts();
            }
            else if (choice == 6)
            {
                viewAllTransaction(admin,s);
            }
            else if (choice == 7)
            {
                break;
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
                System.out.println();
            }
        }
    }



    public static Admin adminLogin(Scanner s)
    {
        int attempts = 0;
        System.out.println("WELCOME TO ADMIN LOGIN SYSTEM...");

        for (Admin tempAccount : Atm.getAdminArrayList())
        {
            while (attempts < 3)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String username = s.nextLine();
                if (username.equals(tempAccount.getAdminName()))
                {
                    System.out.print("Enter Password: ");
                    String password = s.nextLine();
                    if (password.equals(tempAccount.getAdminPassword()))
                    {
                        return tempAccount;
                    }
                    else
                    {
                        attempts++;
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3)
                        {
                            Admin ob=new Admin(null,null);
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


    public void checkAtmBalance()
    {
        double atmBalance = Atm.getBalance();
        System.out.println("The Current ATM Balance is ₹ "+String.format("%,.2f",atmBalance));
        System.out.println();
        for(Notes tempNotes : Atm.getNotesArrayList())
        {
            System.out.println("₹ "+tempNotes.getNote()+" × "+tempNotes.getCount()+" = ₹ "+tempNotes.getNote()*tempNotes.getCount());
        }
    }


    public void addCustomerAccount(Scanner s)
    {
        String accNo;
        String pin;
        double balance;
        String name;
        System.out.println("To Add Account Enter The Following Detials...");
        System.out.println();
        System.out.print("Enter Customer Account Number: ");
        accNo = s.nextLine();
        System.out.print("Enter Customer PIN Code: ");
        pin = s.nextLine();
        System.out.print("Enter Customer Balance: ");
        balance = s.nextDouble();
        s.nextLine();
        System.out.print("Enter Customer Name: ");
        name = s.nextLine();
        Atm.getCustomerArrayList().add(new Customer(accNo, pin, balance, name));
        System.out.println();
        System.out.println("Account Added Successfully!");
    }


    public void deleteCustomerAccount(Scanner s) {
        String accNo;
        System.out.println("To Delete Account Enter The Following Detials...");
        System.out.print("Enter Customer Account Number: ");
        accNo = s.nextLine();
        boolean accountFound = false;
        Customer accountToRemove =null;

        for (Customer tempAccount : Atm.getCustomerArrayList())
        {
            if (tempAccount.getAccountNumber().equals(accNo))
            {
                accountToRemove = tempAccount;
                accountFound = true;
                break;
            }
        }

        if(accountFound)
        {
            Atm.getCustomerArrayList().remove(accountToRemove);
            System.out.println();
            System.out.println("Account Deleted Successfully!");
        }
        else
        {
            System.out.println("The Account you Provide Is Not Exist...");
        }

    }


    public void adminDeposite(Scanner s, Admin admin)
    {
        System.out.print("Enter the Amount To Deposit: ");
        double amount = s.nextInt();
        s.nextLine();
        System.out.println();

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
                if (tempNotes.getNote() == 2000)
                {
                    oldCount = tempNotes.getCount();
                    newCount = oldCount + twoThousand;
                    tempNotes.setCount(newCount);
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

            Atm.setBalance(Atm.getBalance()+amount);
            System.out.println();
            System.out.println("After Deposit ₹ " +String.format("%,.2f",amount) + ", ATM Balance Is: ₹ " + String.format("%,.2f", Atm.getBalance()));
            System.out.println("Deposit Successfully!");
            Admin.getTransactionArrayList().add(new Transaction(admin.getAdminName(), "Deposit", amount));
            System.out.println();

        }
        else
        {
            System.out.println();
            System.out.println("The Total Amount Does Not Match The Entered Denominations");
        }

    }


    public void viewAllAccounts()
    {
        System.out.println();
        System.out.println("The List Of All Accounts...");
        System.out.println();
        if(Atm.getCustomerArrayList().isEmpty())
        {
            System.out.println("Empty!");
        }
        else
        {
            for (Customer tempAccount : Atm.getCustomerArrayList())
            {
                System.out.println("[ Name: " + tempAccount.getAccountName()+" , Account Number: " + tempAccount.getAccountNumber()+" , Balance: ₹" +String.format("%,.2f",tempAccount.getAccountBalance())+" ]");
                System.out.println();

            }
        }

    }


    public  void viewAllTransaction(Admin admin,Scanner s)
    {
        while(true) {
            System.out.println();
            System.out.println("TRANSACTIONS...");
            System.out.println();
            System.out.println("1. View Admin Transaction \n2. View All Customer Transaction \n3. View Specific Customer Transaction \n4. Exit  ");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt();
            s.nextLine();

            if (choice == 1) {
                viewAdminTransactions(admin);
            } else if (choice == 2) {
                viewAllCustomerTransactions();
            } else if (choice == 3) {
                viewCustomerTransactions(s);
            } else if (choice == 4) {
                break;
            }else {
                System.out.println("Enter the Valid Choice!");
            }
        }

    }


    public void viewAllCustomerTransactions()
    {
        System.out.println();
        System.out.println("List Of All Customer Transactions...");
        System.out.println();
        if(Customer.getTransactionArrayList().isEmpty())
        {
            System.out.println("Empty!");
        }
        else
        {
            for (Transaction transaction : Customer.getTransactionArrayList())
            {
                System.out.println("Transaction [Account: " + transaction.getAccountNumber() + " , Type: " + transaction.getType() + " , Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
            }
        }
    }


    public void viewAdminTransactions(Admin admin)
    {
        if(Admin.getTransactionArrayList().isEmpty())
        {
            System.out.println();
            System.out.println("The Transaction History Is Empty!");
        }
        else
        {
            System.out.println();
            for (Transaction transaction : Admin.getTransactionArrayList())
            {
                if (admin.getAdminName().equals(transaction.accountNumber))
                {
                    System.out.println("Transaction [Name: " + transaction.getAccountNumber() + " , Type: " + transaction.getType() + " , Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }


    public void viewCustomerTransactions(Scanner s)
    {
        System.out.print("Enter The Customer Account Number: ");
        String tempAccNo = s.nextLine();
        System.out.println();
        if(Customer.getTransactionArrayList().isEmpty())
        {
            System.out.println("The Transaction History Is Empty!");
        }
        else
        {
            for (Transaction transaction : Customer.getTransactionArrayList())
            {
                if (tempAccNo.equals(transaction.getAccountNumber()))
                {
                    System.out.println("Transaction [Account: " + transaction.getAccountNumber() + ", Type: " + transaction.getType() + ", Amount: ₹" + String.format("%.2f", transaction.getAmount()) + "]");
                }
            }
        }
        System.out.println();
    }

}
