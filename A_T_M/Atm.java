import java.util.ArrayList;
import Notes.*;

// Atm pojo class to maintain the data of atm

class Atm
{
    // I also put the variable in private for a security purpose (by put variables in private the other classes can't aware of this variable existence)
    private static double balance;  // create balance variable
    private static ArrayList<Account> accountArrayList = new ArrayList<>();  // creating an array list to store and manage the admin data
    public static ArrayList<Notes> notesList = new ArrayList<>();  // creating an array list to store and manage the atm notes data

    public static ArrayList<Account> getAccountArrayList()
    {
        return accountArrayList;
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



