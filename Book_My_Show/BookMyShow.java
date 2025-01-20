import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow
{
    private static HashMap<String, Customer> customersHashMap = new HashMap<>();
    private static HashMap<String, Admin> adminsHashMap = new HashMap<>();
    private static HashMap<String, Theatre> theatresHashMap = new HashMap<>();
    private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    private static DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static HashMap<String, Admin> getAdminHashMap()
    {
        return adminsHashMap;
    }

    public static HashMap<String, Customer> getCustomerHashMap()
    {
        return customersHashMap;
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
