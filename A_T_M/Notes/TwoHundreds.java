package Notes;

// creating a TwoHundreds class to maintain and manage the 200 notes

public class TwoHundreds extends Notes // subclass of Notes
{
    public TwoHundreds(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        super(note,count);
    }
}