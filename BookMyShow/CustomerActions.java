import java.util.Scanner;

class CustomerActions
{
    public Customer loginProcess(Scanner s)
    {
        Customer status;
        System.out.println("1. SIGN UP\n2. LOGIN\n3. EXIT");
        System.out.print("Enter Your Choice: ");
        int choice = s.nextInt();
        s.nextLine();
        if(choice==1)
        {
            status = signUp(s);
            return status;
        }
        else if(choice==2)
        {
            status = login(s);
            return status;
        }
        else if(choice==3)
        {
            return null;
        }
        else
        {
            System.out.println("Enter The Valid Choice!");
        }
        return null;
    }

    public Customer login(Scanner s)
    {
        int attempts = 0;
        System.out.println();
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");

        for (Customer tempAccount : BookMyShow.getCustomerArrayList())
        {
            while (attempts < 3)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String accNo = s.nextLine();
                if (accNo.equals(tempAccount.getUserName()))
                {
                    System.out.print("Enter Password: ");
                    String pin = s.nextLine();
                    if (pin.equals(tempAccount.getPassword()))
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
                            Customer ob = new Customer(null, null,null);
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

    public Customer signUp(Scanner s)
    {
        String userName;
        String password;
        String location;
        System.out.println();
        System.out.println("To Sign Up Enter The Following Details...");
        System.out.println();
        System.out.print("Enter User Name: ");
        userName = s.nextLine();
        System.out.print("Enter Password: ");
        password = s.nextLine();
        System.out.print("Enter Location: ");
        location = s.nextLine();
        Customer temp = new Customer(userName,password,location);
        BookMyShow.getCustomerArrayList().add(temp);
        return temp;
    }

}
