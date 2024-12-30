import java.util.ArrayList;

public class Atm
{
    private static double balance = 0;
    private static ArrayList<Customer> customersList = new ArrayList<>();
    private static ArrayList<Admin> adminsList = new ArrayList<>();
    private static ArrayList<Notes> notesList = new ArrayList<>();

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



