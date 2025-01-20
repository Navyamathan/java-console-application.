import java.time.LocalDate;
import java.time.LocalTime;

class Movie
{
    private String movieName;
    private LocalDate date;
    private LocalTime duration;
    private long price;
    private String location;
    private Theatre theatre;
    private Screen screen;
    private Show show;

    public Movie(String movieName,LocalDate date,LocalTime duration,long price,String location,Theatre theatre,Screen screen,Show show)
    {
        this.movieName = movieName;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.location = location;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
    }

    public String getMovieName()
    {
        return movieName;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public LocalTime getDuration()
    {
        return duration;
    }

    public long getPrice()
    {
        return price;
    }

    public String getLocation()
    {
        return location;
    }

    public Theatre getTheatre()
    {
        return theatre;
    }

    public Screen getScreen()
    {
        return screen;
    }

    public Show getShow()
    {
        return show;
    }

}
