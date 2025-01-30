import java.util.HashMap;

// pojo Theatre class to maintain and manage the theatre data
public class Theatre
{
    private String theatreName; // stores the theatre name
    private String theatreLocation; // stores the theatre location
    private int screenCount; // stores the screen count of the theatre
    private HashMap<String, Screen> screenHashMap = new HashMap<>(); // creating a hashmap to store the screens data under the theatre

    //  create a constructor to assign a values to the variables by passing a parameter
    public Theatre(String theatreName,String theatreLocation, int screenCount)
    {
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
        this.screenCount = screenCount;
    }

    // getters of the theatre data

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


}
