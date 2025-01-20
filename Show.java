import java.time.LocalTime;

class Show
{
    private LocalTime startingTime;
    private LocalTime endingTime;

    public Show(LocalTime startingTime, LocalTime endingTime)
    {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public LocalTime getStartingTime()
    {
        return startingTime;
    }

    public LocalTime getEndingTime()
    {
        return endingTime;
    }

}
