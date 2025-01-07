package Notes;

// creating a FiveHundreds class to maintain and manage the 500 notes

public class FiveHundreds extends Notes // subclass of Notes
{
    public FiveHundreds(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        super(note,count);
    }
}
