import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

// pojo Show class to maintain and manage the show data
public class Show
{
    private LocalDate date; // stores the show date
    private LocalTime startingTime; // stores the show starting time
    private LocalTime endingTime; // stores the show ending time
    private String screenName;
    private HashMap<Character, ArrayList<String>> seatsGrid; // creating a hashmap to store the seats arrangements in a show
    private long price; // stores the price of the show

    //  create a constructor to assign a values to the variables by passing a parameter
    public Show(LocalDate date,LocalTime startingTime, LocalTime endingTime,String screenName,HashMap<Character, ArrayList<String>> seatsGrid, long price)
    {
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.screenName = screenName;
        this.seatsGrid = seatsGrid;
        this.price = price;
    }

    // getters of the show data

    public LocalDate getDate()
    {
        return date;
    }

    public LocalTime getStartingTime()
    {
        return startingTime;
    }

    public LocalTime getEndingTime()
    {
        return endingTime;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public HashMap<Character, ArrayList<String>> getSeatsGrid()
    {
        return seatsGrid;
    }

    public long getPrice()
    {
        return price;
    }

    // override the equals built-in function to check where thw starting and ending time of the given show is matches the already exist starting and ending time of a show
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Show show = (Show) obj;
        return Objects.equals(this.startingTime, show.getStartingTime()) && Objects.equals(this.endingTime, show.getEndingTime());
    }

    // override a hashCode built-in method to create a key based on the given input but not randomly to avoid the duplicate key generating by java itself
    @Override
    public int hashCode()
    {
        return Objects.hash(this.date, this.startingTime, this.endingTime);
    }

}
