import java.time.LocalTime;

public class Movie
{
    private String movieName;
    private LocalTime duration;
    private Theatre theatre;
    private Screen screen;
    private Show show;

    public Movie(String movieName,LocalTime duration,Theatre theatre,Screen screen,Show show)
    {
        this.movieName = movieName;
        this.duration = duration;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
    }

    public String getMovieName()
    {
        return movieName;
    }

    public LocalTime getDuration()
    {
        return duration;
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
