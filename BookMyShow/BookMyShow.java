import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

// pojo BookMyShow class to maintain and manage the bookMyShow data
public class BookMyShow
{
    private static ArrayList<Customer> customersArrayList = new ArrayList<>(); // arraylist to store the customers data
    private static ArrayList<Admin> adminsArrayList = new ArrayList<>(); // arraylist to store the admins data
    private static HashMap<String, Theatre> theatresHashMap = new HashMap<>(); // hashmap to store the theatres data
    private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy"); // DateTimeFormater to store the date of the show
    private static DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // DateTimeFormater to store the time of the show
    private static HashMap<String, ArrayList<Movie>>  movieHashMap = new HashMap<>(); // hashmap to store the movies data

    // getters of the data managed by BookMyShow

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

    public static HashMap<String, ArrayList<Movie>> getMovieHashMap()
    {
        return movieHashMap;
    }

}
