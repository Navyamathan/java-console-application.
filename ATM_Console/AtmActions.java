import java.util.Scanner;

class AtmActions
{
    AdminActions a = new AdminActions();
    CustomerActions c = new CustomerActions();
    Scanner s = new Scanner(System.in);

    public void start()throws  CloneNotSupportedException
    {
        Atm.getNotesArrayList().add(new TwoThousands(2000, 0));
        Atm.getNotesArrayList().add(new FiveHundreds(500, 0));
        Atm.getNotesArrayList().add(new TwoHundreds(200, 0));
        Atm.getNotesArrayList().add(new OneHundreds(100, 0));

        System.out.println();
        System.out.println("Welcome To The Secure ATM System!");

        while (true)
        {
            System.out.println(" ");
            System.out.println("1. ADMIN \n2. CUSTOMER \n3. EXIT");
            System.out.print("Enter Your Choice: ");
            int n = s.nextInt();
            s.nextLine();
            System.out.println();

            if (n == 1)
            {
                Atm.getAdminArrayList().add(new Admin("Navya","1995"));
                Admin currentAdmin = AdminActions.adminLogin(s);

                if (currentAdmin != null )
                {
                    if (currentAdmin.getAdminPassword() == null)
                    {
                        System.out.println("Access denied. You have used all login attempts.");
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Login successful!");
                        a.adminAction(s, currentAdmin);
                    }
                }
                else
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 2)
            {
                Customer currentCustomer = CustomerActions.customerLogin(s);
                if (currentCustomer != null )
                {
                    if (currentCustomer.getAccountPin() == null)
                    {
                        System.out.println("Access denied. You have used all login attempts.");
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Login successful!");
                        c.customerAction(s, currentCustomer);
                    }
                }
                else
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 3)
            {
                break;
            }

            else
            {
                System.out.println("Enter A Valid Input! ");
            }
        }
    }


}
