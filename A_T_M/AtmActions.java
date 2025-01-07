import java.util.Scanner;
import Notes.*; // import Notes package to access the classes in it

// creating a class for to perform Atm Actions

class AtmActions
{
    AdminActions a = new AdminActions(); // creating an object for Admin actions then only we can use the AdminActions class
    CustomerActions c = new CustomerActions(); // creating an object for Customer actions then only we can use the CustomerActions class
    Scanner s = new Scanner(System.in); //create an object for scanner java built-in function to get the input from the user

    // function that maintain the atm actions
    public void start()throws  CloneNotSupportedException // this exception comes cause of we use Clone built-in function in the project
    {
        // Adding the atm notes data into the Notes array list
        Atm.getNotesArrayList().add(new TwoThousands(2000, 0));
        Atm.getNotesArrayList().add(new FiveHundreds(500, 0));
        Atm.getNotesArrayList().add(new TwoHundreds(200, 0));
        Atm.getNotesArrayList().add(new OneHundreds(100, 0));

        System.out.println();
        System.out.println("Welcome To The Secure ATM System!");

        while (true) // while loop for run iteratively to show the choice again and again until the user give exit option
        {
            System.out.println();
            System.out.println("1. ADMIN \n2. CUSTOMER \n3. EXIT"); // print the choice
            System.out.print("Enter Your Choice: ");
            int n = s.nextInt(); // get choice from the user
            s.nextLine(); // it gets the escape character /n that the nextInt miss(because nextInt get only integer values but /n is a character that's why)
            System.out.println();

            // using if,else if,else condition statement to check the choice that give based on the choice user give program will execute
            if (n == 1)
            {
                Atm.getAccountArrayList().add(new Admin("Navya","1995")); // add the Admin data to the Admin arraylist

                Account currentAdmin = AdminActions.adminLogin(s); // call the adminLogin function By the Class name because this function declared as a static and store the value to the variable that the function returns

                if (currentAdmin != null ) //check the variable is not null
                {
                    if (currentAdmin.getPassword() == null) // check the password is null in the variable of the type Admin object
                    {
                        System.out.println("Access denied. You have used all login attempts.");
                    }
                    else // if the condition in "if" statement is false then "else" block will be executed
                    {
                        System.out.println();
                        System.out.println("Login successful!");
                        a.adminAction(s, currentAdmin); // call the adminAction function by using the AdminActions object a
                    }
                }
                else // if the condition in "if" statement is false then "else" block will be executed
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 2)
            {

                Account currentCustomer = CustomerActions.customerLogin(s);// call the customerLogin function By the Class name because this function declared as a static and store the value to the variable that the function returns

                if (currentCustomer != null )//check the variable is not null
                {
                    if (currentCustomer.getPassword() == null) // check the password is null in the variable of the type Admin object
                    {
                        System.out.println("Access denied. You have used all login attempts.");
                    }
                    else // if the condition in "if" statement is false then "else" block will be executed
                    {
                        System.out.println();
                        System.out.println("Login successful!");
                        c.customerAction(s, currentCustomer); // call the customerAction function by using the CustomerActions object c
                    }
                }
                else // if the condition in "if" statement is false then "else" block will be executed
                {
                    System.out.println();
                    System.out.println("The Account Doesn't Exist!");
                }
            }

            else if (n == 3)
            {
                break; // if the user choice is 3 this while loop will be ended
            }

            else
            {
                System.out.println("Enter A Valid Input! "); // if they enter any other value except the mentioned values then it will be executed
            }
        }
    }


}
