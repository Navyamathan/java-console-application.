public class Notes implements Cloneable
{
    private int note;
    private int count ;

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

    public Notes clone()throws CloneNotSupportedException
    {
        return (Notes) super.clone();
    }
}
