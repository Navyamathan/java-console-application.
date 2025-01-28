import java.time.LocalTime;

public class Tickets
{
    private String theatreName;
    private String movieName;
    private String location;
    private String screenName;
    private LocalTime startingTime;
    private String[] bookedSeats;
    private long ticketPrice;

    public Tickets(String theatreName, String movieName, String location, String screenName, LocalTime startingTime, long ticketPrice)
    {
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.location = location;
        this.screenName = screenName;
        this.startingTime = startingTime;
        this.ticketPrice = ticketPrice;
    }

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

    public String[] getBookedSeats()
    {
        return bookedSeats;
    }

    public void setBookedSeats(String[] bookedSeats)
    {
       this.bookedSeats = bookedSeats;
    }

    public long getTicketPrice()
    {
        return ticketPrice;
    }

}
