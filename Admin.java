// pojo Admin class to maintain and manage the admin data
public class Admin
{
    private String userName; // to store admin username
    private String password; // to store admin password

    //  create a constructor to assign a values to the variables by passing a parameter
    public Admin(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    // getters of the admin data

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

}
