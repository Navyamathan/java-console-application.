import java.util.ArrayList;

// pojo Customer class to maintain and manage the customer data
public class Customer
{
    private String userName; // stores the customer username
    private String password; // stores the customer password
    private String location; // stores the customer location
    private ArrayList<Tickets> ticketList = new ArrayList<>(); // creating a arraylist to store the tickets data booked by the customer

    //  create a constructor to assign a values to the variables by passing a parameter
    public Customer(String userName, String password, String location)
    {
        this.userName = userName;
        this.password = password;
        this.location = location;
    }

    // getters of the customer data

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public ArrayList<Tickets> getTicketList()
    {
        return ticketList;
    }

}
