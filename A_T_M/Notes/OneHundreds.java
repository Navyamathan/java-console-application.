package Notes;

// creating a OneHundreds class to maintain and manage the 100 notes

public class OneHundreds extends Notes // subclass of Notes
{
    public OneHundreds(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        super(note,count);
    }
}
