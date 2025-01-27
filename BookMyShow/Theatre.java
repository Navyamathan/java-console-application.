import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Theatre
{
    private String theatreName;
    private String theatreLocation;
    private int screenCount;
    private HashMap<String, Screen> screenHashMap = new HashMap<>();
    private static HashMap<String, ArrayList<Movie>>  movieHashMap = new HashMap<>();

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

    public static HashMap<String, ArrayList<Movie>> getMovieHashMap()
    {
        return movieHashMap;
    }

}
