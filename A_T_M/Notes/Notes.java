package Notes;

// creating a pojo transaction class to maintain and manage the transaction data
//super class for TwoThousands, FiveHundreds, TwoHundreds and OneHundreds

public class Notes implements Cloneable // this exception comes cause of we use Clone built-in function in the project
{
    private int note;
    private int count ;

    protected Notes(int note,int count) //  create a constructor to re-assign the variable in the super class by passing a parameter
    {
        // using super keyword to invoke the super class and call the setter method to set the data
        this.note = note;
        this.count = count;
    }

    public void setNote(int note)
    {
        this.note = note;
    }

    public int getNote()
    {
        return note;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }

    public Notes clone()throws CloneNotSupportedException // overriding a build in method (here we change the return type of Object to Notes)
    {
        return (Notes) super.clone(); // by super keyword it invokes it super class and return a Notes object
    }
}



