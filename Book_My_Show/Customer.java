public class Customer
{
    private String userName;
    private String password;
    private String location;

    public Customer(String userName, String password, String location)
    {
        this.userName = userName;
        this.password = password;
        this.location = location;
    }

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


}
