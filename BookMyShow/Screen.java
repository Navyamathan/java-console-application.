import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen
{
    private String screenName;
    private int seatsCount;
    private String grid;
    private  HashMap<Character,ArrayList<String>> seatsGrid;
    private HashSet<Show> showHashSet = new HashSet<>();

    public Screen(String screenName, int seatsCount, String grid, HashMap<Character,ArrayList<String>> seatsGrid)
    {
        this.screenName = screenName;
        this.seatsCount = seatsCount;
        this.grid = grid;
        this.seatsGrid = seatsGrid;
    }

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
