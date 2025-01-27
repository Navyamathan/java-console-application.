import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show
{
    private LocalDate date;
    private String location;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private HashMap<Character, ArrayList<String>> seatsGrid;
    private long price;

    public Show(LocalDate date,String location,LocalTime startingTime, LocalTime endingTime,HashMap<Character, ArrayList<String>> seatsGrid, long price)
    {
        this.date = date;
        this.location = location;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.seatsGrid = seatsGrid;
        this.price = price;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public String getLocation()
    {
        return location;
    }

    public LocalTime getStartingTime()
    {
        return startingTime;
    }

    public LocalTime getEndingTime()
    {
        return endingTime;
    }

    public HashMap<Character, ArrayList<String>> getSeatsGrid()
    {
        return seatsGrid;
    }

    public long getPrice()
    {
        return price;
    }

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

    @Override
    public int hashCode()
    {
        return Objects.hash(this.date, this.startingTime, this.endingTime);
    }

}
