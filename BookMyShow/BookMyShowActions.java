import java.util.Scanner;

class BookMyShowActions
{
    AdminActions a = new AdminActions();
    CustomerActions c = new CustomerActions();
    Scanner s = new Scanner(System.in);

    public void start()
    {
        System.out.println(".....BOOK MY SHOW.....");

        while (true)
        {
            System.out.println();
            System.out.println("1. ADMIN \n2. CUSTOMER \n3. EXIT");
            System.out.print("Enter Your Choice: ");
            int n = s.nextInt();
            s.nextLine();
            System.out.println();

            if (n == 1)
            {
                BookMyShow.getAdminArrayList().add(new Admin("Navya","1995"));
                Admin currentAdmin = a.adminLogin(s);

                if (currentAdmin != null )
                {
                    if (currentAdmin.getPassword() == null)
                    {
                        System.out.println("Access Denied. You Have Used All Login Attempts!");
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Login Successful!");
                       a.adminAction(s);
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
                Customer currentCustomer = c.loginProcess(s);
                if (currentCustomer != null )
                {
                    if (currentCustomer.getPassword() == null)
                    {
                        System.out.println("Access Denied. You Have Used All Login Attempts!");
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Login Successful!");
                       // c.customerAction(s, currentCustomer);
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
