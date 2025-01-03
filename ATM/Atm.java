import java.util.ArrayList;

// Atm pojo class to maintain the data of atm

class Atm
{
    // I also put the variable in private for a security purpose (by put variables in private the other classes can't aware of this variable existance)
    private static double balance;  // create balance variable
    private static ArrayList<Customer> customersList = new ArrayList<>();  // creating an array list to store and manage the customer data
    private static ArrayList<Admin> adminsList = new ArrayList<>();  // creating an array list to store and manage the admin data
    private static ArrayList<Notes> notesList = new ArrayList<>();  // creating an array list to store and manage the atm notes data

    public static ArrayList<Admin> getAdminArrayList()
    {
        return adminsList;
    }

    public static ArrayList<Customer> getCustomerArrayList()
    {
        return customersList;
    }

    public static ArrayList<Notes> getNotesArrayList()
    {
        return notesList;
    }

    public static double getBalance()
    {
        return balance;
    }

    public static void setBalance(double atmBalance)
    {
        balance = atmBalance;
    }

    public static void setNotesList(ArrayList<Notes> notesList)
    {
        Atm.notesList = notesList;
    }

}



