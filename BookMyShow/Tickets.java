import java.time.LocalTime;
import java.util.ArrayList;

// pojo Tickets class to maintain and manage the tickets data
public class Tickets
{
    private String theatreName; // store the theatre name
    private String movieName; // stores the movie name
    private String location; // stores the theatre location
    private String screenName; // store the screen name
    private LocalTime startingTime; // store the show starting time
    private ArrayList<String> bookedSeats; // store the booked seat numbers in a String array
    private long ticketPrice; // store the price of the booked seats

    //  create a constructor to assign a values to the variables by passing a parameter
    public Tickets(String theatreName, String movieName, String location, String screenName, LocalTime startingTime, long ticketPrice)
    {
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.location = location;
        this.screenName = screenName;
        this.startingTime = startingTime;
        this.ticketPrice = ticketPrice;
    }

    // getters and setters of the tickets data

    public String getTheatreName()
    {
        return theatreName;
    }

    public String getMovieName()
    {
        return movieName;
    }

    public String getLocation()
    {
        return location;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public LocalTime getStartingTime()
    {
        return startingTime;
    }

    public ArrayList<String> getBookedSeats()
    {
        return bookedSeats;
    }

    public void setBookedSeats(ArrayList<String> bookedSeats)
    {
       this.bookedSeats = bookedSeats;
    }

    public long getTicketPrice()
    {
        return ticketPrice;
    }

}
