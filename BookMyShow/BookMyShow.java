import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow
{
    private static ArrayList<Customer> customersArrayList = new ArrayList<>();
    private static ArrayList<Admin> adminsArrayList = new ArrayList<>();
    private static HashMap<String, Theatre> theatresHashMap = new HashMap<>();
    private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    private static DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static ArrayList<Admin> getAdminArrayList()
    {
        return adminsArrayList;
    }

    public static ArrayList<Customer> getCustomerArrayList()
    {
        return customersArrayList;
    }

    public static HashMap<String, Theatre> getTheatreHashMap()
    {
        return theatresHashMap;
    }

    public static DateTimeFormatter getLocalDateFormatter()
    {
        return localDateFormatter;
    }

    public static DateTimeFormatter getLocalTimeFormatter()
    {
        return localTimeFormatter;
    }

}
