import java.time.LocalTime;

// pojo Movie class to maintain and manage the movie data
public class Movie
{
    private String movieName; // stores the movie name
    private LocalTime duration; // store the movie duration
    private Theatre theatre; // stores the Theatre object in where the movie is going to run
    private Screen screen; // stores the Screen object in where the movie is going to run
    private Show show; // stores the show object in where the movie is going to run

    //  create a constructor to assign a values to the variables by passing a parameter
    public Movie(String movieName,LocalTime duration,Theatre theatre,Screen screen,Show show)
    {
        this.movieName = movieName;
        this.duration = duration;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
    }

    // getters of the movie data

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
