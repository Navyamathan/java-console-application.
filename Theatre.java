import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Theatre
{
    private String theatreName;
    private String theatreLocation;
    private int screenCount;
    private ArrayList<Screen> screenList = new ArrayList<>();
    private static ArrayList<Show> showList = new ArrayList<>();
    private static ArrayList<Movie> movieList = new ArrayList<>();
    private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    private static DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Theatre(String theatreName,String theatreLocation, int screenCount)
    {
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
        this.screenCount = screenCount;
    }

    public String getTheatreName()
    {
        return theatreName;
    }

    public String getTheatreLocation()
    {
        return theatreLocation;
    }

    public int getScreenCount()
    {
        return screenCount;
    }

    public static DateTimeFormatter getLocalDateFormatter()
    {
        return localDateFormatter;
    }

    public static DateTimeFormatter getLocalTimeFormatter()
    {
        return localTimeFormatter;
    }

    public ArrayList<Screen> getScreenArrayList()
    {
        return screenList;
    }

    public static ArrayList<Show> getShowArrayList()
    {
        return showList;
    }

    public static ArrayList<Movie> getMovieArrayList()
    {
        return movieList;
    }

}
