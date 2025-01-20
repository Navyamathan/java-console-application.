import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Theatre
{
    private String theatreName;
    private String theatreLocation;
    private int screenCount;
    private HashMap<String, Screen> screenHashMap = new HashMap<>();
    private static HashMap<LocalDate, Show> showHashMap = new HashMap<>();
    private static HashMap<String, Movie>  movieHashMap = new HashMap<>();

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

    public HashMap<String, Screen> getScreenHashMap()
    {
        return screenHashMap;
    }

    public static HashMap<LocalDate, Show> getShowHashMap()
    {
        return showHashMap;
    }

    public static HashMap<String, Movie> getMovieHashMap()
    {
        return movieHashMap;
    }

}
