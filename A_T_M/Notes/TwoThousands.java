package Notes;

// creating a TwoThousands class to maintain and manage the 2000 notes

public class TwoThousands extends Notes // subclass of Notes
{
    public TwoThousands(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        super(note,count);
    }
}
