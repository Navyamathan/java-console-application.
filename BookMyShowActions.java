import java.util.Scanner;

// class for manage the BookMyShow Actions
class BookMyShowActions
{
    static Scanner s = new Scanner(System.in); // create an object for scanner java built-in function to get the input from the user

    // method for to perform BookMyShow Actions
    public static void start()
    {
        System.out.println();
        System.out.println(".....BOOK MY SHOW.....");

        while (true)
        {
            System.out.println();
            System.out.println("1. ADMIN \n2. CUSTOMER \n3. EXIT"); // print the choice
            System.out.print("Enter Your Choice: ");
            int n = Integer.parseInt(s.nextLine()); // get choice from the user
            System.out.println();

            if (n == 1) // if the user choice is 1
            {
                BookMyShow.getAdminArrayList().add( new Admin("ADMIN","123")); // add a default data to the Admin ArrayList
                Admin currentAdmin = AdminActions.adminLogin(); // call a adminLogin function and store value in the variable that function returns

                if (currentAdmin != null ) // if currentAdmin is null
                {
                    System.out.println();
                    System.out.println("Login Successful!");
                    AdminActions.adminAction(); // after login call the adminActions to step into future process
                }
                else // if the condition is false then else block will be executed
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 2) // if the user choice is 2
            {
                Customer currentCustomer = CustomerActions.loginProcess(); // call a customerLogin function and store value in the variable that function returns

                if (currentCustomer != null ) // if currentCustomer is null
                {
                    System.out.println();
                    System.out.println("Login Successful!");
                    CustomerActions.availableMovies(currentCustomer); // call the availableMovies function to print available movies after login
                    CustomerActions.customerAction(currentCustomer); // after login call the customerActions to step into future process
                }
                else // if the condition is false then else block will be executed
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 3) // if the user choice is 3
            {
                break; // breaks the while loop
            }

            else // if the condition is false then else block will be executed
            {
                System.out.println("Enter A Valid Input! ");
            }
        }
    }
}
