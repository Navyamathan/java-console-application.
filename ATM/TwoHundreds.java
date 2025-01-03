// creating a pojo transaction class to maintain and manage the transaction data

public class TwoHundreds extends Notes // subclass of Notes
{
    public TwoHundreds(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        super.setNote(note);
        super.setCount(count);
    }
}
