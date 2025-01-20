import java.util.ArrayList;

public class BookMyShow
{
    private static ArrayList<Customer> customersList = new ArrayList<>();
    private static ArrayList<Admin> adminsList = new ArrayList<>();
    private static ArrayList<Theatre> theatreList = new ArrayList<>();

    public static ArrayList<Admin> getAdminArrayList()
    {
        return adminsList;
    }

    public static ArrayList<Customer> getCustomerArrayList()
    {
        return customersList;
    }

    public static ArrayList<Theatre> getTheatreArrayList()
    {
        return theatreList;
    }

}
