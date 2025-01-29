import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// pojo Screen class to maintain and manage the screen data
public class Screen
{
    private String screenName; // stores the screen name
    private int seatsCount; // stores the seats count
    private String grid; // stores the seats grid as a string
    private  HashMap<Character,ArrayList<String>> seatsGrid; // creating a hashmap to store the seats arrangements based on the grid String given by the admin
    private HashSet<Show> showHashSet = new HashSet<>(); // creating a hashset to store the shows under the screen

    //  create a constructor to assign a values to the variables by passing a parameter
    public Screen(String screenName, int seatsCount, String grid, HashMap<Character,ArrayList<String>> seatsGrid)
    {
        this.screenName = screenName;
        this.seatsCount = seatsCount;
        this.grid = grid;
        this.seatsGrid = seatsGrid;
    }

    // getters of the screen data

    public String getScreenName()
    {
        return screenName;
    }

    public int getSeatsCount()
    {
        return seatsCount;
    }

    public String getGrid()
    {
        return grid;
    }

    public HashMap<Character,ArrayList<String>> getSeatsGrid()
    {
        return seatsGrid;
    }

    public HashSet<Show> getShowHashSet()
    {
        return showHashSet;
    }

}
