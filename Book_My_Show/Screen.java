import java.util.ArrayList;
import java.util.HashMap;

public class Screen
{
    private String screenName;
    private int seatsCount;
    private  HashMap<Character,ArrayList<String>> seatsGrid;

    public Screen(String screenName, int seatsCount, HashMap<Character, ArrayList<String>> seatsGrid)
    {
        this.screenName = screenName;
        this.seatsCount = seatsCount;
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

    public HashMap<Character,ArrayList<String>> getSeatsGrid()
    {
        return seatsGrid;
    }

}
